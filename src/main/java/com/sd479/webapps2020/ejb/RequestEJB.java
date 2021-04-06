/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sd479.webapps2020.ejb;

import com.sd479.webapps2020.entity.Request;
import com.sd479.webapps2020.entity.SystemUser;
import java.math.BigDecimal;
import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Scott
 */
@Stateless
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class RequestEJB {

    @PersistenceContext
    EntityManager em;

    public List<Request> getRequestsByUser(SystemUser user) {
        List<Request> request = em.createNamedQuery("getRequestsByUser").setParameter("user", user).getResultList();
        return request;
    }

    public Request getRequestById(long id) {
        Request request = em.find(Request.class, id);
        return request;
    }

    public void postRequest(long fromId, long toId, BigDecimal amount) {
        SystemUser from = em.find(SystemUser.class, fromId);
        SystemUser to = em.find(SystemUser.class, toId);

        Request request = new Request(from, to, amount);

        em.persist(request);
    }

    public void deleteRequest(Request request) {
        if (!em.contains(request)) {
            request = em.merge(request);
        }

        em.remove(request);
    }

    public void putRequest(Request request) {
        em.merge(request);
    }
}
