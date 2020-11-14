package eu.getmangos.mapper;

import java.util.Date;

import org.mapstruct.Mapper;

import eu.getmangos.dto.AccountDTO;
import eu.getmangos.dto.Expansion;
import eu.getmangos.dto.Locale;
import eu.getmangos.dto.SecurityLevel;
import eu.getmangos.entities.Account;

@Mapper(componentModel = "cdi")
public interface AccountMapper {

    AccountDTO accountToDTO(Account account);

    Account dtoToEntity(AccountDTO dto);

    default Date map(Long value) {
        return new Date(value);
    }

    default Long map(Date date) {
        return date.getTime();
    }

    default SecurityLevel mapSecLevel(int level) {
        return SecurityLevel.convert(level);
    }

    default short mapSecLevelPrim(SecurityLevel level) {
        return (short) level.level;
    }

    default Expansion mapExp(int exp) {
        return Expansion.convert(exp);
    }

    default short mapExpPrim(Expansion exp) {
        return (short) exp.exp;
    }

    default Locale mapLocale(int loc) {
        return Locale.convert(loc);
    }

    default short mapLocalePrim(Locale loc) {
        return (short) loc.loc;
    }
}
