package eu.getmangos.mapper;

import java.util.Date;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import eu.getmangos.dto.BansDTO;
import eu.getmangos.entities.AccountBanned;

@Mapper(componentModel = "cdi")
public interface BanMapper {

    @Mapping(source = "ban.accountBannedPK.id", target = "id")
    @Mapping(source = "ban.accountBannedPK.banDate", target = "date")
    BansDTO map(AccountBanned ban);

    @InheritInverseConfiguration
    AccountBanned map(BansDTO ban);

    default Date map(Long value) {
        return new Date(value);
    }

    default Long map(Date date) {
        return date.getTime();
    }
}
