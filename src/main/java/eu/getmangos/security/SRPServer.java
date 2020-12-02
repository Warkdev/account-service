package eu.getmangos.security;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

import eu.getmangos.utils.BigNumber;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * SRPServer is an helper class to handle SRP6 calculation and hashing methods
 * for WoW client since it deviates from the original SRP6 specification..
 *
 * @author Talendrys
 * @version v1.0.
 */
@ApplicationScoped
@Named("srpServer")
@Getter @Setter @ToString @NoArgsConstructor
public class SRPServer {
    /**
     * N is a safe-prime of 32 bytes length. Equals to
     * 894B645E89E1535BBDAD5B8B290650530801B18EBFBF5E8FAB3C82872A3E9BB7.
     */
    private static final BigNumber N = new BigNumber("894B645E89E1535BBDAD5B8B290650530801B18EBFBF5E8FAB3C82872A3E9BB7", 16);

    /**
     * g is a generator of 1 byte. Equals to 7.
     */
    private static final BigNumber g = new BigNumber("7");

    /**
     * k is a constant used to generate other proofs. Equals to 3.
     */
    private static final BigNumber k = new BigNumber("3");

    /**
     * b is the server private value of 19 bytes, it is never shared and is only
     * used for one authentication.
     */
    private final BigNumber b = new BigNumber().setRand(19); // server private value

    /**
     * s is the Salt generated, it is stored in database once generated for a
     * given account.
     */
    private BigNumber s; // Salt

    /**
     * v is the Password Verifier generated, it is stored in database once
     * generated for a given account.
     */
    private BigNumber v; // Verifier

    /**
     * gmod is used to calculate B, the server public value.
     */
    private BigNumber gmod; // gmod - used to calculate B

    /**
     * B is the server public value.
     */
    private BigNumber B; // server public value

    /**
     * x is the password verifier, never held in database, always calculated
     * from H(s | H(I | ":" | p)).
     */
    private BigNumber x; // Intermediate verifier

    /**
     * M2 is the server proof to show the client that the server knows the
     * password.
     */
    private BigNumber M2; // Server M calculation

    /**
     * S is the session key, unencrypted.
     */
    private BigNumber S; // Session Key

    /**
     * K is the hashed session key.
     */
    private BigNumber K; // Hashed Session Key

    /**
     * I is the account name.
     */
    byte[] I = null; // Client name

    // Cryptographic digest.
    private MessageDigest md;

    @PostConstruct
    private void init() {
        try {
            md = MessageDigest.getInstance("SHA1");
        } catch (NoSuchAlgorithmException ex) {
            System.out.println("No such algorithm available");
        }
    }

    /**
     * Generates a new random 32-bytes Salt.
     *
     * @return The BigNumber generated Salt.
     */
    public BigNumber generateS() {
        if (this.s == null) {
            this.s = new BigNumber().setRand(32);
        }

        return this.s;
    }

    /**
     * Performs the first step of the authentication challenge, generating the
     * server public value.
     */
    public void step1() {
        // Generate B - The server public value - (k.v + g^b)
        this.gmod = g.modPow(this.b, N);
        this.B = (this.v.multiply(k).add(this.gmod)).remainder(N);
    }

    /**
     * Performs the second step of the authentication challenge, verifying that
     * the server knows the information the client has generated.
     *
     * @param A The A sent by the client.
     * @param M1 The proof sent by the client.
     * @return True if the server recognize the proof, false otherwise.
     */
    public boolean step2(BigNumber A, BigNumber M1) {
        // Generate u - the so called "Random scrambling parameter"
        // H(A | B)
        this.md.update(A.asByteArray(32));
        this.md.update(this.B.asByteArray(32));

        BigNumber u = new BigNumber();
        u.setBinary(this.md.digest());

        // Generate S - the Session key - (A.v^u)^b
        this.S = (A.multiply(this.v.modPow(u, N))).modPow(this.b, N);

        // Generate vK - the hashed session key, hashed with H hash function
        byte[] t = this.S.asByteArray(32);
        byte[] t1 = new byte[16];
        byte[] vK = new byte[40];

        for (int i = 0; i < 16; i++) {
            t1[i] = t[i * 2];
        }

        this.md.update(t1);

        byte[] digest = this.md.digest();
        for (int i = 0; i < 20; i++) {
            vK[i * 2] = digest[i];
        }

        for (int i = 0; i < 16; i++) {
            t1[i] = t[i * 2 + 1];
        }

        this.md.update(t1);
        digest = this.md.digest();
        for (int i = 0; i < 20; i++) {
            vK[i * 2 + 1] = digest[i];
        }

        // generating M - the server's SRP6 M value
        // Formula: H(H(N)^H(g),H(I),s,A,B,K)
        // H(N)
        this.md.update(N.asByteArray(32));
        byte[] hash = this.md.digest();

        // H(g)
        this.md.update(g.asByteArray(1));
        digest = this.md.digest();

        // H(N)^H(g)
        for (int i = 0; i < 20; i++) {
            hash[i] ^= digest[i];
        }

        this.md.update(I);
        byte[] t4 = this.md.digest();

        this.K = new BigNumber();
        this.K.setBinary(vK);

        BigNumber t3 = new BigNumber();
        t3.setBinary(hash);
        BigNumber t4_correct = new BigNumber();
        t4_correct.setBinary(t4);

        // All together
        this.md.update(t3.asByteArray());
        this.md.update(t4_correct.asByteArray());
        this.md.update(this.s.asByteArray());
        this.md.update(A.asByteArray());
        this.md.update(this.B.asByteArray());
        this.md.update(this.K.asByteArray());

        byte[] m = this.md.digest();
        this.M2 = new BigNumber();
        this.M2.setBinary(m, false);

        return this.M2.equals(M1);
    }

    /**
     * Generates the hash logon proof from A and M1.
     *
     * @param A The Big Number A to be used.
     * @return The Hash Logon Proof.
     */
    public byte[] generateHashLogonProof(BigNumber A) {
        // Formula: H(A | M2 | K)
        this.md.update(A.asByteArray(32));
        this.md.update(this.M2.asByteArray(20, false));
        this.md.update(this.K.asByteArray(40));

        return this.md.digest();
    }

    public byte[] generateHashReconnectProof(BigNumber R1, BigNumber challenge, BigNumber sessionKey) {
        // Formula: H(account | R1 | challenge | session key)
        this.md.update(this.I);
        this.md.update(R1.asByteArray(16));
        this.md.update(challenge.asByteArray(16));
        this.md.update(sessionKey.asByteArray(40));

        return this.md.digest();
    }

}
