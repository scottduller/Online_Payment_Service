/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sd479.webapps2020.dao;

/**
 *
 * @author Scott
 */
public interface Dao {

    void persist(E entity);

    void remove(E entity);

    E findById(K id);
}
