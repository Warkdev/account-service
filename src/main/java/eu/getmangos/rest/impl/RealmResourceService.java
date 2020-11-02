package eu.getmangos.rest.impl;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;



import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import org.slf4j.Logger;

import eu.getmangos.controllers.DAOException;
import eu.getmangos.controllers.RealmController;
import eu.getmangos.dto.RealmDTO;
import eu.getmangos.entities.Realm;
import eu.getmangos.mapper.RealmMapper;
import eu.getmangos.rest.RealmResource;

@RequestScoped
@Path("/realm/v1")
@Tag(name = "realm")
public class RealmResourceService implements RealmResource {

    @Inject private Logger logger;

    @Inject private RealmController realmController;

    @Inject private RealmMapper mapper;

    public Response findRealm(Integer id) {
        logger.debug("find() entry.");

        if (id == null) {
                return Response.status(500).entity("The provided ID is null.").build();
        }

        Realm Realm = this.realmController.find(id);

        if(Realm == null) {
                return Response.status(404).entity("The provided ID has no match in the database.").build();
        }

        logger.debug("find() exit.");
        return Response.status(200).entity(Realm).build();
    }

    public List<RealmDTO> findAllRealms() {
        logger.debug("findAll() entry.");

        List<RealmDTO> listRealms = new ArrayList<>();

        for(Realm r : this.realmController.findAll()) {
            listRealms.add(mapper.realmToDTO(r));
        }

        logger.debug("findAll() exit.");
        return listRealms;
    }

    public Response addRealm(RealmDTO entity) {
        try {
                this.realmController.create(mapper.dtoToEntity(entity));
        } catch (DAOException daoEx) {
                return Response.status(400).entity(daoEx.getMessage()).build();
        } catch (Exception ex) {
                return Response.status(500).entity(ex.getMessage()).build();
        }
        return Response.status(201).entity("Realm has been created.").build();
    }

    public Response editRealm(Integer id, RealmDTO entity) {
        try {
                entity.setId(id);
                this.realmController.update(mapper.dtoToEntity(entity));
        } catch (DAOException daoEx) {
                return Response.status(404).entity(daoEx.getMessage()).build();
        } catch (Exception ex) {
                return Response.status(500).entity(ex.getMessage()).build();
        }
        return Response.status(200).entity("Realm has been updated.").build();
    }

    public Response deleteRealm(Integer id) {
        try {
                this.realmController.delete(id);
        } catch (DAOException daoEx) {
                return Response.status(404).entity(daoEx.getMessage()).build();
        } catch (Exception ex) {
                return Response.status(500).entity(ex.getMessage()).build();
        }
        return Response.status(204).build();
    }

}
