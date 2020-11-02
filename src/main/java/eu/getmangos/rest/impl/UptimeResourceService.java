package eu.getmangos.rest.impl;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;



import org.slf4j.Logger;

import eu.getmangos.controllers.UptimeController;
import eu.getmangos.dto.UptimeDTO;
import eu.getmangos.entities.Uptime;
import eu.getmangos.mapper.UptimeMapper;
import eu.getmangos.rest.UptimeResource;

@RequestScoped
@Path("/status/v1")
public class UptimeResourceService implements UptimeResource {

    @Inject private Logger logger;

    @Inject private UptimeController uptimeController;

    @Inject private UptimeMapper mapper;

    public Response getUptimesForRealm(Integer realmId) {
        logger.debug("getUptimesForRealm() entry.");

        if(realmId == null) {
            return Response.status(500).entity("The provided ID is null.").build();
        }

        List<UptimeDTO> uptimeList = new ArrayList<>();

        for(Uptime u : this.uptimeController.getUptimeForRealm(realmId)) {
            uptimeList.add(mapper.uptimeToDTO(u));
        }

        logger.debug("getUptimesForRealm() exit.");
        return Response.status(200).entity(uptimeList).build();
    }

    public Response getUptimes() {
        logger.debug("getUptimes() entry.");

        List<UptimeDTO> uptimeList = new ArrayList<>();

        for(Uptime u : this.uptimeController.getAllUptimes()) {
            uptimeList.add(mapper.uptimeToDTO(u));
        }

        logger.debug("getUptimes() exit.");
        return Response.status(200).entity(uptimeList).build();
    }
}
