/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sd479.webapps2020.dao;

import com.sd479.webapps2020.entity.Request;
import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import static javax.ejb.TransactionAttributeType.REQUIRED;

/**
 *
 * @author Scott
 */
@Stateless
@TransactionAttribute(REQUIRED)
public class JpaRequestDao extends JpaDao<Request> implements RequestDao {

    @Override
    public List<Request> findRequestsByUsernameTo(String username) {
        return em.createNamedQuery("findRequestsByUsernameTo").setParameter("username", username).getResultList();
    }

}
