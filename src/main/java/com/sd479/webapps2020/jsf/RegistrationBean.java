/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sd479.webapps2020.jsf;

import com.sd479.webapps2020.ejb.AdminEJB;
import com.sd479.webapps2020.ejb.TransactionEJB;
import com.sd479.webapps2020.ejb.UserEJB;
import java.io.Serializable;
import java.math.BigDecimal;
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

    @EJB
    UserEJB userService;
    @EJB
    AdminEJB adminService;
    @EJB
    TransactionEJB transactionService;

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
        BigDecimal balance = transactionService.getCurrencyConversion("GBP", currency, new BigDecimal(1000));
        userService.registerUser(email, firstName, surname, username, password, currency, balance);
        return "index";
    }

    public String registerAdmin() {
        BigDecimal balance = transactionService.getCurrencyConversion("GBP", currency, new BigDecimal(1000));
        adminService.registerAdmin(email, firstName, surname, username, password, currency, balance);
        return "admin";
    }

    public UserEJB getUserService() {
        return userService;
    }

    public void setUserService(UserEJB userService) {
        this.userService = userService;
    }

    public AdminEJB getAdminService() {
        return adminService;
    }

    public void setAdminService(AdminEJB adminService) {
        this.adminService = adminService;
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

    public Currency[] getCurrencies() {
        return currencies;
    }

    public void setCurrencies(Currency[] currencies) {
        this.currencies = currencies;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 59 * hash + Objects.hashCode(this.userService);
        hash = 59 * hash + Objects.hashCode(this.adminService);
        hash = 59 * hash + Objects.hashCode(this.email);
        hash = 59 * hash + Objects.hashCode(this.firstName);
        hash = 59 * hash + Objects.hashCode(this.surname);
        hash = 59 * hash + Objects.hashCode(this.username);
        hash = 59 * hash + Objects.hashCode(this.password);
        hash = 59 * hash + Objects.hashCode(this.currency);
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
        if (!Objects.equals(this.userService, other.userService)) {
            return false;
        }
        if (!Objects.equals(this.adminService, other.adminService)) {
            return false;
        }
        return true;
    }

}
