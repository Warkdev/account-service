package eu.getmangos.dao;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;

import eu.getmangos.entities.WardenLog;

@ApplicationScoped
public interface WardenDAO {

    /**
     * Retrieves all warden logs from the database.
     * @return A list of records containing the warden logs for all servers.
     */
    public List<WardenLog> getAllLogs();

    /**
     * Delete all warden logs from the database which have dead links.
     * @return A list of records containing the warden logs for all servers.
     */
    @Transactional
    public int cleanupDeadLinks();

    /**
     * Retrieves all warden logs from the database which have dead links.
     * @return A list of records containing the warden logs for all servers.
     */
    public List<WardenLog> findDeadLinks();

    /**
     * Retrieves a log entry by its id.
     * @param entry The log entry id.
     * @return The log entry.
     */
    public WardenLog search(Integer entry);

    /**
     * Delete warden logs for the given id.
     * @param entry The Id of the log to be deleted.
     */
    @Transactional
    public void delete(Integer entry);

    /**
     * Delete warden logs for the given account id.
     * @param accountId The Id of the account for which the logs needs to be deleted.
     */
    @Transactional
    public void deleteLogsForAccount(Integer accountId);

    /**
     * Retrieves all warden logs from the database for a given account.
     * @param accountId The Id of the account for which the warden logs needs to be retrieved.
     * @return A list of records containing the logs for a single account.
     */
    public List<WardenLog> getWardenLogForAccount(Integer accountId);
}
