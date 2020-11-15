package eu.getmangos.rest;

import java.util.Date;
import java.util.List;

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
import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;

import eu.getmangos.dto.AccountDTO;
import eu.getmangos.dto.BansDTO;
import eu.getmangos.dto.IpBannedDTO;
import eu.getmangos.dto.WardenLogDTO;
import eu.getmangos.dto.srp.RegistrationDTO;
import eu.getmangos.dto.srp.ServerCredentialsDTO;

public interface AccountResource {

    @GET
    @Path("{id}")
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
    public List<AccountDTO> findAllAccounts();

    @POST
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
    @Path("{id}")
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
    @Path("{id}")
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
    @Path("bans/{id}/{bandate}")
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Retrieves a ban given the id",
        description = "This API is retrieving the ban with the given id/bandate from the database."
    )
    @APIResponses(
        value = {
            @APIResponse(responseCode = "200", description = "The ban", content = @Content(
                        mediaType = "application/json", schema = @Schema(implementation = BansDTO.class)
                )
            ),
            @APIResponse(responseCode = "400", description = "Error with the request"),
            @APIResponse(responseCode = "404", description = "Ban not found"),
            @APIResponse(responseCode = "500", description = "An unexpected event occured")
        }
    )
    @Tag(name = "ban")
    public Response findBan(@PathParam("id") Integer id, @PathParam("bandate") Date banDate);

    @GET
    @Path("bans")
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Retrieves all bans",
        description = "This API is retrieving all bans from the database."
    )
    @APIResponses(
        value = {
            @APIResponse(responseCode = "200", description = "A list of bans", content = @Content(
                        mediaType = "application/json", schema = @Schema(implementation = BansDTO.class)
                )
            ),
            @APIResponse(responseCode = "400", description = "Error with the request"),
            @APIResponse(responseCode = "500", description = "An unexpected even occured")
        }
    )
    @Tag(name = "ban")
    public Response findAllBans();

    @POST
    @Path("bans")
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
    @Tag(name = "ban")
    public Response addBan(BansDTO entity);

    @PUT
    @Path("bans/{id}/{bandate}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Operation(summary = "Edit a ban",
        description = "This API is updating an existing ban based on the provided input."
    )
    @APIResponses(
        value = {
            @APIResponse(responseCode = "200", description = "The ban has been updated", content = @Content(
                        mediaType = "application/json"
                )
            ),
            @APIResponse(responseCode = "400", description = "Error with the request"),
            @APIResponse(responseCode = "404", description = "Ban not found"),
            @APIResponse(responseCode = "500", description = "An unexpected even occured")
        }
    )
    @Tag(name = "ban")
    public Response editBan(@PathParam("id") Integer id, @PathParam("bandate") Date banDate, BansDTO entity);

    @DELETE
    @Path("bans/{id}/{bandate}")
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Delete a ban",
        description = "This API is deleting an existing ban based on the provided id."
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
    @Tag(name = "ban")
    public Response deleteBan(@PathParam("id") Integer id, @PathParam("bandate") Date banDate);

    @GET
    @Path("banip/{ip}/{bandate}")
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
    @Tag(name = "ban ip")
    public Response findIpBan(@PathParam("ip") @Parameter(
        description = "The IP address to search for",
        required = true,
        example = "127.0.0.1"
    ) String ip, @PathParam("bandate") @Parameter(
        description = "The ban date for this IP",
        required = true
    ) Date banDate);

    @GET
    @Path("banip")
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
    @Tag(name = "ban ip")
    public Response findAllIpBans();

    @POST
    @Path("banip")
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
    @Tag(name = "ban ip")
    public Response addIpBan(IpBannedDTO entity);

    @PUT
    @Path("banip/{ip}/{bandate}")
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
    @Tag(name = "ban ip")
    public Response editIpBan(@PathParam("ip") String ip, @PathParam("bandate") Date banDate, IpBannedDTO entity);

    @DELETE
    @Path("banip/{ip}/{bandate}")
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
    @Tag(name = "ban ip")
    public Response deleteIpBan(@PathParam("ip") String ip, @PathParam("bandate") Date banDate);

    @GET
    @Path("/challenge/{username}")
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

    @GET
    @Path("/warden/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Retrieves all warden log data for a given account",
        description = "This API is retrieving the warden log data from the database for a given account."
    )
    @APIResponses(
        value = {
            @APIResponse(responseCode = "200", description = "The warden logs", content = @Content(
                        mediaType = "application/json", schema = @Schema(implementation = WardenLogDTO.class)
                )
            ),
            @APIResponse(responseCode = "400", description = "Error with the request"),
            @APIResponse(responseCode = "500", description = "An unexpected even occured")
        }
    )
    @Tag(name = "warden")
    public Response getWardenLogsForAccount(@PathParam("id") Integer accountId);

    @GET
    @Path("warden")
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Retrieves all warden logs data",
        description = "This API is retrieving the warden logs data from the database."
    )
    @APIResponses(
        value = {
            @APIResponse(responseCode = "200", description = "The warden logs", content = @Content(
                        mediaType = "application/json", schema = @Schema(implementation = WardenLogDTO.class)
                )
            ),
            @APIResponse(responseCode = "400", description = "Error with the request"),
            @APIResponse(responseCode = "500", description = "An unexpected even occured")
        }
    )
    @Tag(name = "warden")
    public Response getAllWardenLogs();


}
