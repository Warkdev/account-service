package eu.getmangos.rest.impl;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import org.slf4j.Logger;

import eu.getmangos.controllers.WardenController;
import eu.getmangos.dto.CleanupResultDTO;
import eu.getmangos.dto.MessageDTO;
import eu.getmangos.dto.WardenLogDTO;
import eu.getmangos.entities.WardenLog;
import eu.getmangos.mapper.WardenLogMapper;
import eu.getmangos.rest.WardenResource;

@ApplicationScoped
@Path("")
public class WardenResourceService implements WardenResource {

    @Inject private Logger logger;

    @Inject private WardenController controller;

    @Inject private WardenLogMapper mapper;

    @Override
    public Response getWardenLogsForAccount(Integer accountId) {
        logger.debug("getWardenLogsForAccount() entry.");

        if(accountId == null) {
            return Response.status(400).entity(new MessageDTO("The provided ID is null.")).build();
        }

        List<WardenLogDTO> logList = new ArrayList<>();

        for(WardenLog log : this.controller.getWardenLogForAccount(accountId)) {
            logList.add(mapper.map(log));
        }

        logger.debug("getWardenLogsForAccount() exit.");
        return Response.status(200).entity(logList).build();
    }

    @Override
    public Response getAllWardenLogs() {
        logger.debug("getAllWardenLogs() entry.");

        List<WardenLogDTO> logList = new ArrayList<>();

        for(WardenLog log : this.controller.getAllLogs()) {
            logList.add(mapper.map(log));
        }

        logger.debug("getAllWardenLogs() exit.");
        return Response.status(200).entity(logList).build();
    }

    @Override
    public Response cleanupWardenLogs() {
        logger.debug("deleteWardenLogs() entry.");

        CleanupResultDTO result = new CleanupResultDTO();
        result.setRecords(controller.cleanupDeadLinks());

        logger.debug("deleteWardenLogs() exit.");
        return Response.status(200).entity(result).build();
    }

}
