/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uts.isd.group30.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import uts.isd.group30.model.AccessLog;
import uts.isd.group30.model.Customer;
import uts.isd.group30.model.Staff;
import uts.isd.group30.model.dao.DBManager;

/**
 *
 * @author Zunther
 */
public class LoginServlet extends HttpServlet {

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession session = request.getSession();
        Validators validator = new Validators();
        
        String email = request.getParameter("email");
        if (email == null || !validator.validateEmail(email))
        {
            //Invalid email! Kick 'em back.
            session.setAttribute("InvalidEmail", true);
        }
        
        String password = request.getParameter("password");
        Boolean isCustomer = request.getParameter("isCustomer") != null;
        Boolean isStaff = request.getParameter("isStaff") != null;
        
        DBManager manager = (DBManager) session.getAttribute("dbmanager");
        Customer customer = null;
        Staff staff = null;
        
        //We attempt an account retrieval with both email and password.
        //Informing user that password is wrong only lets them know that email is registered.
        //Could be used to mount phishing attacks under the guise of legitimate IOTBAY correspondence...
        if (isCustomer)
        {
            try {
                customer = manager.getCustomerByLoginDetails(email, password);
            }
            catch(SQLException e)
            {
                //Something went wrong with the database!
            }
            if (customer == null)
            {
                //Wrong credentials!
                session.setAttribute("InvalidLogin", true);
            }
        }
        else if (isStaff)
        {
            try 
            {
                staff = manager.getStaffByLoginDetails(email, password);
            }
            catch (SQLException e)
            {
                //Something went wrong with the database!
            }
            if (staff == null)
            {
                //Wrong credentials!
                session.setAttribute("InvalidLogin", true);
            }
        }
        //Wrap access log addition with try-catch, leave session login 
        //outside as access log addition failure should not be fatal. 
        //!!!UX uber alles!!!
        if (staff != null)
        {
            try
            {
                manager.addAccessLog(new AccessLog(null, staff.getId(), "staffLogin", new Date()));
            }
            catch(SQLException e)
            {
                //Insertion failed!
            }
            
            session.setAttribute("User", staff);
            session.setAttribute("IsStaff", true);
            //Direct to staff landing page.
        }
        else if (customer != null)
        {
            try
            {
                manager.addAccessLog(new AccessLog(customer.getId(), null, "customerLogin", new Date()));  
            }
            catch(SQLException e)
            {
                //Insertion failed!
            }
            
            session.setAttribute("User", customer);
            session.setAttribute("IsStaff", false);
            //Direct to customer landing page.
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Login servlet. Introduces you to the real world.";
    }// </editor-fold>

}
