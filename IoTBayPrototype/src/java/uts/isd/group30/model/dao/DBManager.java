/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uts.isd.group30.model.dao;

import java.sql.*;
import java.util.ArrayList;
import uts.isd.group30.model.*;

/* 
* DBManager is the primary DAO class to interact with the database. 
* Complete the existing methods of this classes to perform CRUD operations with the db.
*/

public class DBManager {

    private Statement st;

    public DBManager(Connection conn) throws SQLException {       
       st = conn.createStatement();   
    }

    //Find user by email and password in the database   
    public Customer findCustomer() throws SQLException {       
       //setup the select sql query string       
       //execute this query using the statement field       
       //add the results to a ResultSet       
       //search the ResultSet for a user using the parameters               
       return null;   
    }

    //Add a user-data into the database   
    public void addCustomer() throws SQLException {                   //code for add-operation       
      st.executeUpdate("sql query");   

    }

    //update a user details in the database   
    public void updateCustomer() throws SQLException {       
       //code for update-operation   

    }       

    //delete a user from the database   
    public void deleteCustomer() throws SQLException{       
       //code for delete-operation   
    }
    public void addPaymentDetails() throws SQLException {

    }
    public void deletePaymentDetails() throws SQLException {

    }
    public void updatePaymentDetails() throws SQLException {

    }
    public String fetchPaymentDetails() throws SQLException {
        return "";
    }

    //update a user details in the database   
    public void UpsertCustomer(Customer customer) throws SQLException {        
       String query = "INSERT INTO IOTBAY.CUSTOMER (" + customer.getName() + "," + 
                                                        customer.getAddress() + "," + 
                                                        customer.getEmail() + "," + 
                                                        customer.getPhoneNumber() + "," +
                                                        customer.getPassword() + ")";
        st.executeUpdate(query);   
    }      

    public void UpsertStaff(Staff staff) throws SQLException {        
       String query = "INSERT INTO IOTBAY.STAFF (" + staff.getName() + "," + 
                                                        staff.getEmail() + "," + 
                                                        staff.getPhone() + "," +
                                                        (staff.getIsManager() ? "1" : "0") + "," +
                                                        staff.getPassword() + "," + 
                                                        staff.getManager() + ")";
        st.executeUpdate(query);   
    }       

    //delete a user from the database   
    public void deleteUser(String email) throws SQLException{       
       //code for delete-operation   
       st.executeUpdate("DELETE FROM IOTBAY.CUSTOMER WHERE EMAIL = '" + email +"'");
    }

    public void getAccessCustomerLogsById(String id) throws SQLException {
        st.executeQuery("SELECT * FROM IOTBAY.ACCESSLOG WHERE CUSTOMERID = '" + id + "'");
    }
    
    public Customer getCustomerByLoginDetails (String email, String password) throws SQLException {
        ResultSet results = st.executeQuery("SELECT * FROM IOTBAY.CUSTOMER WHERE EMAIL = '" + email + "'");
        
        
        while(results.next()) {
            String resultEmail = results.getString("customerEmail");
            String resultPassword = results.getString("customerPassword");
            if (resultEmail.equals(resultEmail) && resultPassword.equals(password))
            {
                String id = results.getString(1);
                String customerAddress = results.getString("customerAddress");
                String customerPhone = results.getString("customerPhone");
                String creditCardNumber = results.getString("creditCardNumber");
                String creditCardExpiry = results.getString("creditCardExpiry");
                String creditCardCVC = results.getString("creditCardCVC");
                String customerName = results.getString("customerName");
                String customerPassword = results.getString("customerPassword");
                return new Customer(); 
            }
        }
        return null;
    }
    
    // Adds a transaction to the database
    public void addTransaction(double value, int customerId) throws SQLException {
        st.executeUpdate("INSERT INTO IOTBAY.TRANSACTIONS (TRANSACTIONVALUE, CUSTOMERID,  STATUS) VALUES (" + value + ", '" + customerId + "', 0)");
    }
    
    // Gets a list of transactions associated with the provided customerID
    public ArrayList<Integer> getCustomerTransactions(int customerID) throws SQLException{
        ResultSet results = st.executeQuery("SELECT TRANSACTIONID FROM IOTBAY.TRANSACTIONS WHERE CUSTOMERID=" + customerID);
        ArrayList<Integer> transactions = new ArrayList<>();
        
        while (results.next()) {
            transactions.add(results.getInt("transactionId"));
        }
        return transactions;
    }
    
    // Gets a list of transactions associated with the provided customerID, ordered by last modified date
    public ArrayList<Integer> getCustomerTransactionsByDate(int customerID) throws SQLException{
        ResultSet results = st.executeQuery("SELECT TRANSACTIONID FROM IOTBAY.TRANSACTIONS WHERE CUSTOMERID=" + customerID + "ORDER BY LASTMODIFIED");
        ArrayList<Integer> transactions = new ArrayList<>();
        
        while (results.next()) {
            transactions.add(results.getInt("transactionId"));
        }
        return transactions;
    }
    
    // Retrieves the transaction associated with the provided transactionId
    public Transaction getTransaction(int transactionId) throws SQLException {
        ResultSet result = st.executeQuery("SELECT * FROM IOTBAY.TRANSACTIONS WHERE TRANSACTIONID=" + transactionId);
        
        if (result != null){
            Double transactionValue = result.getDouble("transactionValue");
            int customerId = result.getInt("customerId");
            Timestamp createdOn = result.getTimestamp("createdOn");
            Timestamp lastModified = result.getTimestamp("lastModified");
            int status = result.getInt("status");
            return new Transaction(transactionId, transactionValue, customerId, status, createdOn, lastModified);
        }
        
        return null;
    }
    
    // Updates the value and last modified date of a transaction
    public void updateTransactionValue(int transactionID, double transactionValue) throws SQLException { 
        st.executeUpdate("UPDATE IOTBAY.TRANSACTIONS SET TRANSACTIONVALUE="+ transactionValue + ", LASTMODIFIED=CURRENT_TIMESTAMP WHERE TRANSACTIONID=" + transactionID);
    }
    
    // Updates thes tatus of the provided transaction to 'completed' (1)
    public void completeTransaction(int transactionID) throws SQLException {
        st.executeUpdate("UPDATE IOTBAY.TRANSACTIONS SET STATUS=1 WHERE TRANSACTIONID=" + transactionID);
    }
    
    // Updates the status of the provided transaction to 'cancelled' (2)
    public void cancelTransaction(int transactionID) throws SQLException {
        st.executeUpdate("UPDATE IOTBAY.TRANSACTIONS SET STATUS=2 WHERE TRANSACTIONID=" + transactionID);
    }
    
    // Removes a transaction from the database and any associated transactionlineitems
    public void deleteTransaction(int transactionID) throws SQLException {
        st.executeUpdate("DELETE FROM IOTBAY.TRANSACTIONS WHERE TRANSACTIONID=" + transactionID);
        st.executeUpdate("DELETE FROM IOTBAY.TRANSACTIONLINEITEM WHERE TRANSACTIONID=" + transactionID);
    }
    
    public ArrayList<Device> findDevice(String search) throws SQLException{
        String query ="SELECT * FROM DEVICE WHERE LOWER(DEVICENAME)  LIKE LOWER('%"+search+"%')"; // LIKE Operator is case-sensitive so LOWER make everything to lowercase.
        ResultSet rs = st.executeQuery(query);
        ArrayList<Device> list = new ArrayList();
        while (rs.next()){
            String name = rs.getString(2);
            String description = rs.getString(3);
            double cost = rs.getDouble(4);
            int stock = rs.getInt(5);
            list.add(new Device(name,description,cost,stock,1));
        }
        return list;
    }
    public ArrayList<Device> fetchDevice(char a) throws SQLException{
        String fetch;
        switch (a){
            case 'l':
                fetch = "SELECT * FROM DEVICE";
                break;
            case 'n':
                fetch = "SELECT * FROM DEVICE ORDER BY DEVICENAME";
                break;
            case 'p':
                fetch = "SELECT * FROM DEVICE ORDER BY DEVICECOST";
                break;
            default:
                fetch ="";
                break;
        }
        ResultSet rs = st.executeQuery(fetch);
        ArrayList<Device> list = new ArrayList();
        while (rs.next()){
            String name = rs.getString(2);
            String description = rs.getString(3);
            double cost = rs.getDouble(4);
            int stock = rs.getInt(5);
            list.add(new Device(name,description,cost,stock,1));
        }
        return list;
    }
}