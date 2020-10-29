package eu.getmangos.rest;

import org.eclipse.microprofile.openapi.annotations.OpenAPIDefinition;
import org.eclipse.microprofile.openapi.annotations.info.Info;
import org.eclipse.microprofile.openapi.annotations.info.License;

@OpenAPIDefinition(
    info = @Info(
            title = "Mangos Auth API",
            version = "1.0",
            description = "API allowing to interact with the Mangos Auth Database",
            license = @License(
                    name = "Apache 2.0"
            )
    )
)
public class JAXRSApp {
    
}
