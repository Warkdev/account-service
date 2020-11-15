package eu.getmangos.rest;

import javax.ws.rs.ApplicationPath;

import org.eclipse.microprofile.openapi.annotations.ExternalDocumentation;
import org.eclipse.microprofile.openapi.annotations.OpenAPIDefinition;
import org.eclipse.microprofile.openapi.annotations.info.Info;
import org.eclipse.microprofile.openapi.annotations.info.License;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

@ApplicationPath("/account")
@OpenAPIDefinition(
    tags = {
        @Tag(name = "account", description="Operations about accounts"),
        @Tag(name = "authentication", description="Operations about authentication"),
        @Tag(name = "ban", description="Operations about bans"),
        @Tag(name = "ban ip", description="Operations about bans IP"),
        @Tag(name = "warden", description="Operations about warden logs")
    },
    externalDocs = @ExternalDocumentation(
        description = "Instructions on how to deploy this WebApp",
        url = "https://github.com/Warkdev/account-service/blob/master/README.md"
    ),
    info = @Info(
            title = "Mangos Account API",
            version = "1.0",
            description = "API allowing to interact with Mangos Accounts",
            license = @License(
                    name = "Apache 2.0"
            )
    )
)
public class OpenAPIConfig {
}
