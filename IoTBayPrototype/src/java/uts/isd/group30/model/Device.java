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
public class Device {

    private int id;
    private String name;
    private String description;
    private double cost;
    private int stock;
    private int stockWarnThreshold;

    public Device(String name, String description, double cost, int stock, int stockWarnThreshold) {
        this.name = name;
        this.description = description;
        this.cost = cost;
        this.stock = stock;
        this.stockWarnThreshold = stockWarnThreshold;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public double getCost() {
        return cost;
    }

    public int getStock() {
        return stock;
    }

    public int getStockWarnThreshold() {
        return stockWarnThreshold;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public void setStockWarnThreshold(int stockWarnThreshold) {
        this.stockWarnThreshold = stockWarnThreshold;
    }

}
