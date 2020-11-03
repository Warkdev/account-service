package eu.getmangos.dto;

import java.io.Serializable;
import java.util.Date;

import org.eclipse.microprofile.openapi.annotations.media.Schema;

public class AccountDTO implements Serializable{
    private static final long serialVersionUID = 1L;

    @Schema(description = "A unique identifier of the account")
    private int id;

    @Schema(description = "An username for this account")
    private String username;

    @Schema(description = "The security level of the account")
    private SecurityLevel gmLevel;

    @Schema(description = "Session key for this account")
    private String sessionKey;

    @Schema(description = "An email address for this account. Must be of a valid format")
    private String email;

    @Schema(description = "The date at which the user joined")
    private Date joinDate;

    @Schema(description = "The last IP used for this account")
    private String lastIP;

    @Schema(description = "The amount of failed logins for this account")
    private int failedLogins;

    @Schema(description = "Indicates whether this account is locked or not")
    private boolean locked;

    @Schema(description = "Last login date for this account")
    private Date lastLogin;

    @Schema(description = "An unique identifier on which this account connected the last time")
    private int activeRealmId;

    @Schema(description = "An enum value indicating the expansion until which this account has access")
    private Expansion expansion;

    @Schema(description = "The date at which the account will be unmuted")
    private Date mutetime;

    @Schema(description = "The locale for this account")
    private Locale locale;

    @Schema(description = "OS used last time for this client")
    private String os;

    @Schema(description = "Determines whether the account is an user or a playerbot")
    private boolean playerBot;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public SecurityLevel getGmLevel() {
        return gmLevel;
    }

    public void setGmLevel(SecurityLevel gmLevel) {
        this.gmLevel = gmLevel;
    }

    public String getSessionKey() {
        return sessionKey;
    }

    public void setSessionKey(String sessionKey) {
        this.sessionKey = sessionKey;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getJoinDate() {
        return joinDate;
    }

    public void setJoinDate(Date joinDate) {
        this.joinDate = joinDate;
    }

    public String getLastIP() {
        return lastIP;
    }

    public void setLastIP(String lastIP) {
        this.lastIP = lastIP;
    }

    public int getFailedLogins() {
        return failedLogins;
    }

    public void setFailedLogins(int failedLogins) {
        this.failedLogins = failedLogins;
    }

    public boolean isLocked() {
        return locked;
    }

    public void setLocked(boolean locked) {
        this.locked = locked;
    }

    public Date getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(Date lastLogin) {
        this.lastLogin = lastLogin;
    }

    public int getActiveRealmId() {
        return activeRealmId;
    }

    public void setActiveRealmId(int activeRealmId) {
        this.activeRealmId = activeRealmId;
    }

    public Expansion getExpansion() {
        return expansion;
    }

    public void setExpansion(Expansion expansion) {
        this.expansion = expansion;
    }

    public Date getMutetime() {
        return mutetime;
    }

    public void setMutetime(Date mutetime) {
        this.mutetime = mutetime;
    }

    public Locale getLocale() {
        return locale;
    }

    public void setLocale(Locale locale) {
        this.locale = locale;
    }

    public String getOs() {
        return os;
    }

    public void setOs(String os) {
        this.os = os;
    }

    public boolean isPlayerBot() {
        return playerBot;
    }

    public void setPlayerBot(boolean playerBot) {
        this.playerBot = playerBot;
    }
}
