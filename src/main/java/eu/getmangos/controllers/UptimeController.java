package eu.getmangos.controllers;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.slf4j.Logger;

import eu.getmangos.entities.Uptime;
import eu.getmangos.entities.UptimeId;

@RequestScoped
public class UptimeController {
    @Inject private Logger logger;

    @PersistenceContext(name = "AUTH_PU")
    private EntityManager em;

    /**
     * Retrieves all uptimes from the database.
     * @return A list of records containing the uptime for all servers.
     */
    @SuppressWarnings("unchecked")
    public List<Uptime> getAllUptimes() {
        logger.debug("getAllUptimes() entry.");

        List<Uptime> uptimeList = (List<Uptime>) em.createNamedQuery("Uptime.findAll").getResultList();

        logger.debug("getAllUptimes() exit.");
        return uptimeList;
    }

    @Transactional
    public void delete(UptimeId id) throws DAOException {
        logger.debug("delete() entry.");

        // Need to add retrieval by ID.
        em.remove(id);

        logger.debug("delete() exit.");
    }

    /**
     * Delete uptimes for the given realm ID.
     * @param realmId The Id of the realm for which the uptime needs to be deleted.
     */
    @Transactional
    public void deleteUptimeForRealm(Integer realmId) {
        logger.debug("deleteUptimeForRealm() entry.");

        for(Uptime u : getUptimeForRealm(realmId)) {
            em.remove(u);
        }

        logger.debug("deleteUptimeForRealm() exit.");
    }

    /**
     * Retrieves all uptimes from the database for a given Realm.
     * @return A list of records containing the uptime for a single server.
     */
    @SuppressWarnings("unchecked")
    public List<Uptime> getUptimeForRealm(Integer realmId) {
        logger.debug("getUptimeForRealm() entry.");

        List<Uptime> uptimeList = (List<Uptime>) em.createNamedQuery("Uptime.findByRealm").setParameter("id", realmId).getResultList();

        logger.debug("getUptimeForRealm() exit.");
        return uptimeList;
    }

    /**
     * Delete all uptime logs from the database which have dead links.
     * @return An int indicating the amount of records impacted by this operation.
     */
    @Transactional
    public int cleanupDeadLinks() {
        logger.debug("cleanupDeadLinks() entry.");

        List<Uptime> logs = findDeadLinks();
        int records = 0;
        if(logs.size() > 0) {
            records = em.createNamedQuery("Uptime.deleteDeadLinks")
                .setParameter("id", logs)
                .executeUpdate();
        }

        logger.debug("cleanupDeadLinks() exit.");
        return records;
    }

    /**
     * Retrieves all uptime logs from the database which have dead links.
     * @return A list of records containing the uptime logs which could be cleaned-up.
     */
    @SuppressWarnings("unchecked")
    public List<Uptime> findDeadLinks() {
        logger.debug("findDeadLinks() entry.");

        logger.debug(""+em.createNamedQuery("Uptime.findDeadLinks"));
        List<Uptime> logsList = (List<Uptime>) em.createNamedQuery("Uptime.findDeadLinks").getResultList();

        logger.debug("findDeadLinks() exit.");
        return logsList;
    }
}
