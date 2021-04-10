/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sd479.webapps2020.dao;

import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author Scott
 */
public interface SystemUserDao extends Dao {

    List findAllSystemUsers();

    E findSystemUserByUsername(String username);

    void registerSystemUser(E systemUser);

    void updateBalance(E systemUser, BigDecimal newBalance);

}
