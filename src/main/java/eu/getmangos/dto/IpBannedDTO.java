package eu.getmangos.dto;

import java.io.Serializable;
import java.util.Date;

public class IpBannedDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private String ip;
    private Date banDate;

    private long unbanDate;

    private String bannedBy;

    private String banReason;

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Date getBanDate() {
        return banDate;
    }

    public void setBanDate(Date banDate) {
        this.banDate = banDate;
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
