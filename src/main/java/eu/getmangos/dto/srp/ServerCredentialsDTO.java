package eu.getmangos.dto.srp;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter @AllArgsConstructor
public class ServerCredentialsDTO {
    @NotNull
    private String salt;

    @NotNull
    private String B;
}
