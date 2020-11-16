package eu.getmangos.controllers;

import java.util.Date;
import java.util.List;

import org.eclipse.microprofile.reactive.messaging.Message;
import org.eclipse.microprofile.reactive.messaging.Outgoing;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.slf4j.Logger;

import eu.getmangos.dto.AccountEventDTO;
import eu.getmangos.entities.Account;
import eu.getmangos.mapper.AccountMapper;

@ApplicationScoped
public class AccountController {
    @Inject private Logger logger;

    @Inject private AccountBannedController accountBannedController;

    @PersistenceContext(name = "AUTH_PU")
    private EntityManager em;

    @Inject private AccountMapper mapper;

    @Transactional
    /**
     * Creates an account in the dabatase.
     * @param account The account to create.
     * @throws DAOException Send a DAOException if something happened during the data validation.
     */
    public void register(Account account) throws DAOException {
        logger.debug("register() entry.");
        if(account.getUsername().isBlank()) {
            logger.debug("register() exit.");
            throw new DAOException("Username is null or empty.");
        }
        if(search(account.getUsername()) != null) {
            logger.debug("register() exit.");
            throw new DAOException("Account already exist.");
        }
        if(searchByEmail(account.getEmail()) != null) {
            logger.debug("register() exit.");
            throw new DAOException("An account with this email address already exist.");
        }
        account.setJoinDate(new Date(System.currentTimeMillis()));
        account.setShaPassHash("DEPRECATED");
        account.setLastIP("0.0.0.0");
        account.setFailedLogins(0);
        account.setLocked(false);
        account.setLastLogin(account.getJoinDate());
        account.setActiveRealmId(0);
        account.setMutetime(0L);
        account.setPlayerBot(false);

        em.persist(account);
        logger.debug("register() exit.");
    }

    @Transactional
    /**
     * Updates an account in the dabatase.
     * @param account The account to edit.
     * @throws DAOException Send a DAOException if something happened during the data validation.
     */
    public void update(Account account) throws DAOException {
        logger.debug("update() entry.");
        if(account.getUsername().isBlank()) {
            logger.debug("update() exit.");
            throw new DAOException("Username is null or empty.");
        }
        Account existing = search(account.getUsername());
        if(existing == null) {
            logger.debug("update() exit.");
            throw new DAOException("Account doesn't exist.");
        }

        if(existing.getId() != account.getId()) {
            logger.debug("update() exit.");
            throw new DAOException("The provided account doesn't match the account to be updated.");
        }
        em.merge(account);
        logger.debug("update() exit.");
    }

    /**
     * Delete an account in the database. This method will also delete all links with the bans for this account.
     * @param id The ID of the account to be deleted.
     * @throws DAOException Send a DAOException if something happened during the data validation.
     */
    @Transactional
    @Outgoing("account")
    public Message<AccountEventDTO> delete(Integer id) throws DAOException {
        logger.debug("delete() entry.");

        Account account = find(id);
        if(account == null) {
            logger.debug("delete() exit.");
            throw new DAOException("The account doesn't exist");
        }

        //wardenController.deleteLogsForAccount(id);
        //realmCharactersController.deleteByAccount(id);
        accountBannedController.deleteForAccount(id);
        em.remove(account);

        logger.debug("delete() exit.");

        return Message.of(mapper.map(account, AccountEventDTO.Event.DELETE));
    }

    /**
     * Retrieves an account by its ID.
     * @param id The ID of the account
     * @return The account if found, null otherwise.
     */
    public Account find(Integer id) {
        try {
            Account account = (Account) em.createNamedQuery("Account.findById").setParameter("id", id).getSingleResult();
            logger.debug("search() exit.");
            return account;
        } catch (NoResultException nre) {
            logger.debug("No account found with this id.");
            logger.debug("search() exit.");
            return null;
        }
    }

    /**
     * Search an account by its name
     * @param name The name of the account.
     * @return The matching account for the given name.
     */
    public Account search(String name) {
        logger.debug("search() entry.");
        try {
            Account account = (Account) em.createNamedQuery("Account.findByName").setParameter("name", name).getSingleResult();
            logger.debug("search() exit.");
            return account;
        } catch (NoResultException nre) {
            logger.debug("No account found with this name.");
            logger.debug("search() exit.");
            return null;
        }
    }

    /**
     * Search an account by its email address
     * @param email The email of the account.
     * @return The matching account for the given email.
     */
    public Account searchByEmail(String email) {
        logger.debug("searchByEmail() entry.");
        try {
            Account account = (Account) em.createNamedQuery("Account.findByEmail").setParameter("email", email).getSingleResult();
            logger.debug("searchByEmail() exit.");
            return account;
        } catch (NoResultException nre) {
            logger.debug("No account found with this email.");
            logger.debug("searchByEmail() exit.");
            return null;
        }

    }

    /**
     * Retrieves all accounts from the database.
     * @return A list of Account.
     */
    @SuppressWarnings("unchecked")
    public List<Account> findAll() {
        return (List<Account>) em.createNamedQuery("Account.findAll").getResultList();
    }
}
