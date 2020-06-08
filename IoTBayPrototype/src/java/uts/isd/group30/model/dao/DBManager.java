/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uts.isd.group30.model.dao;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
        
    public void addPaymentDetails(String creditCardNumber, String creditCardExpiry, String creditCardCVC, int isDefaultValue, int customerId) throws SQLException {
        st.executeUpdate("INSERT INTO IOTBAY.PAYMENT (creditCardNumber, creditCardExpiry, creditCardCVC, isDefault, customerId) VALUES ('" + creditCardNumber + "', '" + creditCardExpiry + "', '" + creditCardCVC + "'," + isDefaultValue + "," + customerId + ")");
    }
    public void deletePaymentDetails(String id) throws SQLException {
        st.executeUpdate("DELETE FROM IOTBAY.PAYMENT WHERE creditCardNumber='" + id + "'");
    }
    public void updatePaymentDetails(String id, String newId, String newExpiry, String newCVC, int isDefaultValue) throws SQLException {
        st.executeUpdate("UPDATE IOTBAY.PAYMENT SET creditCardNumber='" + newId + "', creditCardExpiry='" + newExpiry + "', creditCardCVC='" + newCVC + "', isDefault=" + isDefaultValue + ", lastUpdated=CURRENT_TIMESTAMP WHERE creditCardNumber='" + id + "'");
    }
    public Payment getPaymentDetails(String CCN, int customerId) throws SQLException {
        try {
            ResultSet results = st.executeQuery("SELECT * FROM IOTBAY.PAYMENT WHERE creditCardNumber = '" + CCN + "'");
            return new Payment(results.getString("creditCardNumber"), results.getString("creditCardExpiry"), results.getString("creditCardCVC"), results.getInt("isDefault"), results.getInt("customerId"), results.getTimestamp("createdOn"), results.getTimestamp("lastUpdated"));
        }
        catch (Exception ex){
            return null;
        }
    }
    public ArrayList<Payment> getPaymentList(int customerId) throws SQLException {
        try {
            ResultSet results = st.executeQuery("SELECT * FROM IOTBAY.PAYMENT WHERE CUSTOMERID=" + customerId);
            ArrayList<Payment> paymentList = new ArrayList<Payment>();
            while (results.next()) {
                paymentList.add(new Payment(results.getString("creditCardNumber"), results.getString("creditCardExpiry"), results.getString("creditCardCVC"), results.getInt("isDefault"), results.getInt("customerId"), results.getTimestamp("createdOn"), results.getTimestamp("lastUpdated")));
            }
            return paymentList;
        }
        catch (Exception ex){
            return null;
        }
    }
    
    
    public boolean CheckCustomerExistsByEmail(String email) throws SQLException
    {
        ResultSet results = st.executeQuery("SELECT COUNT(*) FROM IOTBAY.CUSTOMER AS TOTAL WHERE CUSTOMEREMAIL = '" + email + "'");
        
        if (results.getInt(1) > 0)
        {
            return true;
        }
        return false;
    }
    
    public boolean CheckStaffExistsByEmail(String email) throws SQLException
    {
        ResultSet results = st.executeQuery("SELECT COUNT(*) FROM IOTBAY.STAFF AS TOTAL WHERE STAFFEMAIL = '" + email + "'");
        
        if (results.getInt(1) > 0)
        {
            return true;
        }
        return false;
    }
    
    public void UpsertStaff(Staff staff) throws SQLException {        
   String query = "INSERT INTO IOTBAY.STAFF ("
           + "staffName, "
           + "staffEmail,"
           + "staffPhone,"
           + "rank,"
           + "staffPassword,"
           + "staffManager) VALUES (" + 

           "'" + staff.getName() + "'" + ", " + 
            "'" + staff.getEmail() + "'" + ", " + 
            staff.getPhone() + ", " +
            (staff.getIsManager() ? 1 : 0) + ", " +
            "'" + staff.getPassword() + "'" + ", " + 
            staff.getManager() + ")";
        st.execute(query);
    }
            
    public Payment getDefaultPayment(int customerId) throws SQLException {
        try{
            ResultSet results = st.executeQuery("SELECT * FROM IOTBAY.PAYMENT WHERE isDefault=1 and customerId=" + customerId);
            while (results.next()){
                if (results.getInt("isDefault")==1){
                    return new Payment(results.getString("creditCardNumber"), results.getString("creditCardExpiry"), results.getString("creditCardCVC"), results.getInt("isDefault"), results.getInt("customerId"), results.getTimestamp("createdOn"), results.getTimestamp("lastUpdated"));
                }
            }
            return null;
        }
        catch (Exception ex){
            return null;
        }
        
    }

    //update a user details in the database   
    public void UpsertCustomer(Customer customer) throws SQLException {        
       String query = "INSERT INTO IOTBAY.CUSTOMER ("
               + "customerName,"
               + "customerAddress,"
               + "customerEmail,"
               + "customerPhone,"
               + "customerPassword) VALUES (" + 
               
               "'" + customer.getName() + "'" + "," + 
                "'" + customer.getAddress() + "'" + "," + 
                "'" + customer.getEmail() + "'" + "," + 
                customer.getPhoneNumber() + "," +
                "'" + customer.getPassword() + "'" + ")";
        st.executeUpdate(query);   
    }   

    public void UpdateStaff(Staff staff) throws SQLException {        
       String query = "UPDATE IOTBAY.STAFF SET "
               + "staffName = " + "'" + staff.getName() + "', " 
               + "staffPhone = " + staff.getPhone() + ", " 
               + "rank = " + (staff.getIsManager() ? 1 : 0) + ", "
               + "staffPassword = " + "'" + staff.getPassword() + "', "
               + "staffManager = " + staff.getManager() + "  " 
               + "WHERE STAFFEMAIL = '" + staff.getEmail() + "'";
        st.executeUpdate(query);   
    }   

    public void UpdateCustomer(Customer customer) throws SQLException {        
       String query = "UPDATE IOTBAY.CUSTOMER SET "
               + "customerName = " + "'" + customer.getName() + "', " 
               + "customerPhone = " + customer.getPhoneNumber() + ", " 
               + "customerAddress = '" + customer.getAddress() + "', "
               + "customerPassword = " + "'" + customer.getPassword() + "' "
               + "WHERE CUSTOMEREMAIL = '" + customer.getEmail() + "'";
        st.executeUpdate(query);   
    }     

    //delete a user from the database   
    public void deleteCustomerByEmail(String email) throws SQLException{       
       //code for delete-operation   
       st.execute("DELETE FROM IOTBAY.CUSTOMER WHERE CUSTOMEREMAIL = '" + email +"'");
    }  
    
    //delete a user from the database   
    public void deleteStaffByEmail(String email) throws SQLException{       
       //code for delete-operation   
       st.execute("DELETE FROM IOTBAY.STAFF WHERE STAFFEMAIL = '" + email +"'");
    }  
    
    public void addAccessLog(AccessLog accessLog) throws SQLException
    {
        String t = accessLog.getTimeStamp().toString();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS Z");
        String query = "INSERT INTO IOTBAY.ACCESSLOG ("
                + "customerId, "
                + "eventType, "
                + "staffId,"
                + "logTime) VALUES (" + 
                
                accessLog.getCustomerId() + ", " +
                "'" + accessLog.getEventType() + "'" + ", " +
                accessLog.getStaffId() + ", " +
                "'" + Timestamp.valueOf(accessLog.getTimeStamp()) + "'" + ")";
        st.execute(query);
    }
    
    public ArrayList<AccessLog> getStaffAccessLogsByUserId(int staffId) throws SQLException
    {
        ResultSet results = st.executeQuery("Select * FROM IOTBAY.ACCESSLOG WHERE CUSTOMERID = '" + staffId + "' ORDER BY LOGTIME DESC");
        
        ArrayList<AccessLog> accessLogs = new ArrayList<>();
        
        while (results.next()) {
            accessLogs.add(new AccessLog(results.getInt("accessLogId"), 
                    results.getInt("customerId"), 
                    results.getInt("staffId"), 
                    results.getString("eventType"),
                    LocalDateTime.parse(results.getString("logTime"))));
        }
        return accessLogs;
    }
    
    public ArrayList<AccessLog> getCustomerAccessLogsByUserId(int customerId) throws SQLException
    {
        ResultSet results = st.executeQuery("Select * FROM IOTBAY.ACCESSLOG WHERE CUSTOMERID = '" + customerId + "' ORDER BY LOGTIME DESC");
        
        ArrayList<AccessLog> accessLogs = new ArrayList<>();
        
        while (results.next()) {
            accessLogs.add(new AccessLog(results.getInt("accessLogId"), 
                    results.getInt("customerId"), 
                    results.getInt("staffId"), 
                    results.getString("eventType"),
                    LocalDateTime.parse(results.getString("logTime"))));
        }
        return accessLogs;
    }
    
    
    public Customer getCustomerByLoginDetails (String email, String password) throws SQLException {
        ResultSet results = st.executeQuery("SELECT * FROM IOTBAY.CUSTOMER WHERE CUSTOMEREMAIL = '" + email + "'");
        
        
        while(results.next()) {
            String customerEmail = results.getString("customerEmail");
            String customerPassword = results.getString("customerPassword");
            if (customerEmail.equals(email) && customerPassword.equals(password))
            {
                int id = results.getInt("customerId");
                String customerAddress = results.getString("customerAddress");
                int customerPhone = results.getInt("customerPhone");
                String customerName = results.getString("customerName");
                return new Customer(id,
                                    customerName,
                                    customerAddress,
                                    customerEmail,
                                    customerPhone,
                                    customerPassword); 
            }
        }
        return null;
    }
    
    public Staff getStaffByLoginDetails (String email, String password) throws SQLException {
        ResultSet results = st.executeQuery("SELECT * FROM IOTBAY.STAFF WHERE STAFFEMAIL = '" + email + "'");
        
        
        while(results.next()) {
            String staffEmail = results.getString("staffEmail");
            String staffPassword = results.getString("staffPassword");
            
            
            if (staffEmail.equals(email) && staffPassword.equals(password))
            {
                int staffManager = results.getInt("staffManager");
                int staffPhone = results.getInt("staffPhone");
                String staffName = results.getString("staffName");
                int staffId = results.getInt("staffId");
                Boolean isManager = results.getInt("rank") == 1;
                return new Staff(staffId,
                                    staffName,
                                    staffEmail,
                                    staffPassword,
                                    staffPhone,
                                    isManager,
                                    staffManager); 
            }
        }
        return null;
    }
    
    // Adds a transaction to the database and returns its ID
    public int addTransaction(double value, int customerId) throws SQLException {
        st.executeUpdate("INSERT INTO IOTBAY.TRANSACTIONS (TRANSACTIONVALUE, CUSTOMERID,  STATUS) VALUES (" + value + ", " + customerId + ", 0)");
        ResultSet results = st.executeQuery("SELECT TRANSACTIONID FROM IOTBAY.TRANSACTIONS WHERE CUSTOMERID=" + customerId + " ORDER BY TRANSACTIONID DESC");
        results.next();
        return results.getInt("transactionId");
    }
    
    // Gets a list of transactions associated with the provided customerID
    public ArrayList<Integer> getCustomerTransactionIDs(int customerID) throws SQLException{
        ResultSet results = st.executeQuery("SELECT TRANSACTIONID FROM IOTBAY.TRANSACTIONS WHERE CUSTOMERID=" + customerID);
        ArrayList<Integer> transactions = new ArrayList<>();
        
        while (results.next()) {
            transactions.add(results.getInt("transactionId"));
        }
        return transactions;
    }
    
    // Gets a list of transactions associated with the provided customerID, ordered by last modified date
    public ArrayList<Integer> getCustomerTransactionIDsByDate(int customerID) throws SQLException{
        ResultSet results = st.executeQuery("SELECT TRANSACTIONID FROM IOTBAY.TRANSACTIONS WHERE CUSTOMERID=" + customerID + " ORDER BY LASTMODIFIED");
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
    
    public ArrayList<Transaction> getCustomerTransactions(int customerId) throws SQLException {
        ResultSet rs = st.executeQuery("SELECT * FROM IOTBAY.TRANSACTIONS WHERE CUSTOMERID=" + customerId);
        ArrayList<Transaction> list = new ArrayList();
        while (rs.next()){
            int transactionId = rs.getInt(1);
            Double transactionValue = rs.getDouble(2);
            Timestamp createdOn = rs.getTimestamp(4);
            Timestamp lastModified = rs.getTimestamp(5);
            int status = rs.getInt(6);
            list.add(new Transaction(transactionId, transactionValue, customerId, status, createdOn, lastModified));
        }
        return list;
        
    }
    
    public Double getTransactionValue(int transactionId) throws SQLException{
        ResultSet rs = st.executeQuery("SELECT TRANSACTIONVALUE FROM IOTBAY.TRANSACTIONS WHERE TRANSACTIONID=" + transactionId); 
        rs.next();
        return rs.getDouble(1);
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
    
    public void addTransactionLineItem(int transactionId, int deviceId, int quantity, Double cost) throws SQLException{
        st.executeUpdate("INSERT INTO IOTBAY.TRANSACTIONLINEITEM (TRANSACTIONID, DEVICEID, QUANTITY, COST) VALUES (" + transactionId + ", " + deviceId + 
                ", " + quantity + ", " + cost + ")");
    }
    
    public ArrayList<TransactionLineItem> getTransactionLineItems(int transactionId) throws SQLException {
        ResultSet rs = st.executeQuery("SELECT * FROM IOTBAY.TRANSACTIONLINEITEM WHERE TRANSACTIONID=" + transactionId);
        ArrayList<TransactionLineItem> items = new ArrayList();
        while (rs.next()){
            int id = rs.getInt(1);
            int deviceId = rs.getInt(3);
            int quantity = rs.getInt(4);
            double cost = rs.getDouble(5);
            items.add(new TransactionLineItem(id, transactionId, deviceId, quantity, cost));
        }
        return items;       
    }
    
    public String getDeviceName(int deviceId) throws SQLException{
        String query = "SELECT DEVICENAME FROM IOTBAY.DEVICE WHERE DEVICEID=" + deviceId;
        ResultSet rs = st.executeQuery(query);
        while (rs.next()){
            return rs.getString(1);
        }
        return null;
    }
    
    public int getDeviceIDByName (String name) throws SQLException{
        ResultSet result = st.executeQuery("SELECT * FROM IOTBAY.DEVICE WHERE DEVICENAME='" + name + "'");
        while (result.next()){
            return result.getInt("deviceId");
        }
        return 0;
    }
    
    public Device getDeviceByName (String name1) throws SQLException{
        ResultSet result = st.executeQuery("SELECT * FROM IOTBAY.DEVICE WHERE DEVICENAME='" + name1+"'"); 
        while (result.next()){
        String name = result.getString(2);
        String desc = result.getString(3);
        Double cost = result.getDouble(4);
        int stock = result.getInt(5);
        int threshold = result.getInt(6);
        return new Device(name, desc, cost, stock, threshold);
        }
        return null;
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
            int threshold = rs.getInt(6);
            list.add(new Device(name,description,cost,stock,threshold));
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
            int threshold = rs.getInt(6);
            list.add(new Device(name,description,cost,stock,threshold));
        }
        return list;
    }
}