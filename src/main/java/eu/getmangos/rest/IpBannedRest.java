package eu.getmangos.rest;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
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

import org.slf4j.Logger;

import eu.getmangos.controllers.DAOException;
import eu.getmangos.controllers.IpBannedController;
import eu.getmangos.entities.IpBanned;
import eu.getmangos.entities.IpBannedId;

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;

@RequestScoped
@Path("/banip/v1")
public class IpBannedRest {

    @Inject private Logger logger;

    @Inject private IpBannedController ipBannedController;

    @GET
    @Path("{ip}/{bandate}")
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Retrieves a ban given the id",
        description = "This API is retrieving the ban with the given id/bandate from the database."
    )
    @APIResponses(
        value = {
            @APIResponse(responseCode = "200", description = "The ban", content = @Content(
                        mediaType = "application/json",
                        schema = @Schema(implementation=IpBanned.class)
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
    ) long banDate) {
        logger.debug("find() entry.");

        if (ip == null || ip.isBlank()) {
                return Response.status(500).entity("The provided IP is null.").build();
        }

        IpBanned IpBanned = this.ipBannedController.find(new IpBannedId(ip, banDate));

        if(IpBanned == null) {
                return Response.status(404).entity("The provided ID has no match in the database.").build();
        }

        logger.debug("find() exit.");
        return Response.status(200).entity(IpBanned).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Retrieves all bans",
        description = "This API is retrieving all bans from the database."
    )
    @APIResponses(
        value = {
            @APIResponse(responseCode = "200", description = "A list of bans", content = @Content(
                        mediaType = "application/json",
                        schema = @Schema(implementation=IpBanned.class)
                )
            ),
            @APIResponse(responseCode = "400", description = "Error with the request"),
            @APIResponse(responseCode = "500", description = "An unexpected even occured")
        }
    )
    public List<IpBanned> findAllIpBans() {
        logger.debug("findAll() entry.");
        List<IpBanned> listBans = this.ipBannedController.findAll();

        logger.debug("findAll() exit.");
        return listBans;
    }

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
    public Response addIpBan(IpBanned entity) {
        try {
                this.ipBannedController.create(entity);
        } catch (DAOException daoEx) {
                return Response.status(400).entity(daoEx.getMessage()).build();
        } catch (Exception ex) {
                return Response.status(500).entity(ex.getMessage()).build();
        }
        return Response.status(201).entity("Ban has been created.").build();
    }

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
                        schema = @Schema(implementation=IpBanned.class)
                )
            ),
            @APIResponse(responseCode = "400", description = "Error with the request"),
            @APIResponse(responseCode = "404", description = "Ban not found"),
            @APIResponse(responseCode = "500", description = "An unexpected even occured")
        }
    )
    public Response editIpBan(@PathParam("ip") String ip, @PathParam("bandate") long banDate, IpBanned entity) {
        try {
                entity.setId(new IpBannedId(ip, banDate));
                this.ipBannedController.update(entity);
        } catch (DAOException daoEx) {
                return Response.status(404).entity(daoEx.getMessage()).build();
        } catch (Exception ex) {
                return Response.status(500).entity(ex.getMessage()).build();
        }
        return Response.status(200).entity("Ban has been updated.").build();
    }

    @DELETE
    @Path("{ip}/{bandate}")
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Delete a ban",
        description = "This API is deleting an existing ban based on the provided ip."
    )
    @APIResponses(
        value = {
            @APIResponse(responseCode = "204", description = "The ban has been deleted", content = @Content(
                        mediaType = "application/json",
                        schema = @Schema(implementation=IpBanned.class)
                )
            ),
            @APIResponse(responseCode = "400", description = "Error with the request"),
            @APIResponse(responseCode = "404", description = "Ban not found"),
            @APIResponse(responseCode = "500", description = "An unexpected even occured")
        }
    )
    public Response deleteIpBan(@PathParam("ip") String ip, @PathParam("bandate") long banDate) {
        try {
                this.ipBannedController.delete(new IpBannedId(ip, banDate));
        } catch (DAOException daoEx) {
                return Response.status(404).entity(daoEx.getMessage()).build();
        } catch (Exception ex) {
                return Response.status(500).entity(ex.getMessage()).build();
        }
        return Response.status(204).build();
    }

}
