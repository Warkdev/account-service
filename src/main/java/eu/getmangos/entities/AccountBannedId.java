package eu.getmangos.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Composite Primary Key for AccountBanned.
 */
@Embeddable
@Data @AllArgsConstructor
public class AccountBannedId implements Serializable {
    private static final long serialVersionUID = 1L;

    private int id;
    private long banDate;
}
