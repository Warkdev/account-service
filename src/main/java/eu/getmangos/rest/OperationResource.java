package eu.getmangos.rest;

import java.util.Map;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

public interface OperationResource {

    @DELETE
    @Path("/cleanup")
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Clean-up all dead links from the database",
        description = "This API is cleaning-up bans, links, uptimes info and logs from the database for accounts/realms which were removed."
    )
    @APIResponses(
        value = {
            @APIResponse(responseCode = "200", description = "The database has been cleaned-up with a summary of the cleaning", content = @Content(
                        mediaType = "application/json", schema = @Schema(implementation = Map.class)
                )
            ),
            @APIResponse(responseCode = "400", description = "Error with the request"),
            @APIResponse(responseCode = "500", description = "An unexpected even occured")
        }
    )
    public Response cleanUpDB();

    @GET
    @Path("/cleanup")
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Retrieves a list of all items which will be cleaned up if the clean-up operation is performed.",
        description = "This API is showing all items which will be cleaned up from the database."
    )
    @APIResponses(
        value = {
            @APIResponse(responseCode = "200", description = "The list of items which will be cleaned-up is returned.", content = @Content(
                        mediaType = "application/json", schema = @Schema(implementation = Map.class)
                )
            ),
            @APIResponse(responseCode = "400", description = "Error with the request"),
            @APIResponse(responseCode = "500", description = "An unexpected even occured")
        }
    )
    public Response showCleanUpDB();
}
