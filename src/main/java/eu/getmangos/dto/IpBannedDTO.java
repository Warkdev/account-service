package eu.getmangos.dto;

import java.io.Serializable;
import java.util.Date;

import org.eclipse.microprofile.openapi.annotations.media.Schema;

public class IpBannedDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "The IP address that is banned")
    private String ip;

    @Schema(description = "The date when the IP was forst banned")
    private Date banDate;

    @Schema(description = "The date when the IP will be unbanned")
    private Date unbanDate;

    @Schema(description = "The name of the character that banned the IP")
    private String bannedBy;

    @Schema(description = "The reason given for the IP ban")
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
        return unbanDate;
    }

    public void setUnbanDate(Date unbanDate) {
        this.unbanDate = unbanDate;
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
