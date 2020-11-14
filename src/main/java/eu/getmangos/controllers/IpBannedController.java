package eu.getmangos.controllers;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.slf4j.Logger;

import eu.getmangos.entities.IpBanned;
import eu.getmangos.entities.IpBannedId;

@RequestScoped
public class IpBannedController {
    @Inject private Logger logger;

    @PersistenceContext(name = "AUTH_PU")
    private EntityManager em;

    @Transactional
    /**
     * Creates a ban ip in the dabatase.
     * @param ipBan The ip to ban.
     * @throws DAOException Send a DAOException if something happened during the data validation.
     */
    public void create(IpBanned ipBanned) throws DAOException {
        logger.debug("create() entry.");
        ipBanned.getId().setBanDate(System.currentTimeMillis());
        em.persist(ipBanned);
        logger.debug("create() exit.");
    }

    @Transactional
    /**
     * Updates an ip ban in the dabatase.
     * @param account The ban to edit.
     * @throws DAOException Send a DAOException if something happened during the data validation.
     */
    public void update(IpBanned ipBanned) throws DAOException {
        logger.debug("update() entry.");
        IpBanned existing = find(ipBanned.getId());
        if(existing == null) {
            logger.debug("update() exit.");
            throw new DAOException("Ban doesn't exist.");
        }

        em.merge(ipBanned);
        logger.debug("update() exit.");
    }

    @Transactional
    /**
     * Delete a ban in the database.
     * @param id The ID of the ban to be deleted.
     * @throws DAOException Send a DAOException if something happened during the data validation.
     */
    public void delete(IpBannedId id) throws DAOException {
        logger.debug("delete() entry.");

        IpBanned ipBanned = find(id);
        if(ipBanned == null) {
            logger.debug("delete() exit.");
            throw new DAOException("The ban doesn't exist");
        }

        em.remove(ipBanned);

        logger.debug("delete() exit.");
    }

    /**
     * Retrieves a ban by its ID.
     * @param id The ID of the ban
     * @return The ban if found, null otherwise.
     */
    public IpBanned find(IpBannedId id) {
        try {
            IpBanned ipBanned = (IpBanned) em.createNamedQuery("IpBanned.findById").setParameter("id", id).getSingleResult();
            logger.debug("search() exit.");
            return ipBanned;
        } catch (NoResultException nre) {
            logger.debug("No ban found with this id.");
            logger.debug("search() exit.");
            return null;
        }
    }

    /**
     * Search all bans for an ip.
     * @param name The ip.
     * @return The matching bans for the given ip.
     */
    public IpBanned search(Integer ip) {
        logger.debug("search() entry.");
        try {
            IpBanned ipBanned = (IpBanned) em.createNamedQuery("IpBanned.findByIp").setParameter("ip", ip).getSingleResult();
            logger.debug("search() exit.");
            return ipBanned;
        } catch (NoResultException nre) {
            logger.debug("No ban found for this account id.");
            logger.debug("search() exit.");
            return null;
        }

    }

    /**
     * Retrieves all ban ip from the database.
     * @return A list of Bans.
     */
    @SuppressWarnings("unchecked")
    public List<IpBanned> findAll() {
        return (List<IpBanned>) em.createNamedQuery("IpBanned.findAll").getResultList();
    }
}
