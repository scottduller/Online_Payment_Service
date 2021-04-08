/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sd479.webapps2020.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

/**
 *
 * @author Scott
 */
@NamedQueries({
    @NamedQuery(name = "findTransactionsByUser", query = "SELECT t FROM UserTransaction t WHERE t.payementFrom=:user OR t.paymentTo=:user")
})
@Entity
public class UserTransaction implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne
    private SystemUser payementFrom;
    @ManyToOne
    private SystemUser paymentTo;
    @Column(precision = 12, scale = 2)
    private BigDecimal amountFrom;
    @Column(precision = 12, scale = 2)
    private BigDecimal amountTo;

    //add curtecny for each transaction...add positives and negatives...
    public UserTransaction() {
    }

    public UserTransaction(SystemUser payementFrom, SystemUser paymentTo, BigDecimal amountFrom, BigDecimal amountTo) {
        this.payementFrom = payementFrom;
        this.paymentTo = paymentTo;
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

    public SystemUser getPayementFrom() {
        return payementFrom;
    }

    public void setPayementFrom(SystemUser payementFrom) {
        this.payementFrom = payementFrom;
    }

    public SystemUser getPaymentTo() {
        return paymentTo;
    }

    public void setPaymentTo(SystemUser paymentTo) {
        this.paymentTo = paymentTo;
    }

    public BigDecimal getAmountTo() {
        return amountTo;
    }

    public void setAmountTo(BigDecimal amountTo) {
        this.amountTo = amountTo;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 23 * hash + Objects.hashCode(this.id);
        hash = 23 * hash + Objects.hashCode(this.payementFrom);
        hash = 23 * hash + Objects.hashCode(this.paymentTo);
        hash = 23 * hash + Objects.hashCode(this.amountFrom);
        hash = 23 * hash + Objects.hashCode(this.amountTo);
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
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.payementFrom, other.payementFrom)) {
            return false;
        }
        if (!Objects.equals(this.paymentTo, other.paymentTo)) {
            return false;
        }
        if (!Objects.equals(this.amountFrom, other.amountFrom)) {
            return false;
        }
        if (!Objects.equals(this.amountTo, other.amountTo)) {
            return false;
        }
        return true;
    }

}
