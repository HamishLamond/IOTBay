/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uts.isd.group30.model;

import java.sql.Timestamp;

/**
 *
 * @author yash_
 */
public class Payment {
    private String creditCardNumber;
    private String creditCardExpiry;
    private String creditCardCVC;
    private int customerId;
    private Timestamp created;
    private Timestamp lastUpdated;
    private int isDefault;
    public Payment(String creditCardNumber, String creditCardExpiry, String creditCardCVC, int isDefault, int customerId, Timestamp created, Timestamp lastUpdated) {
        this.creditCardCVC = creditCardCVC;
        this.creditCardExpiry = creditCardExpiry;
        this.creditCardNumber = creditCardNumber;
        this.customerId = customerId;
        this.isDefault = isDefault;
        this.created = created;
        this.lastUpdated = lastUpdated;
    }

    public Timestamp getCreated() {
        return created;
    }

    public void setCreated(Timestamp created) {
        this.created = created;
    }

    public void setLastUpdated(Timestamp lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    public Timestamp getLastUpdated() {
        return lastUpdated;
    }

    public void setIsDefault(int isDefault) {
        this.isDefault = isDefault;
    }

    public int getIsDefault() {
        return isDefault;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public int getCustomerId() {
        return customerId;
    }

    
    public String getCreditCardNumber() {
        return creditCardNumber;
    }

    public String getCreditCardExpiry() {
        return creditCardExpiry;
    }

    public String getCreditCardCVC() {
        return creditCardCVC;
    }

    public void setCreditCardNumber(String creditCardNumber) {
        this.creditCardNumber = creditCardNumber;
    }

    public void setCreditCardExpiry(String creditCardExpiry) {
        this.creditCardExpiry = creditCardExpiry;
    }

    public void setCreditCardCVC(String creditCardCVC) {
        this.creditCardCVC = creditCardCVC;
    }
}