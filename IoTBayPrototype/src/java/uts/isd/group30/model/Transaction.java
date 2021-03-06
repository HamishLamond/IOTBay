/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uts.isd.group30.model;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.Date;

/**
 *
 * @author yash_
 */
public class Transaction {

    private int id;
    private double value;
    private int customerId;
    private int status;
    private Timestamp created;
    private Timestamp lastUpdated;
    private BigInteger cardNumber;

    public Transaction(int id, double value, int customerId, int status, Timestamp created, Timestamp lastUpdated) {
        this.id = id;
        this.value = value;
        this.customerId = customerId;
        this.status = status;
        this.created = created;
        this.lastUpdated = lastUpdated;
    }

    public Transaction(int id, double value, int customerId, int status, Timestamp created, Timestamp lastUpdated, BigInteger cardNumber) {
        this.id = id;
        this.value = value;
        this.customerId = customerId;
        this.status = status;
        this.created = created;
        this.lastUpdated = lastUpdated;
        this.cardNumber = cardNumber;
    }

    public int getId() {
        return id;
    }

    public double getValue() {
        return value;
    }

    public int getCustomerId() {
        return customerId;
    }

    public int getStatus() {
        return status;
    }

    public Date getCreated() {
        return created;
    }

    public Date getLastUpdated() {
        return lastUpdated;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public void setCreated(Timestamp created) {
        this.created = created;
    }

    public void setLastUpdated(Timestamp lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    public BigInteger getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(BigInteger cardNumber) {
        this.cardNumber = cardNumber;
    }
}
