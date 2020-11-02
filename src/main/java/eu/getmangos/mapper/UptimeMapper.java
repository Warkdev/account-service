package eu.getmangos.mapper;

import org.mapstruct.Mapper;

import eu.getmangos.dto.UptimeDTO;
import eu.getmangos.entities.Uptime;

@Mapper(componentModel = "cdi")
public interface UptimeMapper {

    UptimeDTO uptimeToDTO(Uptime u);

    Uptime dtoToEntity(UptimeDTO u);
}
