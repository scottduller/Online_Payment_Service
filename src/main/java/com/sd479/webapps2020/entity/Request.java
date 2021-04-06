/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sd479.webapps2020.entity;

import java.io.Serializable;
import java.math.BigDecimal;
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
    @NamedQuery(name = "getRequestsByUser", query = "SELECT r FROM Request r WHERE r.requestTo=:user")
})
@Entity
public class Request implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne
    private SystemUser requestFrom;
    @ManyToOne
    private SystemUser requestTo;
    @Column(precision = 12, scale = 2)
    private BigDecimal amount;

    public Request() {
    }

    public Request(SystemUser requestFrom, SystemUser requestTo, BigDecimal amount) {
        this.requestFrom = requestFrom;
        this.requestTo = requestTo;
        this.amount = amount;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public SystemUser getRequestFrom() {
        return requestFrom;
    }

    public void setRequestFrom(SystemUser requestFrom) {
        this.requestFrom = requestFrom;
    }

    public SystemUser getRequestTo() {
        return requestTo;
    }

    public void setRequestTo(SystemUser requestTo) {
        this.requestTo = requestTo;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 97 * hash + Objects.hashCode(this.id);
        hash = 97 * hash + Objects.hashCode(this.requestFrom);
        hash = 97 * hash + Objects.hashCode(this.requestTo);
        hash = 97 * hash + Objects.hashCode(this.amount);
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
        final Request other = (Request) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.requestFrom, other.requestFrom)) {
            return false;
        }
        if (!Objects.equals(this.requestTo, other.requestTo)) {
            return false;
        }
        if (!Objects.equals(this.amount, other.amount)) {
            return false;
        }
        return true;
    }

}
