package eu.getmangos.mapper;

import org.mapstruct.Mapper;

import eu.getmangos.dto.LinksDTO;
import eu.getmangos.entities.RealmCharacters;

@Mapper(componentModel = "cdi")
public interface LinksMapper {

    LinksDTO linkToDTO(RealmCharacters link);

    RealmCharacters dtoToEntity(LinksDTO link);
}
