/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sd479.webapps2020;

import com.sd479.webapps2020.dao.SystemUserDao;
import com.sd479.webapps2020.dao.SystemUserGroupDao;
import com.sd479.webapps2020.dao.UserTransactionDao;
import com.sd479.webapps2020.entity.SystemUser;
import com.sd479.webapps2020.entity.SystemUserGroup;
import java.math.BigDecimal;
import java.util.Objects;
import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;

/**
 *
 * @author Scott
 */
@Startup
@Singleton
public class Seeder {

    @Inject
    SystemUserDao systemUserDao;
    @Inject
    SystemUserGroupDao systemUserGroupDao;
    @Inject
    UserTransactionDao userTransactionDao;

    @PostConstruct
    public void init() {

        // Initial Admin
        if (systemUserDao.findSystemUserByUsername("admin1") == null) {
            SystemUser newSystemUser = new SystemUser("admin1@admin1.com", "admin1", "admin1", "admin1", "admin1", new BigDecimal(1000), "GBP");
            SystemUserGroup newSystemUserGroup = new SystemUserGroup("admin1", "admins");

            systemUserDao.registerSystemUser(newSystemUser);
            systemUserGroupDao.persist(newSystemUserGroup);
        }
    }

    public SystemUserDao getSystemUserDao() {
        return systemUserDao;
    }

    public void setSystemUserDao(SystemUserDao systemUserDao) {
        this.systemUserDao = systemUserDao;
    }

    public SystemUserGroupDao getSystemUserGroupDao() {
        return systemUserGroupDao;
    }

    public void setSystemUserGroupDao(SystemUserGroupDao systemUserGroupDao) {
        this.systemUserGroupDao = systemUserGroupDao;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 61 * hash + Objects.hashCode(this.systemUserDao);
        hash = 61 * hash + Objects.hashCode(this.systemUserGroupDao);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Seeder other = (Seeder) obj;
        if (!Objects.equals(this.systemUserDao, other.systemUserDao)) {
            return false;
        }
        if (!Objects.equals(this.systemUserGroupDao, other.systemUserGroupDao)) {
            return false;
        }
        return true;
    }

}
