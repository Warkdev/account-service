package eu.getmangos.dto;

import java.io.Serializable;
import java.util.Date;

import org.eclipse.microprofile.openapi.annotations.media.Schema;

public class WardenLogDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "An unique identifier for the log entry")
    private int entry;

    @Schema(description = "Failed warden check ID")
    private int check;

    @Schema(description = "The action taken by Warden")
    private WardenActions action;

    @Schema(description = "The account ID of the player")
    private int account;

    @Schema(description = "The player Global Unique Identifier (GUID)")
    private int guid;

    @Schema(description = "The map ID (see map.dbc)")
    private long map;

    @Schema(description = "The x location of the player")
    private float positionX;

    @Schema(description = "The y position of the player")
    private float positionY;

    @Schema(description = "The z position of the player")
    private float positionZ;

    @Schema(description = "The date/time when the log entry was raised")
    private Date date;

    public int getEntry() {
        return entry;
    }

    public void setEntry(int entry) {
        this.entry = entry;
    }

    public int getCheck() {
        return check;
    }

    public void setCheck(int check) {
        this.check = check;
    }

    public WardenActions getAction() {
        return action;
    }

    public void setAction(WardenActions action) {
        this.action = action;
    }

    public int getAccount() {
        return account;
    }

    public void setAccount(int account) {
        this.account = account;
    }

    public int getGuid() {
        return guid;
    }

    public void setGuid(int guid) {
        this.guid = guid;
    }

    public long getMap() {
        return map;
    }

    public void setMap(long map) {
        this.map = map;
    }

    public float getPositionX() {
        return positionX;
    }

    public void setPositionX(float positionX) {
        this.positionX = positionX;
    }

    public float getPositionY() {
        return positionY;
    }

    public void setPositionY(float positionY) {
        this.positionY = positionY;
    }

    public float getPositionZ() {
        return positionZ;
    }

    public void setPositionZ(float positionZ) {
        this.positionZ = positionZ;
    }

    public Date getDate() {
        return this.date;
    }

    public void setTimestamp(Date date) {
        this.date = date;
    }
}
