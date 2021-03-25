/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sd479.webapps2020.entity;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author Scott
 */
@Entity
public class Transaction implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private SystemUser recievedFrom;
    private SystemUser sentTo;
    private double amountRecieved;
    private double amountSent;

    public Transaction() {
    }

    public Transaction(SystemUser recievedFrom, SystemUser sentTo, double amountRecieved, double amountSent) {
        this.recievedFrom = recievedFrom;
        this.sentTo = sentTo;
        this.amountRecieved = amountRecieved;
        this.amountSent = amountSent;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public SystemUser getRecievedFrom() {
        return recievedFrom;
    }

    public void setRecievedFrom(SystemUser recievedFrom) {
        this.recievedFrom = recievedFrom;
    }

    public SystemUser getSentTo() {
        return sentTo;
    }

    public void setSentTo(SystemUser sentTo) {
        this.sentTo = sentTo;
    }

    public double getAmountRecieved() {
        return amountRecieved;
    }

    public void setAmountRecieved(double amountRecieved) {
        this.amountRecieved = amountRecieved;
    }

    public double getAmountSent() {
        return amountSent;
    }

    public void setAmountSent(double amountSent) {
        this.amountSent = amountSent;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 97 * hash + Objects.hashCode(this.id);
        hash = 97 * hash + Objects.hashCode(this.recievedFrom);
        hash = 97 * hash + Objects.hashCode(this.sentTo);
        hash = 97 * hash + (int) (Double.doubleToLongBits(this.amountRecieved) ^ (Double.doubleToLongBits(this.amountRecieved) >>> 32));
        hash = 97 * hash + (int) (Double.doubleToLongBits(this.amountSent) ^ (Double.doubleToLongBits(this.amountSent) >>> 32));
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
        final Transaction other = (Transaction) obj;
        if (Double.doubleToLongBits(this.amountRecieved) != Double.doubleToLongBits(other.amountRecieved)) {
            return false;
        }
        if (Double.doubleToLongBits(this.amountSent) != Double.doubleToLongBits(other.amountSent)) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.recievedFrom, other.recievedFrom)) {
            return false;
        }
        if (!Objects.equals(this.sentTo, other.sentTo)) {
            return false;
        }
        return true;
    }

}
