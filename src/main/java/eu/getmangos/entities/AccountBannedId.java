package eu.getmangos.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Embeddable;

/**
 * Composite Primary Key for AccountBanned.
 */
@Embeddable
public class AccountBannedId implements Serializable {
    private static final long serialVersionUID = 1L;

    private int id;
    private long banDate;

    public AccountBannedId() {

    }

    public AccountBannedId(int id, long banDate) {
        this.id = id;
        this.banDate = banDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getBanDate() {
        return new Date(banDate);
    }

    public void setBanDate(Date banDate) {
        this.banDate = banDate.getTime();
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (int) (banDate ^ (banDate >>> 32));
        result = prime * result + id;
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
        AccountBannedId other = (AccountBannedId) obj;
        if (banDate != other.banDate)
            return false;
        if (id != other.id)
            return false;
        return true;
    }
}
