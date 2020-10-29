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

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;
import org.slf4j.Logger;

import eu.getmangos.controllers.DAOException;
import eu.getmangos.controllers.RealmCharactersController;
import eu.getmangos.entities.RealmCharacters;
import eu.getmangos.entities.RealmCharactersID;

@RequestScoped
@Path("/account_realms/v1")
public class RealmCharactersRest {

    @Inject private Logger logger;

    @Inject private RealmCharactersController realmCharactersController;

    @GET
    @Path("/account/{accountID}/realm/{realmID}")
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Retrieves a link between an account and a realm",
        description = "This API is retrieving the link with the given id from the database."
    )
    @APIResponses(
        value = {
            @APIResponse(responseCode = "200", description = "The link between the account and the realm", content = @Content(
                        mediaType = "application/json",
                        schema = @Schema(implementation=RealmCharacters.class)
                )
            ),
            @APIResponse(responseCode = "400", description = "Error with the request"),
            @APIResponse(responseCode = "404", description = "Link not found"),
            @APIResponse(responseCode = "500", description = "An unexpected event occured")
        }
    )
    public Response findRealmCharacters(@PathParam("realmID") Integer realmID, @PathParam("accountID") Integer accountID) {
        logger.debug("find() entry.");

        if (realmID == null || accountID == null) {
                return Response.status(500).entity("The provided ID is null.").build();
        }

        RealmCharacters link = this.realmCharactersController.find(new RealmCharactersID(realmID, accountID));

        if(link == null) {
                return Response.status(404).entity("The provided ID has no match in the database.").build();
        }

        logger.debug("find() exit.");
        return Response.status(200).entity(link).build();
    }

    @GET
    @Path("/realm/{realmID}")
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Retrieves all links for a given realm",
        description = "This API is retrieving the links with the given realm id from the database."
    )
    @APIResponses(
        value = {
            @APIResponse(responseCode = "200", description = "A list of links for the given realm", content = @Content(
                        mediaType = "application/json",
                        schema = @Schema(implementation=RealmCharacters.class)
                )
            ),
            @APIResponse(responseCode = "400", description = "Error with the request"),
            @APIResponse(responseCode = "404", description = "Links not found"),
            @APIResponse(responseCode = "500", description = "An unexpected event occured")
        }
    )
    public Response findRealmCharactersByRealmID(@PathParam("realmID") Integer realmID) {
        logger.debug("find() entry.");

        if (realmID == null) {
                return Response.status(500).entity("The provided ID is null.").build();
        }

        List<RealmCharacters> linkList = this.realmCharactersController.findByRealm(realmID);

        logger.debug("find() exit.");
        return Response.status(200).entity(linkList).build();
    }

    @GET
    @Path("/account/{accountID}")
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Retrieves all links for a given account",
        description = "This API is retrieving the links with the given account id from the database."
    )
    @APIResponses(
        value = {
            @APIResponse(responseCode = "200", description = "A list of links for the given account", content = @Content(
                        mediaType = "application/json",
                        schema = @Schema(implementation=RealmCharacters.class)
                )
            ),
            @APIResponse(responseCode = "400", description = "Error with the request"),
            @APIResponse(responseCode = "404", description = "Links not found"),
            @APIResponse(responseCode = "500", description = "An unexpected event occured")
        }
    )
    public Response findRealmCharactersByAccountID(@PathParam("accountID") Integer accountID) {
        logger.debug("find() entry.");

        if (accountID == null) {
                return Response.status(500).entity("The provided ID is null.").build();
        }

        List<RealmCharacters> linkList = this.realmCharactersController.findByAccount(accountID);

        logger.debug("find() exit.");
        return Response.status(200).entity(linkList).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Retrieves all links in the database",
        description = "This API is retrieving all links from the database."
    )
    @APIResponses(
        value = {
            @APIResponse(responseCode = "200", description = "A list of RealmCharacters", content = @Content(
                        mediaType = "application/json",
                        schema = @Schema(implementation=RealmCharacters.class)
                )
            ),
            @APIResponse(responseCode = "400", description = "Error with the request"),
            @APIResponse(responseCode = "500", description = "An unexpected even occured")
        }
    )
    public List<RealmCharacters> findAllRealmCharacters() {
        logger.debug("findAll() entry.");
        List<RealmCharacters> listRealmCharacters = this.realmCharactersController.findAll();

        logger.debug("findAll() exit.");
        return listRealmCharacters;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Operation(summary = "Create a new link between a realm and an account",
        description = "This API is creating a new link based on the provided input."
    )
    @APIResponses(
        value = {
            @APIResponse(responseCode = "201", description = "The link has been created", content = @Content(
                        mediaType = "application/json",
                        schema = @Schema(implementation=RealmCharacters.class)
                )
            ),
            @APIResponse(responseCode = "400", description = "Error with the request"),
            @APIResponse(responseCode = "500", description = "An unexpected even occured")
        }
    )
    public Response addRealmCharacters(RealmCharacters entity) {
        try {
                this.realmCharactersController.create(entity);
        } catch (DAOException daoEx) {
                return Response.status(400).entity(daoEx.getMessage()).build();
        } catch (Exception ex) {
                return Response.status(500).entity(ex.getMessage()).build();
        }
        return Response.status(201).entity("Link has been created.").build();
    }

    @PUT
    @Path("/account/{accountID}/realm/{realmID}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Operation(summary = "Edit a link",
        description = "This API is updating an existing link based on the provided input."
    )
    @APIResponses(
        value = {
            @APIResponse(responseCode = "200", description = "The link has been updated", content = @Content(
                        mediaType = "application/json",
                        schema = @Schema(implementation=RealmCharacters.class)
                )
            ),
            @APIResponse(responseCode = "400", description = "Error with the request"),
            @APIResponse(responseCode = "404", description = "Link not found"),
            @APIResponse(responseCode = "500", description = "An unexpected even occured")
        }
    )
    public Response editRealmCharacters(@PathParam("accountID") Integer accountID, @PathParam("realmID") Integer realmID, RealmCharacters entity) {
        try {
                entity.setId(new RealmCharactersID(accountID, realmID));
                this.realmCharactersController.update(entity);
        } catch (DAOException daoEx) {
                return Response.status(404).entity(daoEx.getMessage()).build();
        } catch (Exception ex) {
                return Response.status(500).entity(ex.getMessage()).build();
        }
        return Response.status(200).entity("Link has been updated.").build();
    }

    @DELETE
    @Path("/account/{accountID}/realm/{realmID}")
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Delete a link",
        description = "This API is deleting an existing link based on the provided id."
    )
    @APIResponses(
        value = {
            @APIResponse(responseCode = "204", description = "The link has been deleted", content = @Content(
                        mediaType = "application/json",
                        schema = @Schema(implementation=RealmCharacters.class)
                )
            ),
            @APIResponse(responseCode = "400", description = "Error with the request"),
            @APIResponse(responseCode = "404", description = "Link not found"),
            @APIResponse(responseCode = "500", description = "An unexpected even occured")
        }
    )
    public Response deleteRealmList(@PathParam("accountID") Integer accountID, @PathParam("realmID") Integer realmID) {
        try {
                this.realmCharactersController.delete(new RealmCharactersID(realmID, accountID));
        } catch (DAOException daoEx) {
                return Response.status(404).entity(daoEx.getMessage()).build();
        } catch (Exception ex) {
                return Response.status(500).entity(ex.getMessage()).build();
        }
        return Response.status(204).build();
    }

}
