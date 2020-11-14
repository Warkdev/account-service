package eu.getmangos.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Entity
@Table(
    name="account_banned"
)

@NamedQueries({
    @NamedQuery(name = "AccountBanned.findAll", query = "SELECT a FROM AccountBanned a"),
    @NamedQuery(name = "AccountBanned.findById", query = "SELECT a FROM AccountBanned a WHERE a.accountBannedId = :id"),
    @NamedQuery(name = "AccountBanned.findByAccount", query = "SELECT a FROM AccountBanned a WHERE a.accountBannedId.id = :id"),
    @NamedQuery(name = "AccountBanned.findDeadLinks", query = "SELECT DISTINCT ab.accountBannedId.id FROM AccountBanned as ab LEFT JOIN Account as a ON a.id = ab.accountBannedId.id WHERE a.id IS NULL"),
    @NamedQuery(name = "AccountBanned.deleteDeadLinks", query = "DELETE FROM AccountBanned ab WHERE ab.accountBannedId.id IN :id")
})
@Data
public class AccountBanned implements Serializable {
    private static final long serialVersionUID = 1L;

    @EmbeddedId
    private AccountBannedId accountBannedId;

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
