/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sd479.webapps2020.entity;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Scott
 */
@NamedQueries({
    @NamedQuery(name = "findAllUsers", query = "SELECT u FROM SystemUser u"),
    @NamedQuery(name = "findUserByUsername", query = "SELECT u FROM SystemUser u WHERE u.username LIKE :username")
})

@Entity
public class SystemUser implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    String email;

    @NotNull
    String firstName;

    @NotNull
    String surname;

    @Column(unique = true)
    @NotNull
    String username;

    @NotNull
    String password;

    @NotNull
    double balance;

    @NotNull
    String currency;

    //TRANSACTIONS NOT WORKING
    public SystemUser() {
    }

    public SystemUser(String email, String firstName, String surname, String username, String password, double balance, String currency) {
        this.email = email;
        this.firstName = firstName;
        this.surname = surname;
        this.username = username;
        this.password = password;
        this.balance = balance;
        this.currency = currency;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.id);
        hash = 97 * hash + Objects.hashCode(this.email);
        hash = 97 * hash + Objects.hashCode(this.firstName);
        hash = 97 * hash + Objects.hashCode(this.surname);
        hash = 97 * hash + Objects.hashCode(this.username);
        hash = 97 * hash + Objects.hashCode(this.password);
        hash = 97 * hash + (int) (Double.doubleToLongBits(this.balance) ^ (Double.doubleToLongBits(this.balance) >>> 32));
        hash = 97 * hash + Objects.hashCode(this.currency);
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
        final SystemUser other = (SystemUser) obj;
        if (Double.doubleToLongBits(this.balance) != Double.doubleToLongBits(other.balance)) {
            return false;
        }
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
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

}
