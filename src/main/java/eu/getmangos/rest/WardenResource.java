package eu.getmangos.rest;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

import eu.getmangos.dto.CleanupResultDTO;
import eu.getmangos.dto.WardenLogDTO;

public interface WardenResource {

    @GET
    @Path("v1/accounts/{id}/wardenlogs")
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Retrieves all warden log data for a given account",
        description = "This API is retrieving the warden log data from the database for a given account."
    )
    @APIResponses(
        value = {
            @APIResponse(responseCode = "200", description = "The warden logs", content = @Content(
                        mediaType = "application/json", schema = @Schema(implementation = WardenLogDTO.class)
                )
            ),
            @APIResponse(responseCode = "400", description = "Error with the request"),
            @APIResponse(responseCode = "500", description = "An unexpected even occured")
        }
    )
    @Tag(name = "warden")
    public Response getWardenLogsForAccount(@PathParam("id") Integer accountId);

    @GET
    @Path("v1/wardenlogs")
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Retrieves all warden logs data",
        description = "This API is retrieving the warden logs data from the database."
    )
    @APIResponses(
        value = {
            @APIResponse(responseCode = "200", description = "The warden logs", content = @Content(
                        mediaType = "application/json", schema = @Schema(implementation = WardenLogDTO.class)
                )
            ),
            @APIResponse(responseCode = "400", description = "Error with the request"),
            @APIResponse(responseCode = "500", description = "An unexpected even occured")
        }
    )
    @Tag(name = "warden")
    public Response getAllWardenLogs();

    @DELETE
    @Path("v1/wardenlogs")
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Clean-up warden logs data",
        description = "This API is cleaning the orphans warden logs data from the database."
    )
    @APIResponses(
        value = {
            @APIResponse(responseCode = "200", description = "The warden logs", content = @Content(
                        mediaType = "application/json", schema = @Schema(implementation = CleanupResultDTO.class)
                )
            ),
            @APIResponse(responseCode = "400", description = "Error with the request"),
            @APIResponse(responseCode = "500", description = "An unexpected even occured")
        }
    )
    @Tag(name = "warden")
    public Response cleanupWardenLogs();
}
