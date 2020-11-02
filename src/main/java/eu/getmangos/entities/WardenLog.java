package eu.getmangos.entities;

import java.io.Serializable;
import java.util.Date;

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
import javax.validation.constraints.NotNull;

@Entity
@Table(name="warden_log")
@NamedQueries({
    @NamedQuery(name="WardenLog.findAll", query="SELECT w from WardenLog w"),
    @NamedQuery(name="WardenLog.findById", query="SELECT w from WardenLog w WHERE w.entry = :id"),
    @NamedQuery(name="WardenLog.findByAccount", query="SELECT w from WardenLog w WHERE w.account = :accountid"),
    @NamedQuery(name="WardenLog.findDeadLinks", query="SELECT w.entry FROM WardenLog as w LEFT JOIN Account as a ON a.id = w.account WHERE a.id IS NULL"),
    @NamedQuery(name="WardenLog.deleteDeadLinks", query="DELETE FROM WardenLog w WHERE w.entry in :entry")
})
public class WardenLog implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int entry;

    @NotNull
    @Column(name = "`check`")
    private int check;

    @NotNull
    @Column(name = "`action`")
    private short action;

    @NotNull
    @Column(name = "`account`")
    private int account;

    @NotNull
    private int guid;

    @NotNull
    private long map;

    @NotNull
    @Column(name = "position_x")
    private float positionX;

    @NotNull
    @Column(name = "position_y")
    private float positionY;

    @NotNull
    @Column(name = "position_z")
    private float positionZ;

    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "`date`")
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

    public short getAction() {
        return action;
    }

    public void setAction(short action) {
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
