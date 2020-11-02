package eu.getmangos.rest;

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
import org.eclipse.microprofile.openapi.annotations.tags.Tags;

public interface WardenResource {

    @GET
    @Path("/account/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Retrieves all warden log data for a given account",
        description = "This API is retrieving the warden log data from the database for a given account."
    )
    @APIResponses(
        value = {
            @APIResponse(responseCode = "200", description = "The warden logs", content = @Content(
                        mediaType = "application/json"
                )
            ),
            @APIResponse(responseCode = "400", description = "Error with the request"),
            @APIResponse(responseCode = "500", description = "An unexpected even occured")
        }
    )
    @Tags({@Tag(name="account"), @Tag(name="warden")})
    public Response getWardenLogsForAccount(@PathParam("id") Integer accountId);

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Retrieves all warden logs data",
        description = "This API is retrieving the warden logs data from the database."
    )
    @APIResponses(
        value = {
            @APIResponse(responseCode = "200", description = "The warden logs", content = @Content(
                        mediaType = "application/json"
                )
            ),
            @APIResponse(responseCode = "400", description = "Error with the request"),
            @APIResponse(responseCode = "500", description = "An unexpected even occured")
        }
    )
    @Tag(name = "warden")
    public Response getAllWardenLogs();
}
