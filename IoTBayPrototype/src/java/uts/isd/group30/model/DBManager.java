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
}