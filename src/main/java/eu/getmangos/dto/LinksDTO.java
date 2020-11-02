package eu.getmangos.dto;

import java.io.Serializable;

public class LinksDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private int realmID;

    private int accountID;

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
