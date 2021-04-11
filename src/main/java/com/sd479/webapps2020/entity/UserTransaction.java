/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sd479.webapps2020.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Scott
 */
@NamedQueries({
    @NamedQuery(name = "findUserTransactionsByUsername", query = "SELECT t FROM UserTransaction t WHERE t.usernameFrom=:username OR t.usernameTo=:username")
})
@Entity
public class UserTransaction implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String usernameFrom;
    private String usernameTo;
    private String currencyFrom;
    private String currencyTo;
    @Column(precision = 12, scale = 2)
    private BigDecimal amountFrom;
    @Column(precision = 12, scale = 2)
    private BigDecimal amountTo;

    @Temporal(TemporalType.TIMESTAMP)
    private Date transactionTimestamp;

    public UserTransaction() {
    }

    public UserTransaction(String usernameFrom, String usernameTo, String currencyFrom, String currencyTo, BigDecimal amountFrom, BigDecimal amountTo) {
        this.usernameFrom = usernameFrom;
        this.usernameTo = usernameTo;
        this.currencyFrom = currencyFrom;
        this.currencyTo = currencyTo;
        this.amountFrom = amountFrom;
        this.amountTo = amountTo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getAmountFrom() {
        return amountFrom;
    }

    public void setAmountFrom(BigDecimal amountFrom) {
        this.amountFrom = amountFrom.setScale(2, RoundingMode.FLOOR);
    }

    public String getUsernameFrom() {
        return usernameFrom;
    }

    public void setUsernameFrom(String usernameFrom) {
        this.usernameFrom = usernameFrom;
    }

    public String getUsernameTo() {
        return usernameTo;
    }

    public void setUsernameTo(String usernameTo) {
        this.usernameTo = usernameTo;
    }

    public BigDecimal getAmountTo() {
        return amountTo;
    }

    public void setAmountTo(BigDecimal amountTo) {
        this.amountTo = amountTo;
    }

    public String getCurrencyFrom() {
        return currencyFrom;
    }

    public void setCurrencyFrom(String currencyFrom) {
        this.currencyFrom = currencyFrom;
    }

    public String getCurrencyTo() {
        return currencyTo;
    }

    public void setCurrencyTo(String currencyTo) {
        this.currencyTo = currencyTo;
    }

    public Date getTransactionTimestamp() {
        return transactionTimestamp;
    }

    public void setTransactionTimestamp(Date transactionTimestamp) {
        this.transactionTimestamp = transactionTimestamp;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + Objects.hashCode(this.id);
        hash = 37 * hash + Objects.hashCode(this.usernameFrom);
        hash = 37 * hash + Objects.hashCode(this.usernameTo);
        hash = 37 * hash + Objects.hashCode(this.currencyFrom);
        hash = 37 * hash + Objects.hashCode(this.currencyTo);
        hash = 37 * hash + Objects.hashCode(this.amountFrom);
        hash = 37 * hash + Objects.hashCode(this.amountTo);
        hash = 37 * hash + Objects.hashCode(this.transactionTimestamp);
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
        final UserTransaction other = (UserTransaction) obj;
        if (!Objects.equals(this.usernameFrom, other.usernameFrom)) {
            return false;
        }
        if (!Objects.equals(this.usernameTo, other.usernameTo)) {
            return false;
        }
        if (!Objects.equals(this.currencyFrom, other.currencyFrom)) {
            return false;
        }
        if (!Objects.equals(this.currencyTo, other.currencyTo)) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.amountFrom, other.amountFrom)) {
            return false;
        }
        if (!Objects.equals(this.amountTo, other.amountTo)) {
            return false;
        }
        if (!Objects.equals(this.transactionTimestamp, other.transactionTimestamp)) {
            return false;
        }
        return true;
    }

}
