package eu.getmangos.rest.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;



import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import org.slf4j.Logger;

import eu.getmangos.controllers.AccountBannedController;
import eu.getmangos.controllers.DAOException;
import eu.getmangos.dto.BansDTO;
import eu.getmangos.entities.AccountBanned;
import eu.getmangos.entities.AccountBannedId;
import eu.getmangos.mapper.BanMapper;
import eu.getmangos.rest.BansResource;

@RequestScoped
@Path("ban/v1")
@Tag(name = "ban")
public class BansResourceService implements BansResource {

    @Inject private Logger logger;

    @Inject private AccountBannedController accountBannedController;

    @Inject private BanMapper mapper;

    public Response findBan(Integer id, Date banDate) {
        logger.debug("find() entry.");

        if (id == null) {
                return Response.status(500).entity("The provided ID is null.").build();
        }

        BansDTO ban = mapper.banToDTO(accountBannedController.find(new AccountBannedId(id, banDate.getTime())));

        if(ban == null) {
                return Response.status(404).entity("The provided ID has no match in the database.").build();
        }

        logger.debug("find() exit.");
        return Response.status(200).entity(ban).build();
    }

    public List<BansDTO> findAllBans() {
        logger.debug("findAll() entry.");

        List<BansDTO> listAccounts = new ArrayList<>();

        for(AccountBanned ban : this.accountBannedController.findAll()){
            listAccounts.add(mapper.banToDTO(ban));
        }

        logger.debug("findAll() exit.");
        return listAccounts;
    }

    public Response addBan(BansDTO entity) {
        try {
                this.accountBannedController.create(mapper.dtoToEntity(entity));
        } catch (DAOException daoEx) {
                return Response.status(400).entity(daoEx.getMessage()).build();
        } catch (Exception ex) {
                return Response.status(500).entity(ex.getMessage()).build();
        }
        return Response.status(201).entity("Ban has been created.").build();
    }

    public Response editBan(Integer id, Date banDate, BansDTO entity) {
        try {
                AccountBanned ban = mapper.dtoToEntity(entity);
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

}
