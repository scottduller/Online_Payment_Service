/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sd479.webapps2020.dao;

import com.sd479.webapps2020.entity.Request;
import java.util.List;

/**
 *
 * @author Scott
 */
public class JpaRequestDao extends JpaDao<Request> implements RequestDao {

    @Override
    public List<Request> findRequestsByUsernameTo(String username) {
        return em.createNamedQuery("findTransactionsByUsername").setParameter("username", username).getResultList();
    }

}
