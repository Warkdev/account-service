package eu.getmangos.dto;

import java.io.Serializable;
import java.util.Date;

import org.eclipse.microprofile.openapi.annotations.media.Schema;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
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
}
