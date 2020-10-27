package eu.getmangos.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(
    name="ip_banned"
)

@NamedQueries({
    @NamedQuery(name = "IpBanned.findAll", query = "SELECT i FROM IpBanned i"),
    @NamedQuery(name = "IpBanned.findById", query = "SELECT i FROM IpBanned i WHERE i.IpBanned = :id"),
    @NamedQuery(name = "IpBanned.findByIp", query = "SELECT i FROM IpBanned i WHERE i.IpBanned.ip = :id")
})
public class IpBanned implements Serializable {

    private static final long serialVersionUID = 1L;

    @EmbeddedId
    private IpBannedId id;

    @Column(name="unbandate")
    @NotNull
    private long unbanDate;

    @Column(name="bannedby", length = 50)
    @NotNull
    private String bannedBy;

    @Column(name="banreason", length = 255)
    @NotNull
    private String banReason;

    public IpBannedId getId() {
        return id;
    }

    public void setId(IpBannedId id) {
        this.id = id;
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

    public String getBanReason() {
        return banReason;
    }

    public void setBanReason(String banReason) {
        this.banReason = banReason;
    }
}
