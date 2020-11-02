package eu.getmangos.mapper;

import org.mapstruct.Mapper;

import eu.getmangos.dto.IpBannedDTO;
import eu.getmangos.entities.IpBanned;

@Mapper(componentModel = "cdi")
public interface IpBannedMapper {

    IpBannedDTO banToDTO(IpBanned ban);

    IpBanned dtoToEntity(IpBannedDTO ban);
}
