package eu.getmangos.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

import eu.getmangos.utils.FlagUtils;

@Entity
@Table(name="Realmlist", uniqueConstraints = {
    @UniqueConstraint(name="name", columnNames = {"name"})
})
@NamedQueries({
    @NamedQuery(name = "Realm.findAll", query = "SELECT r FROM Realm r"),
    @NamedQuery(name = "Realm.findById", query = "SELECT r FROM Realm r where r.id = :id"),
    @NamedQuery(name = "Realm.findByName", query = "SELECT r FROM Realm r where r.name = :name"),
    @NamedQuery(name = "Realm.findByType", query = "SELECT r FROM Realm r where r.icon = :type"),
    @NamedQuery(name = "Realm.findByZone", query = "SELECT r FROM Realm r where r.zone = :zone")
})
public class Realm implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @NotNull
    private String name;

    @NotNull
    private String address;

    @NotNull
    private String localAddress;

    @NotNull
    private String localSubnetMask;

    @NotNull
    private int port;

    @NotNull
    private RealmType icon;

    @NotNull
    private int realmflags;

    @NotNull
    private RealmTimeZone zone;

    @NotNull
    private SecurityLevel allowedSecurityLevel;

    @NotNull
    private double population;

    @NotNull
    @Column(name = "realmbuilds")
    private RealmBuild realmBuild;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLocalAddress() {
        return localAddress;
    }

    public void setLocalAddress(String localAddress) {
        this.localAddress = localAddress;
    }

    public String getLocalSubnetMask() {
        return localSubnetMask;
    }

    public void setLocalSubnetMask(String localSubnetMask) {
        this.localSubnetMask = localSubnetMask;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public RealmType getIcon() {
        return icon;
    }

    public void setIcon(RealmType icon) {
        this.icon = icon;
    }

    public int getRealmflags() {
        return realmflags;
    }

    public void setRealmflags(int realmflags) {
        this.realmflags = realmflags;
    }

    public boolean isInvalid() {
        return FlagUtils.hasFlag(this.realmflags, 0x1);
    }

    public void setInvalid(boolean invalid) {
        this.realmflags = FlagUtils.setFlag(this.realmflags, 0x1, invalid);
    }

    public boolean isOffline() {
        return FlagUtils.hasFlag(this.realmflags, 0x2);
    }

    public void setOffline(boolean offline) {
        this.realmflags = FlagUtils.setFlag(this.realmflags, 0x2, offline);
    }

    public boolean isShowVersion() {
        return FlagUtils.hasFlag(this.realmflags, 0x4);
    }

    public void setShowVersion(boolean showVersion) {
        this.realmflags = FlagUtils.setFlag(this.realmflags, 0x4, showVersion);
    }

    public boolean isNewPlayers() {
        return FlagUtils.hasFlag(this.realmflags, 0x20);
    }

    public void setNewPlayers(boolean newPlayers) {
        this.realmflags = FlagUtils.setFlag(this.realmflags, 0x20, newPlayers);
    }

    public boolean isRecommended() {
        return FlagUtils.hasFlag(this.realmflags, 0x40);
    }

    public void setRecommended(boolean recommended) {
        this.realmflags = FlagUtils.setFlag(this.realmflags, 0x40, recommended);
    }

    public RealmTimeZone getZone() {
        return zone;
    }

    public void setZone(RealmTimeZone zone) {
        this.zone = zone;
    }

    public SecurityLevel getAllowedSecurityLevel() {
        return allowedSecurityLevel;
    }

    public void setAllowedSecurityLevel(SecurityLevel allowedSecurityLevel) {
        this.allowedSecurityLevel = allowedSecurityLevel;
    }

    public double getPopulation() {
        return population;
    }

    public void setPopulation(double population) {
        this.population = population;
    }

    public RealmBuild getRealmBuild() {
        return realmBuild;
    }

    public void setRealmBuild(RealmBuild realmBuild) {
        this.realmBuild = realmBuild;
    }
}
