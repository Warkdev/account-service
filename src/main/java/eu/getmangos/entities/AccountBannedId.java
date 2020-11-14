package eu.getmangos.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Embeddable;

import lombok.Data;

/**
 * Composite Primary Key for AccountBanned.
 */
@Embeddable
@Data
public class AccountBannedId implements Serializable {
    private static final long serialVersionUID = 1L;

    private int id;
    private long banDate;
}
