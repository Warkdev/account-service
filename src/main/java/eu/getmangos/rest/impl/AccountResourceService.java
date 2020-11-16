package eu.getmangos.rest.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import org.slf4j.Logger;

import eu.getmangos.controllers.AccountBannedController;
import eu.getmangos.controllers.AccountController;
import eu.getmangos.controllers.DAOException;
import eu.getmangos.controllers.IpBannedController;
import eu.getmangos.controllers.WardenController;
import eu.getmangos.dto.AccountDTO;
import eu.getmangos.dto.BansDTO;
import eu.getmangos.dto.CleanupResultDTO;
import eu.getmangos.dto.IpBannedDTO;
import eu.getmangos.dto.WardenLogDTO;
import eu.getmangos.dto.srp.RegistrationDTO;
import eu.getmangos.entities.Account;
import eu.getmangos.entities.AccountBanned;
import eu.getmangos.entities.AccountBannedId;
import eu.getmangos.entities.IpBanned;
import eu.getmangos.entities.IpBannedId;
import eu.getmangos.entities.WardenLog;
import eu.getmangos.mapper.AccountMapper;
import eu.getmangos.mapper.BanMapper;
import eu.getmangos.mapper.IpBannedMapper;
import eu.getmangos.mapper.WardenLogMapper;
import eu.getmangos.rest.AccountResource;

@ApplicationScoped
@Path("v1")
public class AccountResourceService implements AccountResource {

    @Inject private Logger logger;

    @Inject private AccountController accountController;
    @Inject private AccountBannedController accountBannedController;
    @Inject private IpBannedController ipBannedController;
    @Inject private WardenController wardenController;

    @Inject private AccountMapper accountMapper;
    @Inject private BanMapper banMapper;
    @Inject private IpBannedMapper ipMapper;
    @Inject private WardenLogMapper wardenMapper;

    public Response findAccount(Integer id) {
        logger.debug("find() entry.");

        if (id == null) {
                return Response.status(500).entity("The provided ID is null.").build();
        }

        AccountDTO account = accountMapper.accountToDTO(accountController.find(id));

        if(account == null) {
                return Response.status(404).entity("The provided ID has no match in the database.").build();
        }

        logger.debug("find() exit.");
        return Response.status(200).entity(account).build();
    }

    public Response findAllAccounts() {
        logger.debug("findAll() entry.");

        List<AccountDTO> listAccounts = new ArrayList<>();
        for(Account a : this.accountController.findAll()) {
            listAccounts.add(accountMapper.accountToDTO(a));
        }

        logger.debug("findAll() exit.");
        return Response.status(200).entity(listAccounts).build();
    }

    @Override
    public Response register(RegistrationDTO account) {
        try {
            this.accountController.register(accountMapper.dtoToEntity(account));
        } catch (DAOException daoEx) {
            return Response.status(400).entity(daoEx.getMessage()).build();
        } catch (Exception ex) {
            return Response.status(500).entity(ex.getMessage()).build();
        }
        return Response.status(201).entity("Account has been created.").build();
    }

    public Response editAccount(Integer id, AccountDTO entity) {
        try {
                entity.setId(id);
                this.accountController.update(accountMapper.dtoToEntity(entity));
        } catch (DAOException daoEx) {
                return Response.status(404).entity(daoEx.getMessage()).build();
        } catch (Exception ex) {
                return Response.status(500).entity(ex.getMessage()).build();
        }
        return Response.status(200).entity("Account has been updated.").build();
    }

    public Response deleteAccount(Integer id) {
        try {
                this.accountController.delete(id);
        } catch (DAOException daoEx) {
                return Response.status(404).entity(daoEx.getMessage()).build();
        } catch (Exception ex) {
                return Response.status(500).entity(ex.getMessage()).build();
        }
        return Response.status(204).build();
    }

    public Response findBan(Integer id, Date banDate) {
        logger.debug("find() entry.");

        if (id == null) {
                return Response.status(500).entity("The provided ID is null.").build();
        }

        BansDTO ban = banMapper.banToDTO(accountBannedController.find(new AccountBannedId(id, banDate.getTime())));

        if(ban == null) {
                return Response.status(404).entity("The provided ID has no match in the database.").build();
        }

        logger.debug("find() exit.");
        return Response.status(200).entity(ban).build();
    }

    public Response findAllBans() {
        logger.debug("findAll() entry.");

        List<BansDTO> listAccounts = new ArrayList<>();

        for(AccountBanned ban : this.accountBannedController.findAll()){
            listAccounts.add(banMapper.banToDTO(ban));
        }

        logger.debug("findAll() exit.");
        return Response.status(200).entity(listAccounts).build();
    }

    public Response addBan(BansDTO entity) {
        try {
                this.accountBannedController.create(banMapper.dtoToEntity(entity));
        } catch (DAOException daoEx) {
                return Response.status(400).entity(daoEx.getMessage()).build();
        } catch (Exception ex) {
                return Response.status(500).entity(ex.getMessage()).build();
        }
        return Response.status(201).entity("Ban has been created.").build();
    }

    public Response editBan(Integer id, Date banDate, BansDTO entity) {
        try {
                AccountBanned ban = banMapper.dtoToEntity(entity);
                ban.setAccountBannedId(new AccountBannedId(id, banDate.getTime()));
                this.accountBannedController.update(ban);
        } catch (DAOException daoEx) {
                return Response.status(404).entity(daoEx.getMessage()).build();
        } catch (Exception ex) {
                return Response.status(500).entity(ex.getMessage()).build();
        }
        return Response.status(200).entity("Ban has been updated.").build();
    }

    public Response deleteBan(Integer id, Date banDate) {
        try {
                this.accountBannedController.delete(new AccountBannedId(id, banDate.getTime()));
        } catch (DAOException daoEx) {
                return Response.status(404).entity(daoEx.getMessage()).build();
        } catch (Exception ex) {
                return Response.status(500).entity(ex.getMessage()).build();
        }
        return Response.status(204).build();
    }

    public Response findIpBan(String ip, Date banDate) {
        logger.debug("find() entry.");

        if (ip == null || ip.isBlank()) {
                return Response.status(500).entity("The provided IP is null.").build();
        }

        IpBanned IpBanned = this.ipBannedController.find(new IpBannedId(ip, banDate.getTime()));

        if(IpBanned == null) {
                return Response.status(404).entity("The provided ID has no match in the database.").build();
        }

        logger.debug("find() exit.");
        return Response.status(200).entity(IpBanned).build();
    }

    public Response findAllIpBans() {
        logger.debug("findAll() entry.");
        List<IpBannedDTO> listBans = new ArrayList<>();

        for(IpBanned ban : this.ipBannedController.findAll()) {
            listBans.add(ipMapper.banToDTO(ban));
        }

        logger.debug("findAll() exit.");
        return Response.status(200).entity(listBans).build();
    }

    public Response addIpBan(IpBannedDTO entity) {
        try {
                this.ipBannedController.create(ipMapper.dtoToEntity(entity));
        } catch (DAOException daoEx) {
                return Response.status(400).entity(daoEx.getMessage()).build();
        } catch (Exception ex) {
                return Response.status(500).entity(ex.getMessage()).build();
        }
        return Response.status(201).entity("Ban has been created.").build();
    }

    public Response editIpBan(String ip, Date banDate, IpBannedDTO entity) {
        try {
                IpBanned ban = ipMapper.dtoToEntity(entity);
                ban.setId(new IpBannedId(ip, banDate.getTime()));
                this.ipBannedController.update(ban);
        } catch (DAOException daoEx) {
                return Response.status(404).entity(daoEx.getMessage()).build();
        } catch (Exception ex) {
                return Response.status(500).entity(ex.getMessage()).build();
        }
        return Response.status(200).entity("Ban has been updated.").build();
    }

    public Response deleteIpBan(String ip, Date banDate) {
        try {
                this.ipBannedController.delete(new IpBannedId(ip, banDate.getTime()));
        } catch (DAOException daoEx) {
                return Response.status(404).entity(daoEx.getMessage()).build();
        } catch (Exception ex) {
                return Response.status(500).entity(ex.getMessage()).build();
        }
        return Response.status(204).build();
    }

    @Override
    public Response challenge(String username) {
        logger.debug("challenge() entry.");

        logger.debug("challenge() exit.");
        return Response.status(200).build();
    }

    @Override
    public Response getWardenLogsForAccount(Integer accountId) {
        logger.debug("getWardenLogsForAccount() entry.");

        if(accountId == null) {
            return Response.status(500).entity("The provided ID is null.").build();
        }

        List<WardenLogDTO> logList = new ArrayList<>();

        for(WardenLog log : this.wardenController.getWardenLogForAccount(accountId)) {
            logList.add(wardenMapper.logToDTO(log));
        }

        logger.debug("getWardenLogsForAccount() exit.");
        return Response.status(200).entity(logList).build();
    }

    @Override
    public Response getAllWardenLogs() {
        logger.debug("getAllWardenLogs() entry.");

        List<WardenLogDTO> logList = new ArrayList<>();

        for(WardenLog log : this.wardenController.getAllLogs()) {
            logList.add(wardenMapper.logToDTO(log));
        }

        logger.debug("getAllWardenLogs() exit.");
        return Response.status(200).entity(logList).build();
    }

    @Override
    public Response cleanupWardenLogs() {
        logger.debug("deleteWardenLogs() entry.");

        CleanupResultDTO result = new CleanupResultDTO();
        result.setRecords(wardenController.cleanupDeadLinks());

        logger.debug("deleteWardenLogs() exit.");
        return Response.status(200).entity(result).build();
    }

}
