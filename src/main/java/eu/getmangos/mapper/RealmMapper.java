package eu.getmangos.mapper;

import org.mapstruct.Mapper;

import eu.getmangos.dto.RealmBuild;
import eu.getmangos.dto.RealmDTO;
import eu.getmangos.dto.RealmTimeZone;
import eu.getmangos.dto.RealmType;
import eu.getmangos.dto.SecurityLevel;
import eu.getmangos.entities.Realm;

@Mapper(componentModel = "cdi")
public interface RealmMapper {

    RealmDTO realmToDTO(Realm realm);

    Realm dtoToEntity(RealmDTO realm);

    default SecurityLevel mapSecLevel(int level) {
        return SecurityLevel.convert(level);
    }

    default short mapSecLevelPrim(SecurityLevel level) {
        return (short) level.level;
    }

    default RealmType mapType(int type) {
        return RealmType.convert(type);
    }

    default short mapTypePriv(RealmType type)  {
        return (short) type.type;
    }

    default RealmTimeZone mapZone(int zone) {
        return RealmTimeZone.convert(zone);
    }

    default short mapZonePrim(RealmTimeZone zone) {
        return (short) zone.zone;
    }

    default RealmBuild mapBuild(String build) {
        return RealmBuild.convert(build);
    }

    default String mapBuildStr(RealmBuild build) {
        return build.build;
    }
}
