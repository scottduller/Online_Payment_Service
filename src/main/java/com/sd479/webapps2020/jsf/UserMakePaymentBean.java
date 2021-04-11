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
import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;

/**
 *
 * @author Scott
 */
@Named(value = "userMakePaymentBean")
@RequestScoped
public class UserMakePaymentBean {

    @EJB(name = "systemUserDao")
    SystemUserDao systemUserDao;

    @EJB(name = "userTransactionDao")
    UserTransactionDao userTransactionDao;

    private String userNameTo;
    private BigDecimal amount;

    public UserMakePaymentBean() {
    }

    public String getUserNameTo() {
        return userNameTo;
    }

    public void setUserNameTo(String userNameTo) {
        this.userNameTo = userNameTo;
    }

    @RolesAllowed("users")
    public List<SystemUser> getUserList() {
        SystemUser currentUser = getLoggedInUser();

        List<SystemUser> users = systemUserDao.findAllSystemUsers();
        users.remove(currentUser);

        return users;
    }

    @RolesAllowed("users")
    public SystemUser getLoggedInUser() {
        FacesContext context = FacesContext.getCurrentInstance();
        context.getExternalContext().getRemoteUser();

        String currentUserUsername = context.getExternalContext().getRemoteUser();

        SystemUser currentUser = systemUserDao.findSystemUserByUsername(currentUserUsername);

        return currentUser;
    }

    @RolesAllowed("users")
    public void makePayment() {
        SystemUser from = getLoggedInUser();
        SystemUser to = systemUserDao.findSystemUserByUsername(userNameTo);

        BigDecimal convertedAmount = userTransactionDao.getCurrencyConversion(from.getCurrency(), to.getCurrency(), amount);

        BigDecimal fromNewBalance = from.getBalance().subtract(amount);
        BigDecimal toNewBalance = to.getBalance().add(convertedAmount);

        from.setBalance(fromNewBalance);
        to.setBalance(toNewBalance);

        UserTransaction transaction = new UserTransaction(from.getUsername(), to.getUsername(), from.getCurrency(), to.getCurrency(), amount, convertedAmount);

        systemUserDao.update(from);
        systemUserDao.update(to);
        userTransactionDao.persistWithTimestamp(transaction);

    }

    @RolesAllowed("users")
    public SystemUserDao getSystemUserDao() {
        return systemUserDao;
    }

    @RolesAllowed("users")
    public void setSystemUserDao(SystemUserDao systemUserDao) {
        this.systemUserDao = systemUserDao;
    }

    @RolesAllowed("users")
    public UserTransactionDao getUserTransactionDao() {
        return userTransactionDao;
    }

    @RolesAllowed("users")
    public void setUserTransactionDao(UserTransactionDao userTransactionDao) {
        this.userTransactionDao = userTransactionDao;
    }

    @RolesAllowed("users")
    public BigDecimal getAmount() {
        return amount;
    }

    @RolesAllowed("users")
    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 83 * hash + Objects.hashCode(this.systemUserDao);
        hash = 83 * hash + Objects.hashCode(this.userTransactionDao);
        hash = 83 * hash + Objects.hashCode(this.userNameTo);
        hash = 83 * hash + Objects.hashCode(this.amount);
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
        final UserMakePaymentBean other = (UserMakePaymentBean) obj;
        if (!Objects.equals(this.userNameTo, other.userNameTo)) {
            return false;
        }
        if (!Objects.equals(this.systemUserDao, other.systemUserDao)) {
            return false;
        }
        if (!Objects.equals(this.userTransactionDao, other.userTransactionDao)) {
            return false;
        }
        if (!Objects.equals(this.amount, other.amount)) {
            return false;
        }
        return true;
    }

}
