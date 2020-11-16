package eu.getmangos.dto;

import java.io.Serializable;

import org.apache.kafka.common.serialization.Serializer;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @ToString @EqualsAndHashCode
public class AccountEventDTO implements Serializable {

    public enum Event {
        ADD,
        DELETE,
        UPDATE;
    }

    /**
    *
    */
    private static final long serialVersionUID = 1L;

    private Integer id;

    private Event event;
}
