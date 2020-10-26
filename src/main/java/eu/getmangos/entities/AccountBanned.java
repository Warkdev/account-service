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

@Entity
@Table(
    name="account_banned"
)

@NamedQueries({
    @NamedQuery(name = "AccountBanned.findAll", query = "SELECT a FROM AccountBanned a"),
    @NamedQuery(name = "AccountBanned.findById", query = "SELECT a FROM Account a WHERE a.accountBannedId = :id"),
    @NamedQuery(name = "AccountBanned.findByAccount", query = "SELECT a FROM Account a WHERE a.accountBannedId.id = :id")
})
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

    @Column(name="bannedreason")
    @NotNull
    private String bannedReason;

    @Column(name="active")
    @Basic(optional = false)
    @NotNull
    private boolean active;

    public AccountBannedId getAccountBannedId() {
        return accountBannedId;
    }

    public void setAccountBannedId(AccountBannedId accountBannedId) {
        this.accountBannedId = accountBannedId;
    }

    public Date getUnbanDate() {
        return new Date(unbanDate);
    }

    public void setUnbanDate(Date unbanDate) {
        this.unbanDate = unbanDate.getTime();
    }

    public String getBannedBy() {
        return bannedBy;
    }

    public void setBannedBy(String bannedBy) {
        this.bannedBy = bannedBy;
    }

    public String getBannedReason() {
        return bannedReason;
    }

    public void setBannedReason(String bannedReason) {
        this.bannedReason = bannedReason;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
