package eu.getmangos.dto;

import java.io.Serializable;

import org.eclipse.microprofile.openapi.annotations.media.Schema;

public class RealmDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "An unique identifier for the realm")
    private int id;

    @Schema(description = "The name of the realm")
    private String name;

    @Schema(description = "The public IP address of the world server")
    private String address;

    @Schema(description = "The local IP address of the world server")
    private String localAddress;

    @Schema(description = "The subnet mask used for the local network")
    private String localSubnetMask;

    @Schema(description = "The port that the world server is running on")
    private int port;

    @Schema(description = "The icon of the realm", implementation = RealmType.class)
    private RealmType icon;

    @Schema(description = "Indicates whether this realm is invalid")
    private boolean invalid;

    @Schema(description = "Indicates whether this realm is offline")
    private boolean offline;

    @Schema(description = "Indicates whether this realm is recommended")
    private boolean recommended;

    @Schema(description = "Indicates whether this realm shows its version")
    private boolean showVersion;

    @Schema(description = "Indicates whether this realm is recommended for new players")
    private boolean newPlayers;

    @Schema(description = "The realm timezone")
    private RealmTimeZone zone;

    @Schema(description = "Minimum account level required to login")
    private SecurityLevel allowedSecurityLevel;

    @Schema(description = "Show the current population")
    private double population;

    @Schema(description = "The accepted client builds that the realm will accept")
    private RealmBuild build;

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

    public RealmBuild getBuild() {
        return build;
    }

    public void setBuild(RealmBuild build) {
        this.build = build;
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
