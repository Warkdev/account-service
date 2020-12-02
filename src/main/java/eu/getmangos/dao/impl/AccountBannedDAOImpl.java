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

@ApplicationScoped
public class AccountBannedDAOImpl implements AccountBannedDAO {
    @Inject private Logger logger;
    @Inject AccountDAO accountController;

    @PersistenceContext(unitName = "AUTH_PU")
    private EntityManager em;

    public void create(AccountBanned accountBanned) throws DAOException {
        logger.debug("create() entry.");
        if(accountController.find(accountBanned.getAccountBannedPK().getId()) == null) {
            logger.debug("create() exit.");
            throw new DAOException("Account doesn't exist.");
        }
        accountBanned.getAccountBannedPK().setBanDate(System.currentTimeMillis());
        em.persist(accountBanned);
        logger.debug("create() exit.");
    }

    public void update(Integer id, long date, AccountBanned accountBanned) throws DAOException {
        logger.debug("update() entry.");
        AccountBanned existing = find(id, date);
        if(existing == null) {
            logger.debug("update() exit.");
            throw new DAOException("Ban doesn't exist.");
        }

        accountBanned.setAccountBannedPK(existing.getAccountBannedPK());

        em.merge(accountBanned);
        logger.debug("update() exit.");
    }

    public void delete(Integer id, long date) throws DAOException {
        logger.debug("delete() entry.");

        AccountBanned accountBanned = find(id, date);
        if(accountBanned == null) {
            logger.debug("delete() exit.");
            throw new DAOException("The ban doesn't exist");
        }

        em.remove(accountBanned);

        logger.debug("delete() exit.");
    }

    public AccountBanned find(Integer id, long date) {
        logger.debug("find() entry.");
        try {
            AccountBanned accountBanned = (AccountBanned) em.createNamedQuery("AccountBanned.findById").setParameter("id", new AccountBanned.PrimaryKeys(id, date)).getSingleResult();
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

        List<AccountBanned.PrimaryKeys> bans = findDeadLinks();

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
    public List<AccountBanned.PrimaryKeys> findDeadLinks() {
        return (List<AccountBanned.PrimaryKeys>) em.createNamedQuery("AccountBanned.findDeadLinks").getResultList();
    }

    @SuppressWarnings("unchecked")
    public List<AccountBanned> findAll() {
        return (List<AccountBanned>) em.createNamedQuery("AccountBanned.findAll").getResultList();
    }
}
