/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uts.isd.group30.controller;

import java.sql.*;

import java.util.*;

import java.util.logging.*;
import uts.isd.group30.model.Customer;
import uts.isd.group30.model.Device;
import uts.isd.group30.model.dao.DBConnector;
import uts.isd.group30.model.dao.DBManager;

public class TestDB {

    private DBConnector connector;
    private Connection conn;
    private DBManager db;
    private static Scanner in = new Scanner(System.in);

    public static void main(String[] args) {
        (new TestDB()).runQueries();
    }

    public TestDB() {
        try {
            connector = new DBConnector();
            conn = connector.openConnection();
            db = new DBManager(conn);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(TestDB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private char readChoice() {
        System.out.println("Operation CRUDS or * to exit: ");
        return in.nextLine().charAt(0);
    }

    private void runQueries() {
        char c;
        while ((c = readChoice()) != '*') {
            switch (c) {
                case 'R':
                    testRead();
                    break;
                case 'A':
                    testFindDevice();
                    break;
                case 'B':
                    testGetDevice();
                    break;
                case 'S':
                    showAll();
                    break;
                default:
                    System.out.println("Unknown Command");
                    break;
            }
        }
    }

    private void testRead() {
        System.out.print("User email: ");
        String email = in.nextLine();
        System.out.print("User password: ");
        String password = in.nextLine();
        try {
            Customer customer = db.getCustomerByLoginDetails(email, password);
            if (customer != null) {
                System.out.println("success.");
            } else {
                System.out.println("fail");
            }
        } catch (SQLException ex) {
            Logger.getLogger(TestDB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void testFindDevice(){
        System.out.print("Search for name: ");
        String search = in.nextLine();
        try {
            ArrayList<Device> devices = db.findDevice(search);
            System.out.println("DEVICE TABLE: ");
            devices.stream().forEach((device) -> {
                System.out.printf("%-20s %-30s %-20s %-10s \n", device.getName(), device.getDescription(), device.getStock(), device.getCost());
            });
        } catch (SQLException ex) {
            Logger.getLogger(TestDB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void testGetDevice(){
        System.out.print("Find device by name: ");
        String find = in.nextLine();
        try {
            Device device = db.getDeviceByName(find);
            if (device != null){
                System.out.printf("%-20s %-30s %-20s %-10s \n", device.getName(), device.getDescription(), device.getStock(), device.getCost());
            }else{
                System.out.println("fail");
            }
        } catch (SQLException ex) {
            Logger.getLogger(TestDB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    private void showAll(){
        try {
            ArrayList<Device> devices = db.fetchDevice('l');
            System.out.println("DEVICE TABLE: ");
            devices.stream().forEach((device) -> {
                System.out.printf("%-20s %-30s %-20s %-10s \n", device.getName(), device.getDescription(), device.getStock(), device.getCost());
            });
        } catch (SQLException ex) {
            Logger.getLogger(TestDB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
