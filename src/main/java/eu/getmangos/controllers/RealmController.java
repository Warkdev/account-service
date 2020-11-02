package eu.getmangos.controllers;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.slf4j.Logger;

import eu.getmangos.entities.Realm;

@RequestScoped
public class RealmController {
    @Inject private Logger logger;

    @Inject private RealmCharactersController realmCharactersController;
    @Inject private UptimeController uptimeController;

    @PersistenceContext(name = "AUTH_PU")
    private EntityManager em;

    @Transactional
    /**
     * Creates a realm in the dabatase.
     * @param realm The realm to create.
     * @throws DAOException Send a DAOException if something happened during the data validation.
     */
    public void create(Realm realm) throws DAOException {
        logger.debug("create() entry.");
        if(realm.getName().isBlank()) {
            logger.debug("create() exit.");
            throw new DAOException("Realm name is null or empty.");
        }
        if(search(realm.getName()) != null) {
            logger.debug("create() exit.");
            throw new DAOException("Realm already exist.");
        }
        em.persist(realm);
        logger.debug("create() exit.");
    }

    @Transactional
    /**
     * Updates an realm in the dabatase.
     * @param realm The realm to edit.
     * @throws DAOException Send a DAOException if something happened during the data validation.
     */
    public void update(Realm realm) throws DAOException {
        logger.debug("update() entry.");
        if(realm.getName().isBlank()) {
            logger.debug("create() exit.");
            throw new DAOException("Realm name is null or empty.");
        }
        Realm existing = search(realm.getName());
        if(existing == null) {
            logger.debug("update() exit.");
            throw new DAOException("Realm doesn't exist.");
        }

        if(existing.getId() != realm.getId()) {
            logger.debug("update() exit.");
            throw new DAOException("The provided realm doesn't match the realm to be updated.");
        }
        em.merge(realm);
        logger.debug("update() exit.");
    }

    @Transactional
    /**
     * Delete a realm in the database. This method will also delete all links with the accounts for this realm.
     * @param id The ID of the realm to be deleted.
     * @throws DAOException Send a DAOException if something happened during the data validation.
     */
    public void delete(Integer id) throws DAOException {
        logger.debug("delete() entry.");

        Realm realm = find(id);
        if(realm == null) {
            logger.debug("delete() exit.");
            throw new DAOException("The realm doesn't exist");
        }

        uptimeController.deleteUptimeForRealm(id);
        realmCharactersController.deleteByRealm(id);
        em.remove(realm);

        logger.debug("delete() exit.");
    }

    /**
     * Retrieves an realm by its ID.
     * @param id The ID of the realm
     * @return The realm if found, null otherwise.
     */
    public Realm find(Integer id) {
        try {
            Realm realm = (Realm) em.createNamedQuery("Realm.findById").setParameter("id", id).getSingleResult();
            logger.debug("search() exit.");
            return realm;
        } catch (NoResultException nre) {
            logger.debug("No realm found with this id.");
            logger.debug("search() exit.");
            return null;
        }
    }

    /**
     * Search an realm by its name
     * @param name The name of the realm.
     * @return The matching realm for the given name.
     */
    public Realm search(String name) {
        logger.debug("search() entry.");
        try {
            Realm realm = (Realm) em.createNamedQuery("Realm.findByName").setParameter("name", name).getSingleResult();
            logger.debug("search() exit.");
            return realm;
        } catch (NoResultException nre) {
            logger.debug("No realm found with this name.");
            logger.debug("search() exit.");
            return null;
        }

    }

    /**
     * Retrieves all realms from the database.
     * @return A list of Realm.
     */
    @SuppressWarnings("unchecked")
    public List<Realm> findAll() {
        return (List<Realm>) em.createNamedQuery("Realm.findAll").getResultList();
    }
}
