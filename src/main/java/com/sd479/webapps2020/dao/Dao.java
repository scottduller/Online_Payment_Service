/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sd479.webapps2020.dao;

/**
 *
 * @author Scott
 * @param <T>
 */
public interface Dao<T> {

    void persist(T entity);

    void remove(T entity);

    T findById(Long id);

}
