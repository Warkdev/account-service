package eu.getmangos.rest;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.slf4j.Logger;

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
public class AccountRest extends AbstractFacade<Account> {

    @Inject private Logger logger;

    @PersistenceContext(unitName = "AUTH_PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    @GET
    @Override
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Retrieves all accounts",
            description = "This API is retrieving all accounts from the database.",
            responses = {
                @ApiResponse(description = "The user", content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation=Account.class)
                    )
            ),
                        @ApiResponse(responseCode = "400", description = "Error with the request")
            }
    )
    public List<Account> findAll() {
        return super.findAll();
    }

    public AccountRest() {
        super(Account.class);
    }

}
