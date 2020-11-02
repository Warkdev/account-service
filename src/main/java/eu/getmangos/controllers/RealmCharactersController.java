package eu.getmangos.controllers;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.slf4j.Logger;

import eu.getmangos.entities.RealmCharacters;
import eu.getmangos.entities.RealmCharactersID;

@RequestScoped
public class RealmCharactersController {
    @Inject private Logger logger;

    @Inject AccountController accountController;
    @Inject RealmController realmController;

    @PersistenceContext(name = "AUTH_PU")
    private EntityManager em;

    @Transactional
    /**
     * Creates a link between a realm and an account in the dabatase.
     * @param link The link to create. The account must exist otherwise an error is thrown.
     * @throws DAOException Send a DAOException if something happened during the data validation.
     */
    public void create(RealmCharacters link) throws DAOException {
        logger.debug("create() entry.");

        if(accountController.find(link.getId().getAccountID()) == null) {
            logger.debug("create() exit.");
            throw new DAOException("Account doesn't exist.");
        }
        if(find(link.getId()) != null) {
            logger.debug("create() exit.");
            throw new DAOException("Link already exist.");
        }
        em.persist(link);
        logger.debug("create() exit.");
    }

    @Transactional
    /**
     * Updates an link in the dabatase.
     * @param account The link to edit.
     * @throws DAOException Send a DAOException if something happened during the data validation.
     */
    public void update(RealmCharacters link) throws DAOException {
        logger.debug("update() entry.");
        RealmCharacters existing = find(link.getId());
        if(existing == null) {
            logger.debug("update() exit.");
            throw new DAOException("Link doesn't exist.");
        }

        em.merge(link);
        logger.debug("update() exit.");
    }

    @Transactional
    /**
     * Delete a link in the database.
     * @param id The ID of the link to be deleted.
     * @throws DAOException Send a DAOException if something happened during the data validation.
     */
    public void delete(RealmCharactersID id) throws DAOException {
        logger.debug("delete() entry.");

        RealmCharacters link = find(id);
        if(link == null) {
            logger.debug("delete() exit.");
            throw new DAOException("The link doesn't exist");
        }

        em.remove(link);

        logger.debug("delete() exit.");
    }

    @Transactional
    /**
     * Delete all records for the given accountId
     * @param accountId The account ID to be deleted
     * @throws DAOException An exception is raised if there's an issue while performing the deletion.
     */
    public void deleteByAccount(Integer accountId) throws DAOException {
        logger.debug("deleteByAccount() entry.");

        for(RealmCharacters link : findByAccount(accountId)) {
            em.remove(link);
        }

        logger.debug("deleteByAccount() exit.");
    }

    @Transactional
    /**
     * Delete all records for the given realmId
     * @param realmId The realm ID to be deleted
     * @throws DAOException An exception is raised if there's an issue while performing the deletion.
     */
    public void deleteByRealm(Integer realmId) throws DAOException {
        logger.debug("deleteByRealm() entry.");

        for(RealmCharacters link : findByRealm(realmId)) {
            em.remove(link);
        }

        logger.debug("deleteByRealm() exit.");
    }

    /**
     * Retrieves a link by its ID.
     * @param id The ID of the link
     * @return The link if found, null otherwise.
     */
    public RealmCharacters find(RealmCharactersID id) {
        try {
            RealmCharacters link = (RealmCharacters) em.createNamedQuery("RealmCharacters.findById").setParameter("id", id).getSingleResult();
            logger.debug("find() exit.");
            return link;
        } catch (NoResultException nre) {
            logger.debug("No link found with this id.");
            logger.debug("find() exit.");
            return null;
        }
    }

    /**
     * Delete all realm-account links logs from the database which have dead links.
     * @return An int indicating the amount of records impacted by this operation.
     */
    @Transactional
    public int cleanupDeadLinks() {
        logger.debug("cleanupDeadLinks() entry.");

        List<RealmCharacters> links = findDeadLinks();
        int records = 0;
        if(links.size() > 0) {
            for(RealmCharacters link : links) {
                records += em.createNamedQuery("RealmCharacters.deleteDeadLink")
                    .setParameter("accountId", link.getId().getAccountID())
                    .setParameter("realmId", link.getId().getRealmID())
                    .executeUpdate();
            }
        }

        logger.debug("cleanupDeadLinks() exit.");
        return records;
    }

    /**
     * Return a list of links for which the realm ID or the account ID doesnt exist anymore.
     * @return A list of RealmCharacters objects.
     */
    @SuppressWarnings("unchecked")
    public List<RealmCharacters> findDeadLinks() {
        logger.debug("findDeadLinks() entry.");
        List<RealmCharacters> linkList = (List<RealmCharacters>) em.createNamedQuery("RealmCharacters.findDeadLinks").getResultList();
        logger.debug("findDeadLinks() exit.");
        return linkList;
    }

    /**
     * Return a list of links based on the realm ID.
     * @param realmId The realm ID for which the list needs to be found.
     * @return A list of RealmCharacters objects.
     */
    @SuppressWarnings("unchecked")
    public List<RealmCharacters> findByRealm(Integer realmId) {
        logger.debug("findByRealm() entry.");
        List<RealmCharacters> linkList = (List<RealmCharacters>) em.createNamedQuery("RealmCharacters.findByRealm").setParameter("realmID", realmId).getResultList();
        logger.debug("findByRealm() exit.");
        return linkList;
    }

    /**
     * Return a list of links based on the account ID.
     * @param realmId The account ID for which the list needs to be found.
     * @return A list of RealmCharacters objects.
     */
    @SuppressWarnings("unchecked")
    public List<RealmCharacters> findByAccount(Integer accountId) {
        logger.debug("findByAccount() entry.");
        List<RealmCharacters> linkList = (List<RealmCharacters>) em.createNamedQuery("RealmCharacters.findByAccount").setParameter("accountID", accountId).getResultList();
        logger.debug("findByAccount() exit.");
        return linkList;
    }

    /**
     * Retrieves all links from the database.
     * @return A list of links.
     */
    @SuppressWarnings("unchecked")
    public List<RealmCharacters> findAll() {
        return (List<RealmCharacters>) em.createNamedQuery("RealmCharacters.findAll").getResultList();
    }
}
