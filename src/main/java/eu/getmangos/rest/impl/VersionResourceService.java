package eu.getmangos.rest.impl;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Path;



import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import org.slf4j.Logger;

import eu.getmangos.controllers.DbVersionController;
import eu.getmangos.dto.DbVersionDTO;
import eu.getmangos.mapper.VersionMapper;
import eu.getmangos.rest.VersionResource;

@RequestScoped
@Path("/version/v1")
@Tag(name = "version")
public class VersionResourceService implements VersionResource {

    @Inject private Logger logger;

    @Inject private DbVersionController dbVersionController;

    @Inject private VersionMapper mapper;

    public DbVersionDTO getDbVersion() {
        logger.debug("getDbVersion() entry.");
        DbVersionDTO version = mapper.versionToDTO(dbVersionController.getVersion());

        logger.debug("getDbVersion() exit.");
        return version;
    }
}
