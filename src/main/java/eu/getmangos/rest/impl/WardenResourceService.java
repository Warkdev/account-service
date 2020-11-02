package eu.getmangos.rest.impl;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import org.slf4j.Logger;

import eu.getmangos.controllers.WardenController;
import eu.getmangos.dto.WardenLogDTO;
import eu.getmangos.entities.WardenLog;
import eu.getmangos.mapper.WardenLogMapper;
import eu.getmangos.rest.WardenResource;

@RequestScoped
@Path("/warden/v1")
public class WardenResourceService implements WardenResource {

    @Inject private Logger logger;

    @Inject private WardenController wardenController;

    @Inject private WardenLogMapper mapper;

    public Response getWardenLogsForAccount(Integer accountId) {
        logger.debug("getWardenLogsForAccount() entry.");

        if(accountId == null) {
            return Response.status(500).entity("The provided ID is null.").build();
        }

        List<WardenLogDTO> logList = new ArrayList<>();

        for(WardenLog log : this.wardenController.getWardenLogForAccount(accountId)) {
            logList.add(mapper.logToDTO(log));
        }

        logger.debug("getWardenLogsForAccount() exit.");
        return Response.status(200).entity(logList).build();
    }

    public Response getAllWardenLogs() {
        logger.debug("getAllWardenLogs() entry.");

        List<WardenLogDTO> logList = new ArrayList<>();

        for(WardenLog log : this.wardenController.getAllLogs()) {
            logList.add(mapper.logToDTO(log));
        }

        logger.debug("getAllWardenLogs() exit.");
        return Response.status(200).entity(logList).build();
    }
}
