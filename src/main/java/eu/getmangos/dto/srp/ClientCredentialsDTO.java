package eu.getmangos.dto.srp;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter @AllArgsConstructor
public class ClientCredentialsDTO implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private String username;

    private String A;

    private String M1;

    private String M2;

    private String sessionKey;
}
