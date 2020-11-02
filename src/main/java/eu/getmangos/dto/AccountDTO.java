package eu.getmangos.dto;

import java.io.Serializable;
import java.util.Date;

public class AccountDTO implements Serializable{
    private static final long serialVersionUID = 1L;

    private int id;

    private String username;

    private String shaPassHash;

    private SecurityLevel gmLevel;

    private String sessionKey;

    private String v;

    private String s;

    private String email;

    private Date joinDate;

    private String lastIP;

    private int failedLogins;

    private boolean locked;

    private Date lastLogin;

    private int activeRealmId;

    private Expansion expansion;

    private Date mutetime;

    private Locale locale;

    private String os;

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

    public String getShaPassHash() {
        return shaPassHash;
    }

    public void setShaPassHash(String shaPassHash) {
        this.shaPassHash = shaPassHash;
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

    public String getV() {
        return v;
    }

    public void setV(String v) {
        this.v = v;
    }

    public String getS() {
        return s;
    }

    public void setS(String s) {
        this.s = s;
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
