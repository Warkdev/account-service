package eu.getmangos.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Embeddable;

@Embeddable
public class IpBannedId implements Serializable {
    private static final long serialVersionUID = 1L;

    private String ip;
    private long banDate;

    public IpBannedId(){
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Date getBanDate() {
        return new Date(banDate);
    }

    public void setBanDate(Date banDate) {
        this.banDate = banDate.getTime();
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (int) (banDate ^ (banDate >>> 32));
        result = prime * result + ((ip == null) ? 0 : ip.hashCode());
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
        IpBannedId other = (IpBannedId) obj;
        if (banDate != other.banDate)
            return false;
        if (ip == null) {
            if (other.ip != null)
                return false;
        } else if (!ip.equals(other.ip))
            return false;
        return true;
    }
}
