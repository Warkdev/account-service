package eu.getmangos.controllers;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.slf4j.Logger;

import eu.getmangos.entities.WardenLog;

@RequestScoped
public class WardenController {
    @Inject private Logger logger;

    @PersistenceContext(name = "AUTH_PU")
    private EntityManager em;

    /**
     * Retrieves all warden logs from the database.
     * @return A list of records containing the warden logs for all servers.
     */
    @SuppressWarnings("unchecked")
    public List<WardenLog> getAllLogs() {
        logger.debug("getAllLogs() entry.");

        List<WardenLog> logsList = (List<WardenLog>) em.createNamedQuery("WardenLog.findAll").getResultList();

        logger.debug("getAllLogs() exit.");
        return logsList;
    }

    /**
     * Delete all warden logs from the database which have dead links.
     * @return A list of records containing the warden logs for all servers.
     */
    @Transactional
    public int cleanupDeadLinks() {
        logger.debug("cleanupDeadLinks() entry.");

        List<WardenLog> logs = findDeadLinks();
        int records = 0;
        if(logs.size() > 0) {
            records = em.createNamedQuery("WardenLog.deleteDeadLinks")
                .setParameter("entry", logs)
                .executeUpdate();
        }

        logger.debug("cleanupDeadLinks() exit.");
        return records;
    }

    /**
     * Retrieves all warden logs from the database which have dead links.
     * @return A list of records containing the warden logs for all servers.
     */
    @SuppressWarnings("unchecked")
    public List<WardenLog> findDeadLinks() {
        logger.debug("findDeadLinks() entry.");

        List<WardenLog> logsList = (List<WardenLog>) em.createNamedQuery("WardenLog.findDeadLinks").getResultList();

        logger.debug("findDeadLinks() exit.");
        return logsList;
    }

    /**
     * Retrieves a log entry by its id.
     * @param entry The log entry id.
     * @return The log entry.
     */
    public WardenLog search(Integer entry) {
        logger.debug("search() entry");
        try {
            WardenLog log = (WardenLog) em.createNamedQuery("WardenLog.findById").setParameter("id", entry).getSingleResult();
            logger.debug("search() exit.");
            return log;
        } catch (NoResultException nre) {
            logger.debug("No log found with this id.");
            logger.debug("search() exit.");
            return null;
        }
    }

    /**
     * Delete warden logs for the given id.
     * @param entry The Id of the log to be deleted.
     */
    @Transactional
    public void delete(Integer entry) {
        logger.debug("delete() entry.");

        WardenLog log = search(entry);
        if(log != null) {
            em.remove(log);
        }

        logger.debug("delete() exit.");
    }

    /**
     * Delete warden logs for the given account id.
     * @param accountId The Id of the account for which the logs needs to be deleted.
     */
    @Transactional
    public void deleteLogsForAccount(Integer accountId) {
        logger.debug("deleteLogsForAccount() entry.");

        for(WardenLog u : getWardenLogForAccount(accountId)) {
            em.remove(u);
        }

        logger.debug("deleteLogsForAccount() exit.");
    }

    /**
     * Retrieves all warden logs from the database for a given account.
     * @return A list of records containing the logs for a single account.
     */
    @SuppressWarnings("unchecked")
    public List<WardenLog> getWardenLogForAccount(Integer accountId) {
        logger.debug("getWardenLogForAccount() entry.");

        List<WardenLog> logsList = (List<WardenLog>) em.createNamedQuery("WardenLog.findByAccount").setParameter("accountid", accountId).getResultList();

        logger.debug("getWardenLogForAccount() exit.");
        return logsList;
    }
}
