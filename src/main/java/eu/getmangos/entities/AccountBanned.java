package eu.getmangos.entities;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(
    name="account_banned"
)

@NamedQueries({
    @NamedQuery(name = "AccountBanned.findAll", query = "SELECT a FROM AccountBanned a"),
    @NamedQuery(name = "AccountBanned.findById", query = "SELECT a FROM AccountBanned a WHERE a.accountBannedPK = :id"),
    @NamedQuery(name = "AccountBanned.findByAccount", query = "SELECT a FROM AccountBanned a WHERE a.accountBannedPK.id = :id"),
    @NamedQuery(name = "AccountBanned.findDeadLinks", query = "SELECT DISTINCT ab.accountBannedPK.id FROM AccountBanned as ab LEFT JOIN Account as a ON a.id = ab.accountBannedPK.id WHERE a.id IS NULL"),
    @NamedQuery(name = "AccountBanned.deleteDeadLinks", query = "DELETE FROM AccountBanned ab WHERE ab.accountBannedPK.id IN :id"),
    @NamedQuery(name = "AccountBanned.deleteForAccount", query = "DELETE FROM AccountBanned ab WHERE ab.accountBannedPK.id = :id")
})
@Data @NoArgsConstructor @AllArgsConstructor
public class AccountBanned implements Serializable {
    private static final long serialVersionUID = 1L;

    @Embeddable
    @Data @AllArgsConstructor @NoArgsConstructor
    public static class PrimaryKeys implements Serializable {

        private static final long serialVersionUID = 1L;

        private int id;
        private long banDate;
    }

    @EmbeddedId
    private PrimaryKeys accountBannedPK;

    @Column(name="unbandate")
    @NotNull
    private Long unbanDate;

    @Column(name="bannedby")
    @NotNull
    private String bannedBy;

    @Column(name="banreason")
    @NotNull
    private String banReason;

    @Column(name="active")
    @Basic(optional = false)
    @NotNull
    private boolean active;
}
