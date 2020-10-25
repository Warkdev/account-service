package eu.getmangos.entities;

import java.sql.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

@Entity(name="Account")
@Table(
    name="account",
    uniqueConstraints = {
        @UniqueConstraint(name="username", columnNames = {"username"}
        )
    }
)

@Access(AccessType.FIELD)
/**
 * This class represent an entity Account from the Realm Database.
 */
public class Account {
    @Id
    @GeneratedValue
    private int id;

    @Column(name="username", length=32)
    @NotNull
    private String username;

    @Column(name="sha_pass_hash", length=40)
    @NotNull
    private String shaPassHash;

    @Column(name="gmlevel")
    @NotNull
    private short gmLevel;

    @Column(name="sessionkey")
    private String sessionKey;

    @Column(name="v")
    private String v;

    @Column(name="s")
    private String s;

    @Column(name="email")
    private String email;

    @Column(name="joindate")
    @Temporal(TemporalType.TIMESTAMP)
    @NotNull
    private Date joinDate;

    @Column(name="last_ip", length = 30)
    @NotNull
    private String lastIP;

    @Column(name="failed_logins")
    @NotNull
    private int failedLogins;

    @Column(name="locked")
    @Basic(optional = false)
    @NotNull
    private boolean locked;

    @Column(name="last_login")
    @Temporal(TemporalType.TIMESTAMP)
    @NotNull
    private Date lastLogin;

    @Column(name="active_realm_id")
    @NotNull
    private int activeRealmId;

    @Column(name="expansion")
    @Enumerated(EnumType.ORDINAL)
    @NotNull
    private Expansion expansion;

    @Column(name="mutetime")
    @Temporal(TemporalType.TIMESTAMP)
    @NotNull
    private Date mutetime;

    @Column(name="locale")
    @Enumerated(EnumType.ORDINAL)
    @NotNull
    private Locale locale;

    @Column(name="os", length = 3)
    private String os;

    @Column(name="playerBot")
    @Basic(optional = false)
    @NotNull
    private boolean playerBot;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getShaPassHash() {
        return shaPassHash;
    }

    public void setShaPassHash(String shaPassHash) {
        this.shaPassHash = shaPassHash;
    }

    public short getGmLevel() {
        return gmLevel;
    }

    public void setGmLevel(short gmLevel) {
        this.gmLevel = gmLevel;
    }

    public String getSessionKey() {
        return sessionKey;
    }

    public void setSessionKey(String sessionKey) {
        this.sessionKey = sessionKey;
    }

    public String getV() {
        return v;
    }

    public void setV(String v) {
        this.v = v;
    }

    public String getS() {
        return s;
    }

    public void setS(String s) {
        this.s = s;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getJoinDate() {
        return joinDate;
    }

    public void setJoinDate(Date joinDate) {
        this.joinDate = joinDate;
    }

    public String getLastIP() {
        return lastIP;
    }

    public void setLastIP(String lastIP) {
        this.lastIP = lastIP;
    }

    public int getFailedLogins() {
        return failedLogins;
    }

    public void setFailedLogins(int failedLogins) {
        this.failedLogins = failedLogins;
    }

    public boolean isLocked() {
        return locked;
    }

    public void setLocked(boolean locked) {
        this.locked = locked;
    }

    public Date getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(Date lastLogin) {
        this.lastLogin = lastLogin;
    }

    public int getActiveRealmId() {
        return activeRealmId;
    }

    public void setActiveRealmId(int activeRealmId) {
        this.activeRealmId = activeRealmId;
    }

    public Expansion getExpansion() {
        return expansion;
    }

    public void setExpansion(Expansion expansion) {
        this.expansion = expansion;
    }

    public Date getMutetime() {
        return mutetime;
    }

    public void setMutetime(Date mutetime) {
        this.mutetime = mutetime;
    }

    public Locale getLocale() {
        return locale;
    }

    public void setLocale(Locale locale) {
        this.locale = locale;
    }

    public String getOs() {
        return os;
    }

    public void setOs(String os) {
        this.os = os;
    }

    public boolean isPlayerBot() {
        return playerBot;
    }

    public void setPlayerBot(boolean playerBot) {
        this.playerBot = playerBot;
    }
}
