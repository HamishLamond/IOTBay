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
public class Staff {
    private int id;
    private String name;
    private String email;
    private String password;
    private int phone;
    private Boolean isManager;
    private int manager;

    public Staff(int id, String name, String email, String password, int phone, Boolean isManager, int manager) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.isManager = isManager;
        this.manager = manager;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public int getPhone() {
        return phone;
    }

    public Boolean getIsManager() {
        return isManager;
    }

    public int getManager() {
        return manager;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public void setIsManager(Boolean isManager) {
        this.isManager = isManager;
    }

    public void setManager(int manager) {
        this.manager = manager;
    }
    
}
