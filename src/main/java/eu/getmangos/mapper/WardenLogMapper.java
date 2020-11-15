package eu.getmangos.mapper;

import org.mapstruct.Mapper;

import eu.getmangos.dto.WardenActions;
import eu.getmangos.dto.WardenLogDTO;
import eu.getmangos.entities.WardenLog;

@Mapper(componentModel = "cdi")
public interface WardenLogMapper {

    WardenLogDTO logToDTO(WardenLog log);

    WardenLog dtoToEntity(WardenLogDTO dto);

    default WardenActions map(short value) {
        return WardenActions.convert(value);
    }

    default short mapPrim(WardenActions action) {
        return (short) action.code;
    }
}
