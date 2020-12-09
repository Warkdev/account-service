package eu.getmangos.dao.impl;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.eclipse.microprofile.reactive.messaging.Outgoing;
import org.reactivestreams.Publisher;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaQuery;

import org.slf4j.Logger;

import eu.getmangos.dao.AccountBannedDAO;
import eu.getmangos.dao.AccountDAO;
import eu.getmangos.dao.DAOException;
import eu.getmangos.dto.AccountEventDTO;
import eu.getmangos.entities.Account;
import eu.getmangos.mapper.AccountMapper;
import io.reactivex.rxjava3.core.BackpressureStrategy;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.FlowableEmitter;

@ApplicationScoped
public class AccountDAOImpl implements AccountDAO {
    @Inject private Logger logger;

    @Inject private AccountBannedDAO accountBannedController;

    @PersistenceContext(unitName = "AUTH_PU")
    private EntityManager em;

    private FlowableEmitter<AccountEventDTO> accountEventEmitter;

    @Inject private AccountMapper mapper;

    public void register(Account account) throws DAOException {
        logger.debug("register() entry.");
        if(account.getUsername().isBlank()) {
            logger.debug("register() exit.");
            throw new DAOException("Username is null or empty.");
        }
        account.setUsername(account.getUsername().toUpperCase());
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

    @Override
    public void update(Account account) throws DAOException {
        logger.debug("update() entry.");
        if(account.getUsername().isBlank()) {
            logger.debug("update() exit.");
            throw new DAOException("Username is null or empty.");
        }
        account.setUsername(account.getUsername().toUpperCase());
        Account existing = searchByEmail(account.getEmail());
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

    @Override
    public void delete(String email) throws DAOException {
        logger.debug("delete() entry.");

        Account account = searchByEmail(email);
        if(account == null) {
            logger.debug("delete() exit.");
            throw new DAOException("The account doesn't exist");
        }

        //wardenController.deleteLogsForAccount(id);
        //realmCharactersController.deleteByAccount(id);
        accountBannedController.deleteForAccount(account.getId());
        em.remove(account);

        AccountEventDTO event = mapper.map(account, AccountEventDTO.Event.DELETE);
        accountEventEmitter.onNext(event);
        logger.debug("delete() exit.");
    }

    @Outgoing("account")
    public Publisher<AccountEventDTO> notifyAccountEvent() {
        Flowable<AccountEventDTO> flowable = Flowable.<AccountEventDTO>create(emitter ->
            this.accountEventEmitter = emitter, BackpressureStrategy.BUFFER);
        return flowable;
    }

    public Account find(Integer id) {
        logger.debug("find() entry.");
        try {
            Account account = (Account) em.createNamedQuery("Account.findById").setParameter("id", id).getSingleResult();
            logger.debug("find() exit.");
            return account;
        } catch (NoResultException nre) {
            logger.debug("No account found with this id.");
            logger.debug("find() exit.");
            return null;
        }
    }

    public List<Account> findBy(String qryString, Integer page, Integer pageSize) {
        logger.debug("findBy() entry.");
        //RSQLVisitor<CriteriaQuery<Account>, EntityManager> visitor = new JpaCriteriaQueryVisitor<>();

        CriteriaQuery<Account> query = null;
        //query = getCriteriaQuery(qryString, visitor);
        logger.debug("Search query: "+query);
        List<Account> list = em.createQuery(query)
                                .setFirstResult((page - 1) * pageSize)
                                .setMaxResults(pageSize)
                                .getResultList();
        if (list == null || list.isEmpty()) {
            return Collections.emptyList();
        }

        logger.debug("findBy() exit.");
        return list;
    }

    public Account search(String name) {
        logger.debug("search() entry.");
        try {
            Account account = (Account) em.createNamedQuery("Account.findByName").setParameter("name", name.toUpperCase()).getSingleResult();
            logger.debug("search() exit.");
            return account;
        } catch (NoResultException nre) {
            logger.debug("No account found with this name.");
            logger.debug("search() exit.");
            return null;
        }
    }

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

    @SuppressWarnings("unchecked")
    public List<Account> findAll(Integer page, Integer pageSize) {
        logger.debug("findAll() entry.");
        logger.debug("findAll() exit.");
        return (List<Account>) em.createNamedQuery("Account.findAll")
                                .setFirstResult((page - 1) * pageSize)
                                .setMaxResults(pageSize)
                                .getResultList();
    }
}
