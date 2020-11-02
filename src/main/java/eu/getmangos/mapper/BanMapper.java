package eu.getmangos.mapper;

import org.mapstruct.Mapper;

import eu.getmangos.dto.BansDTO;
import eu.getmangos.entities.AccountBanned;

@Mapper(componentModel = "cdi")
public interface BanMapper {

    BansDTO banToDTO(AccountBanned ban);

    AccountBanned dtoToEntity(BansDTO ban);
}
