package eu.getmangos.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(
    name="account",
    uniqueConstraints = {
        @UniqueConstraint(name="username", columnNames = {"username"}
        )
    }
)

@NamedQueries({
    @NamedQuery(name = "Account.findAll", query = "SELECT a FROM Account a"),
    @NamedQuery(name = "Account.findById", query = "SELECT a FROM Account a where a.id = :id"),
    @NamedQuery(name = "Account.findByName", query = "SELECT a FROM Account a where a.username = :name")
})
/**
 * This class represent an entity Account from the Realm Database.
 */
@Data @NoArgsConstructor @AllArgsConstructor
public class Account implements Serializable{
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name="username", length=32)
    @NotNull
    private String username;

    @Column(name="sha_pass_hash", length=40)
    @NotNull
    private String shaPassHash;

    @Column(name="gmlevel")
    @NotNull
    private short gmLevel;

    @Column(name="sessionkey")
    private String sessionKey;

    @Column(name="v")
    private String v;

    @Column(name="s")
    private String s;

    @Column(name="email")
    private String email;

    @Column(name="joindate")
    @Temporal(TemporalType.TIMESTAMP)
    @NotNull
    private Date joinDate;

    @Column(name="last_ip", length = 30)
    @NotNull
    private String lastIP;

    @Column(name="failed_logins")
    @NotNull
    private int failedLogins;

    @Column(name="locked")
    @Basic(optional = false)
    @NotNull
    private boolean locked;

    @Column(name="last_login")
    @Temporal(TemporalType.TIMESTAMP)
    @NotNull
    private Date lastLogin;

    @Column(name="active_realm_id")
    @NotNull
    private int activeRealmId;

    @Column(name="expansion")
    @NotNull
    private short expansion;

    @Column(name="mutetime")
    @NotNull
    private Long mutetime;

    @Column(name="locale")
    @NotNull
    private short locale;

    @Column(name="os", length = 3)
    private String os;

    @Column(name="playerBot")
    @Basic(optional = false)
    @NotNull
    private boolean playerBot;
}
