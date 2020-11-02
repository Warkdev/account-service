package eu.getmangos.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "uptime")
@NamedQueries({
    @NamedQuery(name = "Uptime.findAll", query = "SELECT u FROM Uptime u"),
    @NamedQuery(name = "Uptime.findByRealm", query = "SELECT u FROM Uptime u WHERE u.id.realmId = :id"),
    @NamedQuery(name = "Uptime.findDeadLinks", query= "SELECT DISTINCT u.id.realmId FROM Uptime as u LEFT JOIN Realm as r ON u.id.realmId = r.id WHERE r.id IS NULL"),
    @NamedQuery(name = "Uptime.deleteDeadLinks", query= "DELETE FROM Uptime u WHERE u.id.realmId in :id")
})
public class Uptime implements Serializable {
    private static final long serialVersionUID = 1L;

    @EmbeddedId
    private UptimeId id;

    @Column(name = "startstring")
    @NotNull
    private String startStr;

    @NotNull
    private long uptime;

    @Column(name = "maxplayers")
    @NotNull
    private int maxPlayers;

    public UptimeId getId() {
        return id;
    }

    public void setId(UptimeId id) {
        this.id = id;
    }

    public String getStartStr() {
        return startStr;
    }

    public void setStartStr(String startStr) {
        this.startStr = startStr;
    }

    public long getUptime() {
        return uptime;
    }

    public void setUptime(long uptime) {
        this.uptime = uptime;
    }

    public int getMaxPlayers() {
        return maxPlayers;
    }

    public void setMaxPlayers(int maxPlayers) {
        this.maxPlayers = maxPlayers;
    }
}
