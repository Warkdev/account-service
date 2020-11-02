package eu.getmangos.rest.impl;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import org.slf4j.Logger;

import eu.getmangos.controllers.AccountController;
import eu.getmangos.controllers.DAOException;
import eu.getmangos.dto.AccountDTO;
import eu.getmangos.entities.Account;
import eu.getmangos.mapper.AccountMapper;
import eu.getmangos.rest.AccountResource;

@RequestScoped
@Path("/account/v1")
@Tag(name = "account")
public class AccountResourceService implements AccountResource {

    @Inject private Logger logger;

    @Inject private AccountController accountController;

    @Inject private AccountMapper mapper;

    public Response findAccount(Integer id) {
        logger.debug("find() entry.");

        if (id == null) {
                return Response.status(500).entity("The provided ID is null.").build();
        }

        AccountDTO account = mapper.accountToDTO(accountController.find(id));

        if(account == null) {
                return Response.status(404).entity("The provided ID has no match in the database.").build();
        }

        logger.debug("find() exit.");
        return Response.status(200).entity(account).build();
    }

    public List<AccountDTO> findAllAccounts() {
        logger.debug("findAll() entry.");

        List<AccountDTO> listAccounts = new ArrayList<>();
        for(Account a : this.accountController.findAll()) {
            listAccounts.add(mapper.accountToDTO(a));
        }

        logger.debug("findAll() exit.");
        return listAccounts;
    }

    public Response addAccount(AccountDTO entity) {
        try {
                this.accountController.create(mapper.dtoToEntity(entity));
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
                this.accountController.update(mapper.dtoToEntity(entity));
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

}
