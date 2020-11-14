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

import eu.getmangos.controllers.DAOException;
import eu.getmangos.controllers.IpBannedController;
import eu.getmangos.dto.IpBannedDTO;
import eu.getmangos.entities.IpBanned;
import eu.getmangos.entities.IpBannedId;
import eu.getmangos.mapper.IpBannedMapper;
import eu.getmangos.rest.IPBansResource;

@RequestScoped
@Path("banip/v1")
@Tag(name = "ban")
public class IPBansResourceService implements IPBansResource {

    @Inject private Logger logger;

    @Inject private IpBannedController ipBannedController;

    @Inject private IpBannedMapper mapper;

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

    public List<IpBannedDTO> findAllIpBans() {
        logger.debug("findAll() entry.");
        List<IpBannedDTO> listBans = new ArrayList<>();

        for(IpBanned ban : this.ipBannedController.findAll()) {
            listBans.add(mapper.banToDTO(ban));
        }

        logger.debug("findAll() exit.");
        return listBans;
    }

    public Response addIpBan(IpBannedDTO entity) {
        try {
                this.ipBannedController.create(mapper.dtoToEntity(entity));
        } catch (DAOException daoEx) {
                return Response.status(400).entity(daoEx.getMessage()).build();
        } catch (Exception ex) {
                return Response.status(500).entity(ex.getMessage()).build();
        }
        return Response.status(201).entity("Ban has been created.").build();
    }

    public Response editIpBan(String ip, Date banDate, IpBannedDTO entity) {
        try {
                IpBanned ban = mapper.dtoToEntity(entity);
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

}
