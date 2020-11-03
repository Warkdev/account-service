package eu.getmangos.dto;

import java.io.Serializable;

import org.eclipse.microprofile.openapi.annotations.media.Schema;

public class LinksDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "The ID of the realm")
    private int realmID;

    @Schema(description = "The ID of the account")
    private int accountID;

    @Schema(description = "The number of characters the account has on the realm")
    private int numChars;

    public int getRealmID() {
        return realmID;
    }

    public void setRealmID(int realmID) {
        this.realmID = realmID;
    }

    public int getAccountID() {
        return accountID;
    }

    public void setAccountID(int accountID) {
        this.accountID = accountID;
    }

    public int getNumChars() {
        return numChars;
    }

    public void setNumChars(int numChars) {
        this.numChars = numChars;
    }
}
