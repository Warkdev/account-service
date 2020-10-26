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

import eu.getmangos.controllers.AccountController;
import eu.getmangos.controllers.DAOException;
import eu.getmangos.entities.Account;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@Stateless
@Path("/account/v1")
@OpenAPIDefinition(
    info = @Info(
            title = "Account API",
            version = "1.0",
            description = "Account API is used to retrieve account information",
            license = @License(
                    name = "Apache 2.0"
            )
    )
)
public class AccountRest {

    @Inject private Logger logger;

    @Inject private AccountController accountController;

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Retrieves an account given the id",
        description = "This API is retrieving the account with the given from the database.",
        responses = {
            @ApiResponse(responseCode = "200", description = "The account", content = @Content(
                        mediaType = "application/json",
                        schema = @Schema(implementation=Account.class)
                )
        ),
            @ApiResponse(responseCode = "400", description = "Error with the request"),
            @ApiResponse(responseCode = "500", description = "An unexpected event occured")
        }
    )
    public Response find(@PathParam("id") Integer id) {
        logger.debug("find() entry.");

        if (id == null) {
                return Response.status(500).entity("The provided ID is null.").build();
        }

        Account account = this.accountController.find(id);

        if(account == null) {
                return Response.status(400).entity("The provided ID has no match in the database.").build();
        }

        logger.debug("find() exit.");
        return Response.status(200).entity(account).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Retrieves all accounts",
        description = "This API is retrieving all accounts from the database.",
        responses = {
            @ApiResponse(responseCode = "200", description = "A list of accounts", content = @Content(
                        mediaType = "application/json",
                        schema = @Schema(implementation=Account.class)
                )
        ),
            @ApiResponse(responseCode = "400", description = "Error with the request"),
            @ApiResponse(responseCode = "500", description = "An unexpected even occured")
        }
    )
    public List<Account> findAll() {
        logger.debug("findAll() entry.");
        List<Account> listAccounts = this.accountController.findAll();

        logger.debug("findAll() exit.");
        return listAccounts;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Operation(summary = "Create a new account",
        description = "This API is creating a new account based on the provided input.",
        responses = {
            @ApiResponse(responseCode = "200", description = "The account has been created", content = @Content(
                        mediaType = "application/json",
                        schema = @Schema(implementation=Account.class)
                )
        ),
            @ApiResponse(responseCode = "400", description = "Error with the request"),
            @ApiResponse(responseCode = "500", description = "An unexpected even occured")
        }
    )
    public Response add(Account entity) {
        try {
                this.accountController.create(entity);
        } catch (DAOException daoEx) {
                return Response.status(400).entity(daoEx.getMessage()).build();
        } catch (Exception ex) {
                return Response.status(500).entity(ex.getMessage()).build();
        }
        return Response.status(200).entity("Account has been created.").build();
    }

    @PUT
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Operation(summary = "Edit an account",
        description = "This API is updating an existing account based on the provided input.",
        responses = {
            @ApiResponse(responseCode = "200", description = "The account has been updated", content = @Content(
                        mediaType = "application/json",
                        schema = @Schema(implementation=Account.class)
                )
        ),
            @ApiResponse(responseCode = "400", description = "Error with the request"),
            @ApiResponse(responseCode = "500", description = "An unexpected even occured")
        }
    )
    public Response edit(@PathParam("id") Integer id, Account entity) {
        try {
                entity.setId(id);
                this.accountController.update(entity);
        } catch (DAOException daoEx) {
                return Response.status(400).entity(daoEx.getMessage()).build();
        } catch (Exception ex) {
                return Response.status(500).entity(ex.getMessage()).build();
        }
        return Response.status(200).entity("Account has been updated.").build();
    }

    @DELETE
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Delete an account",
        description = "This API is deleting an existing account based on the provided id.",
        responses = {
            @ApiResponse(responseCode = "200", description = "The account has been deleted", content = @Content(
                        mediaType = "application/json",
                        schema = @Schema(implementation=Account.class)
                )
        ),
            @ApiResponse(responseCode = "400", description = "Error with the request"),
            @ApiResponse(responseCode = "500", description = "An unexpected even occured")
        }
    )
    public Response delete(@PathParam("id") Integer id) {
        try {
                this.accountController.delete(id);
        } catch (DAOException daoEx) {
                return Response.status(400).entity(daoEx.getMessage()).build();
        } catch (Exception ex) {
                return Response.status(500).entity(ex.getMessage()).build();
        }
        return Response.status(200).entity("Account has been deleted.").build();
    }

}
