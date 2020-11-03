package eu.getmangos.dto;

import java.io.Serializable;
import java.util.Date;

import org.eclipse.microprofile.openapi.annotations.media.Schema;

public class BansDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    @Schema(description = "An unique identifier for this ban")
    private int id;

    @Schema(description = "Date at which this ban has been issued")
    private Date date;

    @Schema(description = "The date when this account will be automatically unbanned")
    private Date unbanDate;

    @Schema(description = "The character that banned this account")
    private String bannedBy;

    @Schema(description = "The reason for the ban")
    private String banReason;

    @Schema(description = "Indicates whether the ban is currently active or not")
    private boolean active;

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

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
