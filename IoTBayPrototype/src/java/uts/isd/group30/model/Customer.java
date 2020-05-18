/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uts.isd.group30.model;

/**
 *
 * @author USER
 */
public class Customer {
    
    private String id;
    private String name;
    private String address;
    private String email;
    private int phoneNumber;
    private Long creditCardNumber;
    private int creditCardExpiry;
    private int creditCardCVC;
    private String password;

    public Customer() {
    }

    public Customer(String name, String address, String email, int phoneNumber, String password) {
        this.name = name;
        this.address = address;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.password = password;
    }

    public Customer(String id, String name, String address, String email, int phoneNumber, Long creditCardNumber, int creditCardExpiry, int creditCardCVC, String password) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.creditCardNumber = creditCardNumber;
        this.creditCardExpiry = creditCardExpiry;
        this.creditCardCVC = creditCardCVC;
        this.password = password;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Long getCreditCardNumber() {
        return creditCardNumber;
    }

    public void setCreditCardNumber(Long creditCardNumber) {
        this.creditCardNumber = creditCardNumber;
    }

    public int getCreditCardExpiry() {
        return creditCardExpiry;
    }

    public void setCreditCardExpiry(int creditCardExpiry) {
        this.creditCardExpiry = creditCardExpiry;
    }

    public int getCreditCardCVC() {
        return creditCardCVC;
    }

    public void setCreditCardCVC(int creditCardCVC) {
        this.creditCardCVC = creditCardCVC;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    
    
}
