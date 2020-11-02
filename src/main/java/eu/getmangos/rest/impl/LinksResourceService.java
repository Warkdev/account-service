package eu.getmangos.rest.impl;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;



import org.slf4j.Logger;

import eu.getmangos.controllers.DAOException;
import eu.getmangos.controllers.RealmCharactersController;
import eu.getmangos.dto.LinksDTO;
import eu.getmangos.entities.RealmCharacters;
import eu.getmangos.entities.RealmCharactersID;
import eu.getmangos.mapper.LinksMapper;
import eu.getmangos.rest.LinksResource;

@RequestScoped
@Path("/account_realms/v1")
public class LinksResourceService implements LinksResource {

    @Inject private Logger logger;

    @Inject private RealmCharactersController realmCharactersController;

    @Inject private LinksMapper mapper;

    public Response findRealmCharacters(Integer realmID, Integer accountID) {
        logger.debug("find() entry.");

        if (realmID == null || accountID == null) {
                return Response.status(500).entity("The provided ID is null.").build();
        }

        RealmCharacters link = this.realmCharactersController.find(new RealmCharactersID(realmID, accountID));

        if(link == null) {
                return Response.status(404).entity("The provided ID has no match in the database.").build();
        }

        logger.debug("find() exit.");
        return Response.status(200).entity(link).build();
    }

    public Response findRealmCharactersByRealmID(Integer realmID) {
        logger.debug("find() entry.");

        if (realmID == null) {
                return Response.status(500).entity("The provided ID is null.").build();
        }

        List<RealmCharacters> linkList = this.realmCharactersController.findByRealm(realmID);

        logger.debug("find() exit.");
        return Response.status(200).entity(linkList).build();
    }

    public Response findRealmCharactersByAccountID(Integer accountID) {
        logger.debug("find() entry.");

        if (accountID == null) {
                return Response.status(500).entity("The provided ID is null.").build();
        }

        List<RealmCharacters> linkList = this.realmCharactersController.findByAccount(accountID);

        logger.debug("find() exit.");
        return Response.status(200).entity(linkList).build();
    }

    public List<LinksDTO> findAllRealmCharacters() {
        logger.debug("findAll() entry.");

        List<LinksDTO> listRealmCharacters = new ArrayList<>();

        for(RealmCharacters link : this.realmCharactersController.findAll()) {
            listRealmCharacters.add(mapper.linkToDTO(link));
        }

        logger.debug("findAll() exit.");
        return listRealmCharacters;
    }

    public Response addRealmCharacters(LinksDTO entity) {
        try {
                this.realmCharactersController.create(mapper.dtoToEntity(entity));
        } catch (DAOException daoEx) {
                return Response.status(400).entity(daoEx.getMessage()).build();
        } catch (Exception ex) {
                return Response.status(500).entity(ex.getMessage()).build();
        }
        return Response.status(201).entity("Link has been created.").build();
    }

    public Response editRealmCharacters(Integer accountID, Integer realmID, LinksDTO entity) {
        try {
                RealmCharacters link = mapper.dtoToEntity(entity);
                link.setId(new RealmCharactersID(accountID, realmID));
                this.realmCharactersController.update(link);
        } catch (DAOException daoEx) {
                return Response.status(404).entity(daoEx.getMessage()).build();
        } catch (Exception ex) {
                return Response.status(500).entity(ex.getMessage()).build();
        }
        return Response.status(200).entity("Link has been updated.").build();
    }

    public Response deleteRealmList(Integer accountID, Integer realmID) {
        try {
                this.realmCharactersController.delete(new RealmCharactersID(realmID, accountID));
        } catch (DAOException daoEx) {
                return Response.status(404).entity(daoEx.getMessage()).build();
        } catch (Exception ex) {
                return Response.status(500).entity(ex.getMessage()).build();
        }
        return Response.status(204).build();
    }

}
