/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uts.isd.group30.model;

/**
 *
 * @author Hamish Lamond
 */
public class TransactionLineItem {
    private int id;
    private int transactionId;
    private int deviceId;
    private int quantity;
    private Double cost;

    public TransactionLineItem(int id, int transactionId, int deviceId, int quantity, Double cost) {
        this.id = id;
        this.transactionId = transactionId;
        this.deviceId = deviceId;
        this.quantity = quantity;
        this.cost = cost;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(int transactionId) {
        this.transactionId = transactionId;
    }

    public int getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(int deviceId) {
        this.deviceId = deviceId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Double getCost() {
        return cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }
    
    
    
}
