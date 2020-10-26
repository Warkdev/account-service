package eu.getmangos.controllers;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.slf4j.Logger;

import eu.getmangos.entities.Account;

@RequestScoped
public class AccountController {
    @Inject private Logger logger;

    @PersistenceContext(name = "AUTH_PU")
    private EntityManager em;

    @Transactional
    public void create(Account account) {
        em.persist(account);
    }

    public Account find(Integer id) {
        return (Account) em.createNamedQuery("Account.findById").setParameter("id", id).getSingleResult();
    }

    public List<Account> findAll() {
        return (List<Account>) em.createNamedQuery("Account.findAll").getResultList();
    }
}
