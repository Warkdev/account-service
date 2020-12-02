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

import eu.getmangos.dto.srp.ServerCredentialsDTO;

import org.eclipse.microprofile.openapi.annotations.media.Schema;

public interface AuthenticateResource {

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
