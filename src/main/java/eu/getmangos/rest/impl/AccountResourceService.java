package eu.getmangos.rest.impl;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import org.slf4j.Logger;

import eu.getmangos.controllers.AccountController;
import eu.getmangos.controllers.DAOException;
import eu.getmangos.dto.AccountDTO;
import eu.getmangos.dto.MessageDTO;
import eu.getmangos.dto.srp.RegistrationDTO;
import eu.getmangos.entities.Account;
import eu.getmangos.mapper.AccountMapper;
import eu.getmangos.rest.AccountResource;

@ApplicationScoped
@Path("")
public class AccountResourceService implements AccountResource {

    @Inject private Logger logger;

    @Inject private AccountController controller;

    @Inject private AccountMapper mapper;

    public Response findAccount(Integer id) {
        logger.debug("find() entry.");

        if (id == null) {
                return Response.status(500).entity("The provided ID is null.").build();
        }

        AccountDTO account = mapper.accountToDTO(controller.find(id));

        if(account == null) {
                return Response.status(404).entity(new MessageDTO("The provided ID has no match in the database.")).build();
        }

        logger.debug("find() exit.");
        return Response.status(200).entity(account).build();
    }

    public Response findAllAccounts() {
        logger.debug("findAll() entry.");

        List<AccountDTO> listAccounts = new ArrayList<>();
        for(Account a : this.controller.findAll()) {
            listAccounts.add(mapper.accountToDTO(a));
        }

        logger.debug("findAll() exit.");
        return Response.status(200).entity(listAccounts).build();
    }

    @Override
    public Response register(RegistrationDTO account) {
        try {
            this.controller.register(mapper.map(account));
        } catch (DAOException daoEx) {
            return Response.status(400).entity(new MessageDTO(daoEx.getMessage())).build();
        } catch (Exception ex) {
            return Response.status(500).entity(new MessageDTO(ex.getMessage())).build();
        }
        return Response.status(201).entity(new MessageDTO("Account has been created.")).build();
    }

    public Response editAccount(Integer id, AccountDTO entity) {
        try {
                entity.setId(id);
                this.controller.update(mapper.map(entity));
        } catch (DAOException daoEx) {
                return Response.status(404).entity(new MessageDTO(daoEx.getMessage())).build();
        } catch (Exception ex) {
                return Response.status(500).entity(new MessageDTO(ex.getMessage())).build();
        }
        return Response.status(200).entity("Account has been updated.").build();
    }

    public Response deleteAccount(Integer id) {
        try {
                this.controller.delete(id);
        } catch (DAOException daoEx) {
                return Response.status(404).entity(new MessageDTO(daoEx.getMessage())).build();
        } catch (Exception ex) {
                return Response.status(500).entity(new MessageDTO(ex.getMessage())).build();
        }
        return Response.status(204).build();
    }

    @Override
    public Response challenge(String username) {
        logger.debug("challenge() entry.");

        logger.debug("challenge() exit.");
        return Response.status(200).build();
    }

}
