/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uts.isd.group30.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import uts.isd.group30.model.AccessLog;
import uts.isd.group30.model.Customer;
import uts.isd.group30.model.Staff;
import uts.isd.group30.model.dao.DBConnector;
import uts.isd.group30.model.dao.DBManager;


public class LoginServlet extends HttpServlet {

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession session = request.getSession();
        Validators validator = new Validators();
        RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
        
        String email = request.getParameter("email");
        if (email == null || !validator.validateEmail(email))
        {
            //Invalid email! Kick 'em back.
            session.setAttribute("InvalidLogin", "The email address you have entered is invalid!");
            dispatcher.include(request, response);
            return;
        }
        
        String password = request.getParameter("password");
        String user = request.getParameter("user");
        
        DBManager manager = (DBManager) session.getAttribute("dbmanager");
        if (manager == null)
        {
            try
            {
                manager = new DBManager((new DBConnector()).openConnection());
                session.setAttribute("dbmanager", manager);
            }
            catch(Exception e)
            {
                //Unspecified error occurred
                return;
            }
            
        }
        Customer customer = null;
        Staff staff = null;
        
        //We attempt an account retrieval with both email and password.
        //Informing user that password is wrong only lets them know that email is registered.
        //Could be used to mount phishing attacks under the guise of legitimate IOTBAY correspondence...
        if (user.equals("customer"))
        {
            try {
                customer = manager.getCustomerByLoginDetails(email, password);
            }
            catch(SQLException e)
            {
                //Something went wrong with the database!
            }
            catch(Exception e)
            {
            }
            if (customer == null)
            {
                //Wrong credentials!
                session.setAttribute("InvalidLogin", "The credentials you have entered are invalid!");
                dispatcher.include(request, response);
                return;
            }
        }
        else if (user.equals("staff"))
        {
            try 
            {
                staff = manager.getStaffByLoginDetails(email, password);
            }
            catch (SQLException e)
            {
                //Something went wrong with the database!
            }
            catch(Exception e)
            {
            }
            if (staff == null)
            {
                //Wrong credentials!
                session.setAttribute("InvalidLogin", "The credentials you have entered are invalid!");
                dispatcher.include(request, response);
                return;
            }
        }
        //Wrap access log addition with try-catch, leave session login 
        //outside as access log addition failure should not be fatal. 
        //!!!UX uber alles!!!
        if (staff != null)
        {
            try
            {
                manager.addAccessLog(new AccessLog(null, staff.getId(), "staffLogin", LocalDateTime.now()));
            }
            catch(SQLException e)
            {
                //Insertion failed!
            }
            
            session.setAttribute("staff", staff);
            session.setAttribute("userType", "staff");
            session.setAttribute("InvalidLogin", null);
            request.getRequestDispatcher("staffWelcome.jsp").forward(request, response);
            //Direct to staff landing page.
        }
        else if (customer != null)
        {
            try
            {
                manager.addAccessLog(new AccessLog(customer.getId(), null, "customerLogin", LocalDateTime.now()));  
            }
            catch(SQLException e)
            {
                //Insertion failed!
            }
            
            session.setAttribute("customer", customer);
            session.setAttribute("userType", "customer");
            session.setAttribute("InvalidLogin", null);
            request.getRequestDispatcher("customerWelcome.jsp").forward(request, response);
            //Direct to customer landing page.
        }
        else
        {
            session.setAttribute("InvalidLogin", "An unspecified error has occurred!");
            dispatcher.include(request, response);
        }
    }

}
