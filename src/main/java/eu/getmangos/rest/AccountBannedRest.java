package eu.getmangos.rest;

import java.util.List;

import javax.ejb.Stateless;
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

import eu.getmangos.controllers.AccountBannedController;
import eu.getmangos.controllers.DAOException;
import eu.getmangos.entities.AccountBanned;
import eu.getmangos.entities.AccountBannedId;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@Stateless
@Path("/ban/v1")
@OpenAPIDefinition(
    info = @Info(
            title = "Bans API",
            version = "1.0",
            description = "Bans API is used to retrieve Bans information",
            license = @License(
                    name = "Apache 2.0"
            )
    )
)
public class AccountBannedRest {

    @Inject private Logger logger;

    @Inject private AccountBannedController accountBannedController;

    @GET
    @Path("{id}/{bandate}")
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Retrieves a ban given the id",
        description = "This API is retrieving the ban with the given id/bandate from the database.",
        responses = {
            @ApiResponse(responseCode = "200", description = "The account", content = @Content(
                        mediaType = "application/json",
                        schema = @Schema(implementation=AccountBanned.class)
                )
        ),
            @ApiResponse(responseCode = "400", description = "Error with the request"),
            @ApiResponse(responseCode = "500", description = "An unexpected event occured")
        }
    )
    public Response find(@PathParam("id") Integer id, @PathParam("bandate") long banDate) {
        logger.debug("find() entry.");

        if (id == null) {
                return Response.status(500).entity("The provided ID is null.").build();
        }

        AccountBanned accountBanned = this.accountBannedController.find(new AccountBannedId(id, banDate));

        if(accountBanned == null) {
                return Response.status(400).entity("The provided ID has no match in the database.").build();
        }

        logger.debug("find() exit.");
        return Response.status(200).entity(accountBanned).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Retrieves all bans",
        description = "This API is retrieving all bans from the database.",
        responses = {
            @ApiResponse(responseCode = "200", description = "A list of bans", content = @Content(
                        mediaType = "application/json",
                        schema = @Schema(implementation=AccountBanned.class)
                )
        ),
            @ApiResponse(responseCode = "400", description = "Error with the request"),
            @ApiResponse(responseCode = "500", description = "An unexpected even occured")
        }
    )
    public List<AccountBanned> findAll() {
        logger.debug("findAll() entry.");
        List<AccountBanned> listAccounts = this.accountBannedController.findAll();

        logger.debug("findAll() exit.");
        return listAccounts;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Operation(summary = "Create a new ban",
        description = "This API is creating a new ban based on the provided input.",
        responses = {
            @ApiResponse(responseCode = "200", description = "The ban has been created", content = @Content(
                        mediaType = "application/json",
                        schema = @Schema(implementation=AccountBanned.class)
                )
        ),
            @ApiResponse(responseCode = "400", description = "Error with the request"),
            @ApiResponse(responseCode = "500", description = "An unexpected even occured")
        }
    )
    public Response add(AccountBanned entity) {
        try {
                this.accountBannedController.create(entity);
        } catch (DAOException daoEx) {
                return Response.status(400).entity(daoEx.getMessage()).build();
        } catch (Exception ex) {
                return Response.status(500).entity(ex.getMessage()).build();
        }
        return Response.status(200).entity("Ban has been created.").build();
    }

    @PUT
    @Path("{id}/{bandate}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Operation(summary = "Edit a ban",
        description = "This API is updating an existing ban based on the provided input.",
        responses = {
            @ApiResponse(responseCode = "200", description = "The ban has been updated", content = @Content(
                        mediaType = "application/json",
                        schema = @Schema(implementation=AccountBanned.class)
                )
        ),
            @ApiResponse(responseCode = "400", description = "Error with the request"),
            @ApiResponse(responseCode = "500", description = "An unexpected even occured")
        }
    )
    public Response edit(@PathParam("id") Integer id, @PathParam("bandate") long banDate, AccountBanned entity) {
        try {
                entity.setAccountBannedId(new AccountBannedId(id, banDate));
                this.accountBannedController.update(entity);
        } catch (DAOException daoEx) {
                return Response.status(400).entity(daoEx.getMessage()).build();
        } catch (Exception ex) {
                return Response.status(500).entity(ex.getMessage()).build();
        }
        return Response.status(200).entity("Account has been updated.").build();
    }

    @DELETE
    @Path("{id}/{bandate}")
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Delete a ban",
        description = "This API is deleting an existing ban based on the provided id.",
        responses = {
            @ApiResponse(responseCode = "200", description = "The ban has been deleted", content = @Content(
                        mediaType = "application/json",
                        schema = @Schema(implementation=AccountBanned.class)
                )
        ),
            @ApiResponse(responseCode = "400", description = "Error with the request"),
            @ApiResponse(responseCode = "500", description = "An unexpected even occured")
        }
    )
    public Response delete(@PathParam("id") Integer id, @PathParam("bandate") long banDate) {
        try {
                this.accountBannedController.delete(new AccountBannedId(id, banDate));
        } catch (DAOException daoEx) {
                return Response.status(400).entity(daoEx.getMessage()).build();
        } catch (Exception ex) {
                return Response.status(500).entity(ex.getMessage()).build();
        }
        return Response.status(200).entity("Ban has been deleted.").build();
    }

}
