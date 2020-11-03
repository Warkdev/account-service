package eu.getmangos.rest;

import java.util.Date;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import eu.getmangos.dto.IpBannedDTO;

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;

public interface IPBansResource {

    @GET
    @Path("{ip}/{bandate}")
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Retrieves a ban given the id",
        description = "This API is retrieving the ban with the given id/bandate from the database."
    )
    @APIResponses(
        value = {
            @APIResponse(responseCode = "200", description = "The ban", content = @Content(
                        mediaType = "application/json", schema = @Schema(implementation = IpBannedDTO.class)
                )
            ),
            @APIResponse(responseCode = "400", description = "Error with the request"),
            @APIResponse(responseCode = "404", description = "Ban not found"),
            @APIResponse(responseCode = "500", description = "An unexpected event occured")
        }
    )
    public Response findIpBan(@PathParam("ip") @Parameter(
        description = "The IP address to search for",
        required = true,
        example = "127.0.0.1"
    ) String ip, @PathParam("bandate") @Parameter(
        description = "The ban date for this IP",
        required = true
    ) Date banDate);

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Retrieves all bans",
        description = "This API is retrieving all bans from the database."
    )
    @APIResponses(
        value = {
            @APIResponse(responseCode = "200", description = "A list of bans", content = @Content(
                        mediaType = "application/json", schema = @Schema(implementation = IpBannedDTO.class)
                )
            ),
            @APIResponse(responseCode = "400", description = "Error with the request"),
            @APIResponse(responseCode = "500", description = "An unexpected even occured")
        }
    )
    public List<IpBannedDTO> findAllIpBans();

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Operation(summary = "Create a new ban",
        description = "This API is creating a new ban based on the provided input."
    )
    @APIResponses(
        value = {
            @APIResponse(responseCode = "201", description = "The ban has been created", content = @Content(
                        mediaType = "application/json"
                )
            ),
            @APIResponse(responseCode = "400", description = "Error with the request"),
            @APIResponse(responseCode = "500", description = "An unexpected even occured")
        }
    )
    public Response addIpBan(IpBannedDTO entity);

    @PUT
    @Path("{ip}/{bandate}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Operation(summary = "Edit a ban",
        description = "This API is updating an existing ban based on the provided input."
    )
    @APIResponses(
        value = {
            @APIResponse(responseCode = "200", description = "The ban has been updated", content = @Content(
                        mediaType = "application/json",
                        schema = @Schema(implementation=IpBannedDTO.class)
                )
            ),
            @APIResponse(responseCode = "400", description = "Error with the request"),
            @APIResponse(responseCode = "404", description = "Ban not found"),
            @APIResponse(responseCode = "500", description = "An unexpected even occured")
        }
    )
    public Response editIpBan(@PathParam("ip") String ip, @PathParam("bandate") Date banDate, IpBannedDTO entity);

    @DELETE
    @Path("{ip}/{bandate}")
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Delete a ban",
        description = "This API is deleting an existing ban based on the provided ip."
    )
    @APIResponses(
        value = {
            @APIResponse(responseCode = "204", description = "The ban has been deleted", content = @Content(
                        mediaType = "application/json"
                )
            ),
            @APIResponse(responseCode = "400", description = "Error with the request"),
            @APIResponse(responseCode = "404", description = "Ban not found"),
            @APIResponse(responseCode = "500", description = "An unexpected even occured")
        }
    )
    public Response deleteIpBan(@PathParam("ip") String ip, @PathParam("bandate") Date banDate);

}
