/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uts.isd.group30.model;

/**
 *
 * @author yash_
 */
public class Payment {
    private int id;
    private boolean isDefault;
    private String creditCardNumber;
    private String creditCardExpiry;
    private String creditCardCVC;
    private int customerId;
    public Payment(int id, boolean isDefault, String creditCardNumber, String creditCardExpiry, String creditCardCVC, int customerId) {
        this.isDefault = isDefault;
        this.creditCardCVC = creditCardCVC;
        this.creditCardExpiry = creditCardExpiry;
        this.creditCardNumber = creditCardNumber;
        this.customerId = customerId;
    }

    public int getId() {
        return id;
    }

    public boolean isIsDefault() {
        return isDefault;
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

    public void setId(int id) {
        this.id = id;
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