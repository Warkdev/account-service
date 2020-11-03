package eu.getmangos.rest;

import javax.ws.rs.ApplicationPath;

import org.eclipse.microprofile.openapi.annotations.ExternalDocumentation;
import org.eclipse.microprofile.openapi.annotations.OpenAPIDefinition;
import org.eclipse.microprofile.openapi.annotations.info.Info;
import org.eclipse.microprofile.openapi.annotations.info.License;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

@ApplicationPath("/")
@OpenAPIDefinition(
    tags = {
        @Tag(name = "account", description="Operations about accounts"),
        @Tag(name = "ban", description="Operations about bans (accounts and IPs)"),
        @Tag(name = "version", description="Operations about database version"),
        @Tag(name = "realm", description="Operations about realms"),
        @Tag(name = "status", description="Operations about realms status"),
        @Tag(name = "warden", description="Operations about warden logfiles"),
        @Tag(name = "operation", description="Maintenance operations on the database")
    },
    externalDocs = @ExternalDocumentation(
        description = "Instructions on how to deploy this WebApp",
        url = "https://github.com/Warkdev/zero-auth-db/blob/master/README.md"
    ),
    info = @Info(
            title = "Mangos Auth API",
            version = "1.0",
            description = "API allowing to interact with the Mangos Auth Database",
            license = @License(
                    name = "Apache 2.0"
            )
    )
)
public class OpenAPIConfig {
}
