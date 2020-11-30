package eu.getmangos.dao;

import java.util.List;

import javax.transaction.Transactional;

import eu.getmangos.entities.IpBanned;
import eu.getmangos.entities.IpBannedId;

public interface IpBannedDAO {

    @Transactional
    /**
     * Creates a ban ip in the dabatase.
     * @param ipBanned The ip to ban.
     * @throws DAOException Send a DAOException if something happened during the data validation.
     */
    public void create(IpBanned ipBanned) throws DAOException;

    @Transactional
    /**
     * Updates an ip ban in the dabatase.
     * @param ipBanned The ban to edit.
     * @throws DAOException Send a DAOException if something happened during the data validation.
     */
    public void update(IpBanned ipBanned) throws DAOException;

    @Transactional
    /**
     * Delete a ban in the database.
     * @param id The ID of the ban to be deleted.
     * @throws DAOException Send a DAOException if something happened during the data validation.
     */
    public void delete(IpBannedId id) throws DAOException;

    /**
     * Retrieves a ban by its ID.
     * @param id The ID of the ban
     * @return The ban if found, null otherwise.
     */
    public IpBanned find(IpBannedId id);

    /**
     * Search all bans for an ip.
     * @param ip The ip.
     * @return The matching bans for the given ip.
     */
    public IpBanned search(Integer ip);

    /**
     * Retrieves all ban ip from the database.
     * @return A list of Bans.
     */
    public List<IpBanned> findAll();
}
