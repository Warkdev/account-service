package eu.getmangos.dto;

import java.io.Serializable;
import java.util.Date;

import org.eclipse.microprofile.openapi.annotations.media.Schema;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
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
}
