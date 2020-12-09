package eu.getmangos.rest.impl;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import org.slf4j.Logger;

import eu.getmangos.dao.AccountDAO;
import eu.getmangos.dao.DAOException;
import eu.getmangos.dto.AccountDTO;
import eu.getmangos.dto.MessageDTO;
import eu.getmangos.dto.srp.RegistrationDTO;
import eu.getmangos.mapper.AccountMapper;
import eu.getmangos.rest.AccountResource;

@ApplicationScoped
@Path("")
public class AccountResourceService implements AccountResource {

    @Inject private Logger logger;

    @Inject private AccountDAO controller;

    @Inject private AccountMapper mapper;

    public Response findAccount(String email) {
        logger.debug("find() entry.");

        if (email == null || email.isEmpty()){
                return Response.status(400).entity("The provided email is null.").build();
        }

        AccountDTO account = mapper.map(controller.searchByEmail(email));

        if(account == null) {
                return Response.status(404).entity(new MessageDTO("The provided email has no match in the database.")).build();
        }

        logger.debug("find() exit.");
        return Response.status(200).entity(account).build();
    }

    public Response findBy(Integer page, Integer pageSize) {
        logger.debug("findBy() entry.");

        List<AccountDTO> listAccounts = new ArrayList<>();

        try {
            listAccounts = mapper.map(this.controller.findAll(page, pageSize));
        } catch (IllegalArgumentException iae) {
            logger.error("Error while retrieving the data from the database: "+iae.getMessage());
            return Response.status(400).entity(new MessageDTO("Something went wrong with your request, check your query filter.")).build();
        }

        logger.debug("findBy() exit.");
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

    public Response editAccount(String email, AccountDTO entity) {
        try {
                entity.setEmail(email);
                this.controller.update(mapper.map(entity));
        } catch (DAOException daoEx) {
                return Response.status(404).entity(new MessageDTO(daoEx.getMessage())).build();
        } catch (Exception ex) {
                return Response.status(500).entity(new MessageDTO(ex.getMessage())).build();
        }
        return Response.status(200).entity("Account has been updated.").build();
    }

    public Response deleteAccount(String email) {
        try {
                this.controller.delete(email);
        } catch (DAOException daoEx) {
                return Response.status(404).entity(new MessageDTO(daoEx.getMessage())).build();
        } catch (Exception ex) {
                return Response.status(500).entity(new MessageDTO(ex.getMessage())).build();
        }
        return Response.status(204).build();
    }

}
