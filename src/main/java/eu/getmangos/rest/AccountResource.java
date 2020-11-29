package eu.getmangos.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
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

import eu.getmangos.dto.AccountDTO;
import eu.getmangos.dto.srp.RegistrationDTO;
import eu.getmangos.dto.srp.ServerCredentialsDTO;

public interface AccountResource {

    @GET
    @Path("v1/accounts/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Retrieves an account given the id",
        description = "This API is retrieving the account with the given from the database."
    )
    @APIResponses(
        value = {
            @APIResponse(responseCode = "200", description = "The account", content = @Content(
                        mediaType = "application/json", schema = @Schema(implementation = AccountDTO.class)
                )
            ),
            @APIResponse(responseCode = "400", description = "Error with the request"),
            @APIResponse(responseCode = "404", description = "Account not found"),
            @APIResponse(responseCode = "500", description = "An unexpected event occured")
        }
    )
    @Tag(name = "account")
    public Response findAccount(@PathParam("id") Integer id);

    @GET
    @Path("v1/accounts")
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Retrieves all accounts",
        description = "This API is retrieving all accounts from the database."
    )
    @APIResponses(
        value = {
            @APIResponse(responseCode = "200", description = "A list of accounts", content = @Content(
                        mediaType = "application/json", schema = @Schema(implementation = AccountDTO.class, description = "A list of accounts")
                )
            ),
            @APIResponse(responseCode = "400", description = "Error with the request"),
            @APIResponse(responseCode = "500", description = "An unexpected even occured")
        }
    )
    @Tag(name = "account")
    public Response findAllAccounts();

    @POST
    @Path("v1/accounts")
    @Consumes(MediaType.APPLICATION_JSON)
    @Operation(summary = "Register a new account",
        description = "This API is registering a new account based on the provided input."
    )
    @APIResponses(
        value = {
            @APIResponse(responseCode = "201", description = "The account has been registered", content = @Content(
                        mediaType = "application/json"
                )
            ),
            @APIResponse(responseCode = "400", description = "Error with the request"),
            @APIResponse(responseCode = "500", description = "An unexpected even occured")
        }
    )
    @Tag(name = "account")
    public Response register(RegistrationDTO account);

    @PUT
    @Path("v1/accounts/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Operation(summary = "Edit an account",
        description = "This API is updating an existing account based on the provided input."
    )
    @APIResponses(
        value = {
            @APIResponse(responseCode = "200", description = "The account has been updated", content = @Content(
                        mediaType = "application/json"
                )
            ),
            @APIResponse(responseCode = "400", description = "Error with the request"),
            @APIResponse(responseCode = "404", description = "Account not found"),
            @APIResponse(responseCode = "500", description = "An unexpected even occured")
        }
    )
    @Tag(name = "account")
    public Response editAccount(@PathParam("id") Integer id, AccountDTO entity);

    @DELETE
    @Path("v1/accounts/{id}")
    @Operation(summary = "Delete an account",
        description = "This API is deleting an existing account based on the provided id."
                + "It will also delete the bans for this account, the link with the realms and the warden logs"
    )
    @APIResponses(
        value = {
            @APIResponse(responseCode = "204", description = "The account has been deleted", content = @Content(
                        mediaType = "application/json"
                )
            ),
            @APIResponse(responseCode = "400", description = "Error with the request"),
            @APIResponse(responseCode = "404", description = "Account not found"),
            @APIResponse(responseCode = "500", description = "An unexpected even occured")
        }
    )
    @Tag(name = "account")
    public Response deleteAccount(@PathParam("id") Integer id);

    @GET
    @Path("v1/accounts/challenge/{username}")
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Challenge user according to SRP6a validation in order to process with authentication")
    @APIResponses(
        value = {
            @APIResponse(description = "A Credential object under the form of a JSON object providing the SRP6a challenge",
                        content = @Content( mediaType = "application/json",
                        schema = @Schema(implementation=ServerCredentialsDTO.class))),
                @APIResponse(responseCode = "404", description = "User not found")
        }
    )
    @Tag(name = "authentication")
    public Response challenge(@PathParam("username") String username);
}
