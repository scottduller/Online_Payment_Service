/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sd479.webapps2020.dao;

import java.lang.reflect.ParameterizedType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Scott
 */
public abstract class JpaDao implements Dao {

    Class entityClass;

    @PersistenceContext
    EntityManager em;

    public JpaDao() {
        ParameterizedType genericSuperclass = (ParameterizedType) getClass().getGenericSuperclass();
        this.entityClass = (Class) genericSuperclass.getActualTypeArguments()[1];
    }

    @Override
    public void persist(E entity) {
        em.persist(entity);
    }

    @Override
    public void remove(E entity) {
        em.remove(entity);
    }

    @Override
    public Object findById(Object id) {
        return em.find(entityClass, id);
    }

}
