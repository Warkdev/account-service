package eu.getmangos.dto;

import java.io.Serializable;
import java.util.Date;

import org.eclipse.microprofile.openapi.annotations.media.Schema;

public class UptimeDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    @Schema(description = "The realm ID for which this record applies")
    private int realmId;

    @Schema(description = "The time when the server was started")
    private Date started;

    @Schema(description = "The time when the server was started, formated as readable string")
    private String startStr;

    @Schema(description = "The uptime of the server, in seconds")
    private long uptime;

    @Schema(description = "The maximum number of players connected")
    private int maxPlayers;

    public int getRealmId() {
        return realmId;
    }

    public void setRealmId(int realmId) {
        this.realmId = realmId;
    }

    public Date getStarted() {
        return started;
    }

    public void setStarted(Date started) {
        this.started = started;
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
