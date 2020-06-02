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
public class lineItem {
    private int deviceId;
    private int quantity;
    private int transactionID;

    public lineItem(int deviceId, int quantity, int transactionID) {
        this.deviceId = deviceId;
        this.quantity = quantity;
        this.transactionID = transactionID;
    }

    public int getDeviceId() {
        return deviceId;
    }

    public int getQuantity() {
        return quantity;
    }

    public int getTransactionID() {
        return transactionID;
    }

    public void setDeviceId(int deviceId) {
        this.deviceId = deviceId;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setTransactionID(int transactionID) {
        this.transactionID = transactionID;
    }
    
}
