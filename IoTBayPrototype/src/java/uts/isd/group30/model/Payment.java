/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uts.isd.group30.model;

import java.math.BigInteger;

/**
 *
 * @author Hamish Lamond
 */
public class Payment {
    private BigInteger cardNumber;
    private int cardExpiry;
    private int cardCVC;
    private int customerID;

    public Payment(BigInteger cardNumber, int cardExpiry, int cardCVC) {
        this.cardNumber = cardNumber;
        this.cardExpiry = cardExpiry;
        this.cardCVC = cardCVC;
    }

    public BigInteger getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(BigInteger cardNumber) {
        this.cardNumber = cardNumber;
    }

    public int getCardExpiry() {
        return cardExpiry;
    }

    public void setCardExpiry(int cardExpiry) {
        this.cardExpiry = cardExpiry;
    }

    public int getCardCVC() {
        return cardCVC;
    }

    public void setCardCVC(int cardCVC) {
        this.cardCVC = cardCVC;
    }

    public int getCustomerID() {
        return customerID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }
    
    
}
