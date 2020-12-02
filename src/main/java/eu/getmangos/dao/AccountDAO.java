package eu.getmangos.dao;

import java.util.List;

import javax.transaction.Transactional;

import eu.getmangos.entities.Account;

public interface AccountDAO {

    @Transactional
    /**
     * Creates an account in the dabatase.
     * @param account The account to create.
     * @throws DAOException Send a DAOException if something happened during the data validation.
     */
    public void register(Account account) throws DAOException;

    @Transactional
    /**
     * Updates an account in the dabatase.
     * @param account The account to edit.
     * @throws DAOException Send a DAOException if something happened during the data validation.
     */
    public void update(Account account) throws DAOException;

    @Transactional
    /**
     * Delete an account in the database. This method will also delete all links with the bans for this account.
     * @param id The ID of the account to be deleted.
     * @throws DAOException Send a DAOException if something happened during the data validation.
     */
    public void delete(Integer id) throws DAOException;

    /**
     * Retrieves an account by its ID.
     * @param id The ID of the account
     * @return The account if found, null otherwise.
     */
    public Account find(Integer id);

    /**
     * Retrieves a list of accounts based on the provided query string.
     * @param qryString The query String
     * @return A list of accounts matching the criteria.
     */
    public List<Account> findBy(String qryString, Integer page, Integer pageSize);

    /**
     * Search an account by its name
     * @param name The name of the account.
     * @return The matching account for the given name.
     */
    public Account search(String name);

    /**
     * Search an account by its email address
     * @param email The email of the account.
     * @return The matching account for the given email.
     */
    public Account searchByEmail(String email);

    /**
     * Retrieves all accounts from the database.
     * @return A list of Account.
     */
    public List<Account> findAll(Integer page, Integer pageSize);
}
