/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uts.isd.group30.model;

import uts.isd.group30.model.Customer;
import java.sql.*;

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
                                                        customer.getCreditCardNumber() + "," + 
                                                        customer.getCreditCardExpiry() + "," + 
                                                        customer.getCreditCardCVC() + "," + 
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
}