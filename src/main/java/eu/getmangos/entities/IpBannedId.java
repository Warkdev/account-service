package eu.getmangos.entities;

import java.io.Serializable;

import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.Data;

@Embeddable
@Data @AllArgsConstructor
public class IpBannedId implements Serializable {
    private static final long serialVersionUID = 1L;

    private String ip;
    private long banDate;

}
