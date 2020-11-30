package eu.getmangos.dao.impl;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import org.slf4j.Logger;

import eu.getmangos.dao.AccountBannedDAO;
import eu.getmangos.dao.AccountDAO;
import eu.getmangos.dao.DAOException;
import eu.getmangos.entities.AccountBanned;
import eu.getmangos.entities.AccountBannedId;

@ApplicationScoped
public class AccountBannedDAOImpl implements AccountBannedDAO {
    @Inject private Logger logger;
    @Inject AccountDAO accountController;

    @PersistenceContext(unitName = "AUTH_PU")
    private EntityManager em;

    public void create(AccountBanned accountBanned) throws DAOException {
        logger.debug("create() entry.");
        if(accountController.find(accountBanned.getAccountBannedId().getId()) == null) {
            logger.debug("create() exit.");
            throw new DAOException("Account doesn't exist.");
        }
        accountBanned.getAccountBannedId().setBanDate(System.currentTimeMillis());
        em.persist(accountBanned);
        logger.debug("create() exit.");
    }

    public void update(AccountBanned accountBanned) throws DAOException {
        logger.debug("update() entry.");
        AccountBanned existing = find(accountBanned.getAccountBannedId());
        if(existing == null) {
            logger.debug("update() exit.");
            throw new DAOException("Ban doesn't exist.");
        }

        em.merge(accountBanned);
        logger.debug("update() exit.");
    }

    public void delete(AccountBannedId id) throws DAOException {
        logger.debug("delete() entry.");

        AccountBanned accountBanned = find(id);
        if(accountBanned == null) {
            logger.debug("delete() exit.");
            throw new DAOException("The ban doesn't exist");
        }

        em.remove(accountBanned);

        logger.debug("delete() exit.");
    }

    public AccountBanned find(AccountBannedId id) {
        logger.debug("find() entry.");
        try {
            AccountBanned accountBanned = (AccountBanned) em.createNamedQuery("AccountBanned.findById").setParameter("id", id).getSingleResult();
            logger.debug("find() exit.");
            return accountBanned;
        } catch (NoResultException nre) {
            logger.debug("No ban found with this id.");
            logger.debug("find() exit.");
            return null;
        }
    }

    public void deleteForAccount(Integer accountId) {
        logger.debug("deleteForAccount() entry.");

        em.createNamedQuery("AccountBanned.deleteForAccount").setParameter("id", accountId).executeUpdate();

        logger.debug("deleteForAccount() exit.");
    }

    public AccountBanned search(Integer id) {
        logger.debug("search() entry.");
        try {
            AccountBanned accountBanned = (AccountBanned) em.createNamedQuery("AccountBanned.findByAccount").setParameter("id", id).getSingleResult();
            logger.debug("search() exit.");
            return accountBanned;
        } catch (NoResultException nre) {
            logger.debug("No ban found for this account id.");
            logger.debug("search() exit.");
            return null;
        }

    }

    public int cleanupDeadLinks() {
        logger.debug("cleanupDeadLinks() entry.");

        List<AccountBannedId> bans = findDeadLinks();

        int records = 0;
        if(bans.size() > 0) {
            records = em.createNamedQuery("AccountBanned.deleteDeadLinks")
                .setParameter("id", findDeadLinks())
                .executeUpdate();
        }

        logger.debug("cleanupDeadLinks() exit.");
        return records;
    }

    @SuppressWarnings("unchecked")
    public List<AccountBannedId> findDeadLinks() {
        return (List<AccountBannedId>) em.createNamedQuery("AccountBanned.findDeadLinks").getResultList();
    }

    @SuppressWarnings("unchecked")
    public List<AccountBanned> findAll() {
        return (List<AccountBanned>) em.createNamedQuery("AccountBanned.findAll").getResultList();
    }
}
