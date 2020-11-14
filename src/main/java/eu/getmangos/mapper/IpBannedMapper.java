package eu.getmangos.mapper;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import eu.getmangos.dto.IpBannedDTO;
import eu.getmangos.entities.IpBanned;

@Mapper(componentModel = "cdi")
public interface IpBannedMapper {

    @Mapping(source = "ban.id.ip", target = "ip")
    @Mapping(source = "ban.id.banDate", target = "banDate")
    IpBannedDTO banToDTO(IpBanned ban);

    @InheritInverseConfiguration
    IpBanned dtoToEntity(IpBannedDTO ban);
}
