package eu.getmangos.rest.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import org.slf4j.Logger;

import eu.getmangos.dao.DAOException;
import eu.getmangos.dao.IpBannedDAO;
import eu.getmangos.dto.IpBannedDTO;
import eu.getmangos.dto.MessageDTO;
import eu.getmangos.entities.IpBanned;
import eu.getmangos.entities.IpBannedId;
import eu.getmangos.mapper.IpBannedMapper;
import eu.getmangos.rest.IpBanResource;

@ApplicationScoped
@Path("")
public class IpBanResourceService implements IpBanResource {

    @Inject private Logger logger;

    @Inject private IpBannedDAO controller;

    @Inject private IpBannedMapper mapper;

    @Override
    public Response findIpBan(String ip, Date banDate) {
        logger.debug("find() entry.");

        if (ip == null || ip.isBlank()) {
                return Response.status(400).entity(new MessageDTO("The provided IP is null.")).build();
        }

        IpBanned IpBanned = this.controller.find(new IpBannedId(ip, banDate.getTime()));

        if(IpBanned == null) {
                return Response.status(404).entity(new MessageDTO("The provided ID has no match in the database.")).build();
        }

        logger.debug("find() exit.");
        return Response.status(200).entity(IpBanned).build();
    }

    @Override
    public Response findAllIpBans() {
        logger.debug("findAll() entry.");
        List<IpBannedDTO> listBans = new ArrayList<>();

        for(IpBanned ban : this.controller.findAll()) {
            listBans.add(mapper.map(ban));
        }

        logger.debug("findAll() exit.");
        return Response.status(200).entity(listBans).build();
    }

    @Override
    public Response addIpBan(IpBannedDTO entity) {
        try {
                this.controller.create(mapper.map(entity));
        } catch (DAOException daoEx) {
                return Response.status(400).entity(new MessageDTO(daoEx.getMessage())).build();
        } catch (Exception ex) {
                return Response.status(500).entity(new MessageDTO(ex.getMessage())).build();
        }
        return Response.status(201).entity(new MessageDTO("Ban has been created.")).build();
    }

    @Override
    public Response editIpBan(String ip, Date banDate, IpBannedDTO entity) {
        try {
                IpBanned ban = mapper.map(entity);
                ban.setId(new IpBannedId(ip, banDate.getTime()));
                this.controller.update(ban);
        } catch (DAOException daoEx) {
                return Response.status(404).entity(new MessageDTO(daoEx.getMessage())).build();
        } catch (Exception ex) {
                return Response.status(500).entity(new MessageDTO(ex.getMessage())).build();
        }
        return Response.status(200).entity(new MessageDTO("Ban has been updated.")).build();
    }

    @Override
    public Response deleteIpBan(String ip, Date banDate) {
        try {
                this.controller.delete(new IpBannedId(ip, banDate.getTime()));
        } catch (DAOException daoEx) {
                return Response.status(404).entity(new MessageDTO(daoEx.getMessage())).build();
        } catch (Exception ex) {
                return Response.status(500).entity(new MessageDTO(ex.getMessage())).build();
        }
        return Response.status(204).build();
    }

}
