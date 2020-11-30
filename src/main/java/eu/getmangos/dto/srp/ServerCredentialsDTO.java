package eu.getmangos.dto.srp;

import org.eclipse.microprofile.openapi.annotations.media.Schema;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class ServerCredentialsDTO {
    @Schema(description = "The salt for the authentication challenge")
    private String salt;

    @Schema(description = "The B value calculated by the server")
    private String B;
}
