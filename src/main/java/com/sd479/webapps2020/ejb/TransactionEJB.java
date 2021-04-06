/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sd479.webapps2020.ejb;

import com.sd479.webapps2020.entity.SystemUser;
import com.sd479.webapps2020.entity.UserTransaction;
import java.math.BigDecimal;
import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import static javax.ejb.TransactionAttributeType.REQUIRED;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Scott
 */
@Stateless
@TransactionAttribute(REQUIRED)
public class TransactionEJB {

    @PersistenceContext
    EntityManager em;

    public BigDecimal getBalance(long id) {
        SystemUser user = em.find(SystemUser.class, id);
        return user.getBalance();
    }

    public void makePayment(long fromId, long toId, BigDecimal amount) {
        SystemUser from = em.find(SystemUser.class, fromId);
        SystemUser to = em.find(SystemUser.class, toId);

        from.setBalance(from.getBalance().subtract(amount));
        to.setBalance(to.getBalance().add(amount));

        UserTransaction transaction = new UserTransaction(from, to, amount);

        em.persist(from);
        em.persist(to);
        em.persist(transaction);
    }

    public List<UserTransaction> getTransactionsByUser(SystemUser user) {
        List<UserTransaction> transactions = em.createNamedQuery("findTransactionsByUser").setParameter("user", user).getResultList();
        return transactions;
    }
}
