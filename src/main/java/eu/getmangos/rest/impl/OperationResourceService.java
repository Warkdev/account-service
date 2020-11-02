package eu.getmangos.rest.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import org.slf4j.Logger;

import eu.getmangos.controllers.DAOException;
import eu.getmangos.controllers.OperationController;
import eu.getmangos.rest.OperationResource;

@RequestScoped
@Path("/operation/v1")
@Tag(name = "operation")
public class OperationResourceService implements OperationResource {

    @Inject private Logger logger;

    @Inject private OperationController operationController;

    public Response cleanUpDB() {
        logger.debug("cleanUpDB() entry.");

        Map<String, Integer> ret = new HashMap<>();

        try {
            ret = this.operationController.cleanup();
        } catch (DAOException daoEx) {
                return Response.status(404).entity(daoEx.getMessage()).build();
        } catch (Exception ex) {
                return Response.status(500).entity(ex.getMessage()).build();
        }

        logger.debug("cleanUpDB() exit.");
        return Response.status(200).entity(ret).build();
    }

    public Response showCleanUpDB() {
        logger.debug("showCleanUpDB() entry.");

        Map<String, List> ret = new HashMap<>();

        try {
            ret = this.operationController.getCleanup();
        } catch (DAOException daoEx) {
                return Response.status(404).entity(daoEx.getMessage()).build();
        } catch (Exception ex) {
                return Response.status(500).entity(ex.getMessage()).build();
        }

        logger.debug("showCleanUpDB() exit.");
        return Response.status(200).entity(ret).build();
    }
}
