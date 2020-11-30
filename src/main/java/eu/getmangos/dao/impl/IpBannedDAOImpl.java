package eu.getmangos.dao.impl;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import org.slf4j.Logger;

import eu.getmangos.dao.DAOException;
import eu.getmangos.dao.IpBannedDAO;
import eu.getmangos.entities.IpBanned;
import eu.getmangos.entities.IpBannedId;

@ApplicationScoped
public class IpBannedDAOImpl implements IpBannedDAO {
    @Inject private Logger logger;

    @PersistenceContext(unitName = "AUTH_PU")
    private EntityManager em;

    public void create(IpBanned ipBanned) throws DAOException {
        logger.debug("create() entry.");
        ipBanned.getId().setBanDate(System.currentTimeMillis());
        em.persist(ipBanned);
        logger.debug("create() exit.");
    }

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

    @SuppressWarnings("unchecked")
    public List<IpBanned> findAll() {
        return (List<IpBanned>) em.createNamedQuery("IpBanned.findAll").getResultList();
    }
}
