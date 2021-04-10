/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sd479.webapps2020.jsf;

import com.sd479.webapps2020.dao.SystemUserDao;
import com.sd479.webapps2020.dao.UserTransactionDao;
import com.sd479.webapps2020.entity.SystemUser;
import com.sd479.webapps2020.entity.UserTransaction;
import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

/**
 *
 * @author Scott
 */
@Named(value = "adminUserAccountsBean")
@RequestScoped
public class AdminUserAccountsBean {

    @EJB(name = "systemUserDao")
    SystemUserDao systemUserDao;

    @EJB(name = "userTransactionDao")
    UserTransactionDao userTransactionDao;

    private String userName;
    private SystemUser systemUser;

    public AdminUserAccountsBean() {
    }

    public void setSelectedUser() {
        systemUser = systemUserDao.findSystemUserByUsername(userName);
    }

    public List<SystemUser> getUserList() {
        return systemUserDao.findAllSystemUsers();
    }

    public BigDecimal getUsersBalance() {
        if (systemUser != null) {
            return systemUser.getBalance();
        }
        return null;
    }

    public List<UserTransaction> getUserTransactions() {
        if (systemUser != null) {
            return userTransactionDao.findUserTransactionsByUsername(userName);
        }
        return null;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + Objects.hashCode(this.systemUserDao);
        hash = 37 * hash + Objects.hashCode(this.userTransactionDao);
        hash = 37 * hash + Objects.hashCode(this.userName);
        hash = 37 * hash + Objects.hashCode(this.systemUser);
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
        final AdminUserAccountsBean other = (AdminUserAccountsBean) obj;
        if (!Objects.equals(this.userName, other.userName)) {
            return false;
        }
        if (!Objects.equals(this.systemUserDao, other.systemUserDao)) {
            return false;
        }
        if (!Objects.equals(this.userTransactionDao, other.userTransactionDao)) {
            return false;
        }
        if (!Objects.equals(this.systemUser, other.systemUser)) {
            return false;
        }
        return true;
    }

}
