package eu.getmangos.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;

import eu.getmangos.dto.DbVersionDTO;

public interface VersionResource {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Retrieves the database version",
        description = "This API is retrieving the database version from the database."
    )
    @APIResponses(
        value = {
            @APIResponse(responseCode = "200", description = "The database version", content = @Content(
                        mediaType = "application/json", schema = @Schema(implementation=DbVersionDTO.class)
                )
            ),
            @APIResponse(responseCode = "400", description = "Error with the request"),
            @APIResponse(responseCode = "500", description = "An unexpected even occured")
        }
    )
    public DbVersionDTO getDbVersion();
}
