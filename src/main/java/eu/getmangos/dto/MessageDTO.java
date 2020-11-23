package eu.getmangos.dto;

import org.eclipse.microprofile.openapi.annotations.media.Schema;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter @AllArgsConstructor @EqualsAndHashCode
public class MessageDTO {

    @Schema(description = "Message sent by the API to the requestor")
    private String message;
}
