/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sd479.webapps2020.jsf;

import com.sd479.webapps2020.dao.SystemUserDao;
import com.sd479.webapps2020.dao.SystemUserGroupDao;
import com.sd479.webapps2020.dao.UserTransactionDao;
import com.sd479.webapps2020.entity.SystemUser;
import com.sd479.webapps2020.entity.SystemUserGroup;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Objects;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

/**
 *
 * @author Scott
 */
@Named(value = "registrationBean")
@RequestScoped
public class RegistrationBean implements Serializable {

    @EJB(name = "systemUserDao")
    SystemUserDao systemUserDao;

    @EJB(name = "systemUserGroupDao")
    SystemUserGroupDao systemUserGroupDao;

    @EJB(name = "userTransactionDao")
    UserTransactionDao userTransactionDao;

    Currency[] currencies = Currency.values();

    String email;
    String firstName;
    String surname;
    String username;
    String password;
    String currency;

    public RegistrationBean() {
    }

    public String registerUser() {
        BigDecimal balance = userTransactionDao.getCurrencyConversion("GBP", currency, new BigDecimal(1000));

        SystemUser newUser = new SystemUser(email, firstName, surname, username, password, balance, currency);
        SystemUserGroup newUserGroup = new SystemUserGroup(username, "users");

        systemUserDao.registerSystemUser(newUser);
        systemUserGroupDao.persist(newUserGroup);

        return "index";
    }

    public String registerAdmin() {
        BigDecimal balance = userTransactionDao.getCurrencyConversion("GBP", currency, new BigDecimal(1000));

        SystemUser newUser = new SystemUser(email, firstName, surname, username, password, balance, currency);
        SystemUserGroup newUserGroup = new SystemUserGroup(username, "admins");

        systemUserDao.registerSystemUser(newUser);
        systemUserGroupDao.persist(newUserGroup);

        return "admin";
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

    public UserTransactionDao getUserTransactionDao() {
        return userTransactionDao;
    }

    public void setUserTransactionDao(UserTransactionDao userTransactionDao) {
        this.userTransactionDao = userTransactionDao;
    }

    public Currency[] getCurrencies() {
        return currencies;
    }

    public void setCurrencies(Currency[] currencies) {
        this.currencies = currencies;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 29 * hash + Objects.hashCode(this.systemUserDao);
        hash = 29 * hash + Objects.hashCode(this.systemUserGroupDao);
        hash = 29 * hash + Objects.hashCode(this.userTransactionDao);
        hash = 29 * hash + Arrays.deepHashCode(this.currencies);
        hash = 29 * hash + Objects.hashCode(this.email);
        hash = 29 * hash + Objects.hashCode(this.firstName);
        hash = 29 * hash + Objects.hashCode(this.surname);
        hash = 29 * hash + Objects.hashCode(this.username);
        hash = 29 * hash + Objects.hashCode(this.password);
        hash = 29 * hash + Objects.hashCode(this.currency);
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
        final RegistrationBean other = (RegistrationBean) obj;
        if (!Objects.equals(this.email, other.email)) {
            return false;
        }
        if (!Objects.equals(this.firstName, other.firstName)) {
            return false;
        }
        if (!Objects.equals(this.surname, other.surname)) {
            return false;
        }
        if (!Objects.equals(this.username, other.username)) {
            return false;
        }
        if (!Objects.equals(this.password, other.password)) {
            return false;
        }
        if (!Objects.equals(this.currency, other.currency)) {
            return false;
        }
        if (!Objects.equals(this.systemUserDao, other.systemUserDao)) {
            return false;
        }
        if (!Objects.equals(this.systemUserGroupDao, other.systemUserGroupDao)) {
            return false;
        }
        if (!Objects.equals(this.userTransactionDao, other.userTransactionDao)) {
            return false;
        }
        if (!Arrays.deepEquals(this.currencies, other.currencies)) {
            return false;
        }
        return true;
    }

}
