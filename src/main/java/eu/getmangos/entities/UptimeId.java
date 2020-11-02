package eu.getmangos.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class UptimeId implements Serializable {
    private static final long serialVersionUID = 1L;

    private int realmId;

    @Column(name = "starttime")
    private long started;

    public UptimeId() {
    }

    public UptimeId(int realmId, long started) {
        this.realmId = realmId;
        this.started = started;
    }

    public int getRealmId() {
        return realmId;
    }

    public void setRealmId(int realmId) {
        this.realmId = realmId;
    }

    public Date getStarted() {
        return new Date(started);
    }

    public void setStarted(Date started) {
        this.started = started.getTime();
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + realmId;
        result = prime * result + (int) (started ^ (started >>> 32));
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        UptimeId other = (UptimeId) obj;
        if (realmId != other.realmId)
            return false;
        if (started != other.started)
            return false;
        return true;
    }
}
