package eu.getmangos.mapper;

import java.util.Date;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import eu.getmangos.dto.BansDTO;
import eu.getmangos.entities.AccountBanned;

@Mapper(componentModel = "cdi")
public interface BanMapper {

    @Mapping(source = "ban.accountBannedId.id", target = "id")
    @Mapping(source = "ban.accountBannedId.banDate", target = "date")
    BansDTO banToDTO(AccountBanned ban);

    @InheritInverseConfiguration
    AccountBanned dtoToEntity(BansDTO ban);

    default Date map(Long value) {
        return new Date(value);
    }

    default Long map(Date date) {
        return date.getTime();
    }
}
