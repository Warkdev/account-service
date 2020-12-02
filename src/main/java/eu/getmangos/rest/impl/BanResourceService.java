package eu.getmangos.rest.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import org.slf4j.Logger;

import eu.getmangos.dao.AccountBannedDAO;
import eu.getmangos.dao.DAOException;
import eu.getmangos.dto.BansDTO;
import eu.getmangos.dto.MessageDTO;
import eu.getmangos.entities.AccountBanned;
import eu.getmangos.mapper.BanMapper;
import eu.getmangos.rest.BanResource;

@ApplicationScoped
@Path("")
public class BanResourceService implements BanResource {

    @Inject private Logger logger;

    @Inject private AccountBannedDAO controller;

    @Inject private BanMapper mapper;

    @Override
    public Response findBan(Integer id, Date banDate) {
        logger.debug("find() entry.");

        if (id == null) {
                return Response.status(500).entity(new MessageDTO("The provided ID is null.")).build();
        }

        BansDTO ban = mapper.map(controller.find(id, banDate.getTime()));

        if(ban == null) {
                return Response.status(404).entity(new MessageDTO("The provided ID has no match in the database.")).build();
        }

        logger.debug("find() exit.");
        return Response.status(200).entity(ban).build();
    }

    @Override
    public Response findAllBans() {
        logger.debug("findAll() entry.");

        List<BansDTO> listAccounts = new ArrayList<>();

        for(AccountBanned ban : this.controller.findAll()){
            listAccounts.add(mapper.map(ban));
        }

        logger.debug("findAll() exit.");
        return Response.status(200).entity(listAccounts).build();
    }

    @Override
    public Response addBan(BansDTO entity) {
        try {
                this.controller.create(mapper.map(entity));
        } catch (DAOException daoEx) {
                return Response.status(400).entity(new MessageDTO(daoEx.getMessage())).build();
        } catch (Exception ex) {
                return Response.status(500).entity(new MessageDTO(ex.getMessage())).build();
        }
        return Response.status(201).entity("Ban has been created.").build();
    }

    @Override
    public Response editBan(Integer id, Date banDate, BansDTO entity) {
        try {
                AccountBanned ban = mapper.map(entity);
                this.controller.update(id, banDate.getTime(), ban);
        } catch (DAOException daoEx) {
                return Response.status(404).entity(new MessageDTO(daoEx.getMessage())).build();
        } catch (Exception ex) {
                return Response.status(500).entity(new MessageDTO(ex.getMessage())).build();
        }
        return Response.status(200).entity("Ban has been updated.").build();
    }

    @Override
    public Response deleteBan(Integer id, Date banDate) {
        try {
                this.controller.delete(id, banDate.getTime());
        } catch (DAOException daoEx) {
                return Response.status(404).entity(new MessageDTO(daoEx.getMessage())).build();
        } catch (Exception ex) {
                return Response.status(500).entity(new MessageDTO(ex.getMessage())).build();
        }
        return Response.status(204).build();
    }
}
