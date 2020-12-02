package eu.getmangos.dao;

import java.util.List;

import javax.transaction.Transactional;

import eu.getmangos.entities.AccountBanned;

public interface AccountBannedDAO {

    @Transactional
    /**
     * Creates a ban in the dabatase.
     * @param accountBanned The account to ban.
     * @throws DAOException Send a DAOException if something happened during the data validation.
     */
    public void create(AccountBanned accountBanned) throws DAOException;

    @Transactional
    /**
     * Updates a ban in the dabatase.
     * @param id The ID of the ban.
     * @param date The date of the ban.
     * @param accountBanned The ban to edit.
     * @throws DAOException Send a DAOException if something happened during the data validation.
     */
    public void update(Integer id, long date, AccountBanned accountBanned) throws DAOException;

    @Transactional
    /**
     * Delete a ban in the database.
     * @param id The ID of the ban to be deleted.
     * @throws DAOException Send a DAOException if something happened during the data validation.
     */
    public void delete(Integer id, long date) throws DAOException;

    /**
     * Retrieves a ban by its ID.
     * @param id The ID of the ban
     * @return The ban if found, null otherwise.
     */
    public AccountBanned find(Integer id, long date);

    @Transactional
    /**
     * Delete all ban records for a given account.
     * @param accountId The ID of the account for which the ban records needs to be deleted.
     */
    public void deleteForAccount(Integer accountId);

    /**
     * Search all bans for an account.
     * @param id The id of the account.
     * @return The matching bans for the given id.
     */
    public AccountBanned search(Integer id);

    @Transactional
    /**
     * Delete all bans logs from the database which have dead links.
     * @return An int indicating the amount of records impacted by this operation.
     */
    public int cleanupDeadLinks();

    /**
     * Retrieves all bans from the database which are not linked to an account.
     * @return A list of Bans IDS.
     */
    public List<AccountBanned.PrimaryKeys> findDeadLinks();

    /**
     * Retrieves all bans from the database.
     * @return A list of Bans.
     */
    public List<AccountBanned> findAll();
}
