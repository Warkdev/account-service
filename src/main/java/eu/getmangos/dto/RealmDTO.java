package eu.getmangos.dto;

import java.io.Serializable;

public class RealmDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private int id;

    private String name;

    private String address;

    private String localAddress;

    private String localSubnetMask;

    private int port;

    private RealmType icon;

    private int realmflags;

    private boolean invalid;

    private boolean offline;

    private boolean recommended;

    private boolean showVersion;

    private boolean newPlayers;

    private RealmTimeZone zone;

    private SecurityLevel allowedSecurityLevel;

    private double population;

    private String realmBuild;

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

    public String getRealmBuild() {
        return realmBuild;
    }

    public void setRealmBuild(String realmBuild) {
        this.realmBuild = realmBuild;
    }

    public int getRealmflags() {
        return realmflags;
    }

    public void setRealmflags(int realmflags) {
        this.realmflags = realmflags;
    }

    public boolean isInvalid() {
        return invalid;
    }

    public void setInvalid(boolean invalid) {
        this.invalid = invalid;
    }

    public boolean isOffline() {
        return offline;
    }

    public void setOffline(boolean offline) {
        this.offline = offline;
    }

    public boolean isRecommended() {
        return recommended;
    }

    public void setRecommended(boolean recommended) {
        this.recommended = recommended;
    }

    public boolean isShowVersion() {
        return showVersion;
    }

    public void setShowVersion(boolean showVersion) {
        this.showVersion = showVersion;
    }

    public boolean isNewPlayers() {
        return newPlayers;
    }

    public void setNewPlayers(boolean newPlayers) {
        this.newPlayers = newPlayers;
    }

}
