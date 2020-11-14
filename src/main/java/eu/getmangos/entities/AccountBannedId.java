package eu.getmangos.entities;

import java.io.Serializable;

import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Composite Primary Key for AccountBanned.
 */
@Embeddable
@Data @NoArgsConstructor @AllArgsConstructor
public class AccountBannedId implements Serializable {
    private static final long serialVersionUID = 1L;

    private int id;
    private long banDate;
}
