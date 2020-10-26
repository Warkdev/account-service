package eu.getmangos.controllers;

import java.util.Date;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.slf4j.Logger;

import eu.getmangos.entities.AccountBanned;
import eu.getmangos.entities.AccountBannedId;

@RequestScoped
public class AccountBannedController {
    @Inject private Logger logger;
    @Inject AccountController accountController;

    @PersistenceContext(name = "AUTH_PU")
    private EntityManager em;

    @Transactional
    /**
     * Creates a ban in the dabatase.
     * @param account The account to ban.
     * @throws DAOException Send a DAOException if something happened during the data validation.
     */
    public void create(AccountBanned accountBanned) throws DAOException {
        logger.debug("create() entry.");
        if(accountController.find(accountBanned.getAccountBannedId().getId()) == null) {
            logger.debug("create() exit.");
            throw new DAOException("Account doesn't exist.");
        }
        accountBanned.getAccountBannedId().setBanDate(new Date(System.currentTimeMillis()));
        em.persist(accountBanned);
        logger.debug("create() exit.");
    }

    @Transactional
    /**
     * Updates a ban in the dabatase.
     * @param account The ban to edit.
     * @throws DAOException Send a DAOException if something happened during the data validation.
     */
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

    @Transactional
    /**
     * Delete a ban in the database.
     * @param id The ID of the ban to be deleted.
     * @throws DAOException Send a DAOException if something happened during the data validation.
     */
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

    /**
     * Retrieves a ban by its ID.
     * @param id The ID of the ban
     * @return The ban if found, null otherwise.
     */
    public AccountBanned find(AccountBannedId id) {
        try {
            AccountBanned accountBanned = (AccountBanned) em.createNamedQuery("AccountBanned.findById").setParameter("id", id).getSingleResult();
            logger.debug("search() exit.");
            return accountBanned;
        } catch (NoResultException nre) {
            logger.debug("No ban found with this id.");
            logger.debug("search() exit.");
            return null;
        }
    }

    /**
     * Search all bans for an account.
     * @param name The id of the account.
     * @return The matching bans for the given id.
     */
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

    /**
     * Retrieves all bans from the database.
     * @return A list of Bans.
     */
    @SuppressWarnings("unchecked")
    public List<AccountBanned> findAll() {
        return (List<AccountBanned>) em.createNamedQuery("AccountBanned.findAll").getResultList();
    }
}