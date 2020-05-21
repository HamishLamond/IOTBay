/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uts.isd.group30.model;
import java.util.Date;
/**
 *
 * @author yash_
 */
public class Transaction {
    private double value;
    private String customerId;
    private int status;
    private Date created;
    private Date lastUpdated;

    public Transaction(double value, String customerId, int status, Date created, Date lastUpdated) {
        this.value = value;
        this.customerId = customerId;
        this.status = status;
        this.created = created;
        this.lastUpdated = lastUpdated;
    }

    public double getValue() {
        return value;
    }

    public String getCustomerId() {
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

    public void setValue(double value) {
        this.value = value;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public void setLastUpdated(Date lastUpdated) {
        this.lastUpdated = lastUpdated;
    }
    
}
