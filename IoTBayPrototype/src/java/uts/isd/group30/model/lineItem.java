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
    private String deviceId;
    private int quantity;
    private String transactionID;

    public lineItem(String deviceId, int quantity, String transactionID) {
        this.deviceId = deviceId;
        this.quantity = quantity;
        this.transactionID = transactionID;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getTransactionID() {
        return transactionID;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setTransactionID(String transactionID) {
        this.transactionID = transactionID;
    }
    
}
