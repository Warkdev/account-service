package eu.getmangos.entities;

import java.io.Serializable;

import javax.persistence.Column;
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
    name="ip_banned"
)

@NamedQueries({
    @NamedQuery(name = "IpBanned.findAll", query = "SELECT i FROM IpBanned i"),
    @NamedQuery(name = "IpBanned.findById", query = "SELECT i FROM IpBanned i WHERE i.id = :id"),
    @NamedQuery(name = "IpBanned.findByIp", query = "SELECT i FROM IpBanned i WHERE i.id.ip = :ip")
})
@Data @NoArgsConstructor @AllArgsConstructor
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

}
