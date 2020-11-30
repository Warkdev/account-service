package eu.getmangos.dao.impl;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.slf4j.Logger;

import eu.getmangos.dao.WardenDAO;
import eu.getmangos.entities.WardenLog;

@ApplicationScoped
public class WardenDAOImpl implements WardenDAO {
    @Inject private Logger logger;

    @PersistenceContext(unitName = "AUTH_PU")
    private EntityManager em;

    @SuppressWarnings("unchecked")
    public List<WardenLog> getAllLogs() {
        logger.debug("getAllLogs() entry.");

        List<WardenLog> logsList = (List<WardenLog>) em.createNamedQuery("WardenLog.findAll").getResultList();

        logger.debug("getAllLogs() exit.");
        return logsList;
    }

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

    @SuppressWarnings("unchecked")
    public List<WardenLog> findDeadLinks() {
        logger.debug("findDeadLinks() entry.");

        List<WardenLog> logsList = (List<WardenLog>) em.createNamedQuery("WardenLog.findDeadLinks").getResultList();

        logger.debug("findDeadLinks() exit.");
        return logsList;
    }

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

    @Transactional
    public void delete(Integer entry) {
        logger.debug("delete() entry.");

        WardenLog log = search(entry);
        if(log != null) {
            em.remove(log);
        }

        logger.debug("delete() exit.");
    }

    @Transactional
    public void deleteLogsForAccount(Integer accountId) {
        logger.debug("deleteLogsForAccount() entry.");

        for(WardenLog u : getWardenLogForAccount(accountId)) {
            em.remove(u);
        }

        logger.debug("deleteLogsForAccount() exit.");
    }

    @SuppressWarnings("unchecked")
    public List<WardenLog> getWardenLogForAccount(Integer accountId) {
        logger.debug("getWardenLogForAccount() entry.");

        List<WardenLog> logsList = (List<WardenLog>) em.createNamedQuery("WardenLog.findByAccount").setParameter("accountid", accountId).getResultList();

        logger.debug("getWardenLogForAccount() exit.");
        return logsList;
    }
}
