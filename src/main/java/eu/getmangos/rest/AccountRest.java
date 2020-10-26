package eu.getmangos.rest;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.slf4j.Logger;

import eu.getmangos.controllers.AccountController;
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
        logger.debug("Provided ID: "+id);

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
        this.accountController.create(entity);
        return Response.status(200).entity("Account has been created.").build();
    }

}
