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
}
