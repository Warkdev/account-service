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

import org.eclipse.microprofile.openapi.annotations.OpenAPIDefinition;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.info.Info;
import org.eclipse.microprofile.openapi.annotations.info.License;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;
import org.slf4j.Logger;

import eu.getmangos.controllers.DbVersionController;
import eu.getmangos.controllers.DAOException;
import eu.getmangos.entities.DbVersion;

@RequestScoped
@Path("/version/v1")
@OpenAPIDefinition(
    info = @Info(
            title = "Version API",
            version = "1.0",
            description = "Version API is used to retrieve database version information",
            license = @License(
                    name = "Apache 2.0"
            )
    )
)
public class DbVersionRest {

    @Inject private Logger logger;

    @Inject private DbVersionController dbVersionController;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Retrieves the database version",
        description = "This API is retrieving the database version from the database."
    )
    @APIResponses(
        value = {
            @APIResponse(responseCode = "200", description = "The database version", content = @Content(
                        mediaType = "application/json",
                        schema = @Schema(implementation=DbVersion.class)
                )
            ),
            @APIResponse(responseCode = "400", description = "Error with the request"),
            @APIResponse(responseCode = "500", description = "An unexpected even occured")
        }
    )
    public DbVersion getDbVersion() {
        logger.debug("getDbVersion() entry.");
        DbVersion version = this.dbVersionController.getVersion();

        logger.debug("getDbVersion() exit.");
        return version;
    }
}
