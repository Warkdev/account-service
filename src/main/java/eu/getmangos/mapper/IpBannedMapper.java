package eu.getmangos.mapper;

import java.util.Date;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import eu.getmangos.dto.IpBannedDTO;
import eu.getmangos.entities.IpBanned;

@Mapper(componentModel = "cdi")
public interface IpBannedMapper {

    @Mapping(source = "ban.id.ip", target = "ip")
    @Mapping(source = "ban.id.banDate", target = "banDate")
    IpBannedDTO map(IpBanned ban);

    @InheritInverseConfiguration
    IpBanned map(IpBannedDTO ban);

    default Date map(Long value) {
        return new Date(value);
    }

    default Long map(Date date) {
        return date.getTime();
    }
}
