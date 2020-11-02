package eu.getmangos.dto;

import java.io.Serializable;
import java.util.Date;

public class WardenLogDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private int entry;

    private int check;

    private WardenActions action;

    private int account;

    private int guid;

    private long map;

    private float positionX;

    private float positionY;

    private float positionZ;

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
