package eu.getmangos.dto.srp;

import org.eclipse.microprofile.openapi.annotations.media.Schema;

import eu.getmangos.dto.Expansion;
import eu.getmangos.dto.Locale;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class RegistrationDTO {
    @Schema(description = "An username for this account")
    private String username;

    @Schema(description = "An email address for this account. Must be of a valid format")
    private String email;

    @Schema(description = "An enum value indicating the expansion until which this account has access")
    private Expansion expansion;

    @Schema(description = "The locale for this account")
    private Locale locale;

    @Schema(description = "The generated salt for the registration")
    private String salt;

    @Schema(description = "The generated verifier for the registration")
    private String verifier;
}
