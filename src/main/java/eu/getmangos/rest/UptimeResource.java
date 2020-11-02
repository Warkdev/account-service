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

public interface UptimeResource {

    @GET
    @Path("/realm/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Retrieves all uptimes data for a given realm",
        description = "This API is retrieving the uptime data from the database for a given realm."
    )
    @APIResponses(
        value = {
            @APIResponse(responseCode = "200", description = "The uptimes version", content = @Content(
                        mediaType = "application/json"
                )
            ),
            @APIResponse(responseCode = "400", description = "Error with the request"),
            @APIResponse(responseCode = "500", description = "An unexpected even occured")
        }
    )
    @Tags({@Tag(name="realm"), @Tag(name="status")})
    public Response getUptimesForRealm(@PathParam("id") Integer realmId);

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Retrieves all uptimes data",
        description = "This API is retrieving the uptime data from the database."
    )
    @APIResponses(
        value = {
            @APIResponse(responseCode = "200", description = "The uptimes", content = @Content(
                        mediaType = "application/json"
                )
            ),
            @APIResponse(responseCode = "400", description = "Error with the request"),
            @APIResponse(responseCode = "500", description = "An unexpected even occured")
        }
    )
    @Tag(name = "status")
    public Response getUptimes();
}
