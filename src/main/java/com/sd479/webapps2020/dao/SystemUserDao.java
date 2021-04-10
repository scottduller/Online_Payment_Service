/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sd479.webapps2020.dao;

import com.sd479.webapps2020.entity.SystemUser;
import java.util.List;

/**
 *
 * @author Scott
 */
public interface SystemUserDao extends Dao<SystemUser> {

    List<SystemUser> findAllSystemUsers();

    SystemUser findSystemUserByUsername(String username);

    void registerSystemUser(SystemUser systemUser);

    void update(SystemUser systemUser);

}
