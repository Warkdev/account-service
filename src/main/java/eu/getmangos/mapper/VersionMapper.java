package eu.getmangos.mapper;

import org.mapstruct.Mapper;

import eu.getmangos.dto.DbVersionDTO;
import eu.getmangos.entities.DbVersion;

@Mapper(componentModel = "cdi")
public interface VersionMapper {

    DbVersionDTO versionToDTO(DbVersion version);

    DbVersion dtoToEntity(DbVersionDTO version);

}
