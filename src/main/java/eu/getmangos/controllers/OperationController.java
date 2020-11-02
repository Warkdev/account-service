package eu.getmangos.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;

import org.slf4j.Logger;

@RequestScoped
public class OperationController {
    @Inject private Logger logger;

    @Inject private AccountBannedController accountBannedController;
    @Inject private RealmCharactersController realmCharactersController;
    @Inject private UptimeController uptimeController;
    @Inject private WardenController wardenController;

    /**
     * Clean-up all dead links from the database.
     * @return An Map containing a list of items and the amount of deleted records.
     */
    @Transactional
    public Map<String, Integer> cleanup() throws DAOException {
        logger.debug("cleanup() entry.");

        Map<String, Integer> deletedItems = new HashMap<>();

        // Clean-up bans
        deletedItems.put("bans", accountBannedController.cleanupDeadLinks());

        // Clean-up links
        deletedItems.put("links", realmCharactersController.cleanupDeadLinks());

        // Clean-up Uptimes
        deletedItems.put("uptime", uptimeController.cleanupDeadLinks());

        // Clean-up Warden Logs
        deletedItems.put("warden_logs", wardenController.cleanupDeadLinks());

        logger.debug("cleanup() exit.");
        return deletedItems;
    }

    /**
     * Retrieves the list of all items which will be deleted by the clean-up operation.
     * @return An Map containing a list of items which will be deleted.
     */
    @Transactional
    public Map<String, List> getCleanup() throws DAOException {
        logger.debug("getCleanup() entry.");

        Map<String, List> deletedItems = new HashMap<>();

        deletedItems.put("bans", accountBannedController.findDeadLinks());
        deletedItems.put("links", realmCharactersController.findDeadLinks());
        deletedItems.put("uptime", uptimeController.findDeadLinks());
        deletedItems.put("warden_logs", wardenController.findDeadLinks());

        logger.debug("getCleanup() exit.");
        return deletedItems;
    }
}
