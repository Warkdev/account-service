package eu.getmangos.entities;

import java.io.Serializable;

import javax.persistence.Embeddable;


@Embeddable
public class RealmCharactersID implements Serializable {

    private static final long serialVersionUID = 1L;

    private int realmID;

    private int accountID;

    public RealmCharactersID() {

    }

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

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + accountID;
        result = prime * result + realmID;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        RealmCharactersID other = (RealmCharactersID) obj;
        if (accountID != other.accountID)
            return false;
        if (realmID != other.realmID)
            return false;
        return true;
    }

}
