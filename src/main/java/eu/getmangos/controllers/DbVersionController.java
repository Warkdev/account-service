package eu.getmangos.controllers;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.slf4j.Logger;

import eu.getmangos.entities.DbVersion;

@RequestScoped
public class DbVersionController {
    @Inject private Logger logger;

    @PersistenceContext(name = "AUTH_PU")
    private EntityManager em;

    /**
     * Retrieves the database version.
     * @return A record containing the database version.
     */
    public DbVersion getVersion() {
        logger.debug("getVersion() entry.");

        DbVersion version = (DbVersion) em.createNamedQuery("DbVersion.findAll").getSingleResult();

        logger.debug("getVersion() exit.");
        return version;
    }
}
