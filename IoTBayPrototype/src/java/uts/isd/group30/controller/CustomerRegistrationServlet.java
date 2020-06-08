/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uts.isd.group30.controller;

import java.io.IOException;
import static java.lang.Integer.parseInt;
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
import uts.isd.group30.model.dao.DBConnector;
import uts.isd.group30.model.dao.DBManager;

/**
 *
 * @author Zunther
 */
public class CustomerRegistrationServlet extends HttpServlet {
    HttpSession session;
   
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Boolean invalidValues = false;
        session = request.getSession();
        RequestDispatcher dispatcher = request.getRequestDispatcher("registerCustomer.jsp");

        Validators validator = new Validators();
        
        String email = request.getParameter("email");        
        flushInputErrors();
        //Validate email
        if (!validator.validateEmail(email))
        {
             session.setAttribute("regEmailErr", "You have entered an invalid email!");
             invalidValues = true;
        }
        
        //Validate phone
        String phone = request.getParameter("phoneNumber");
        if (!validator.validatePhoneNumber(phone))
        {
            session.setAttribute("regPhoneErr", "You have entered an invalid phone number!");
            invalidValues = true;
        }
        
        //Validate name
        String name = request.getParameter("name");
        if (!validator.validateName(name))
        {
            session.setAttribute("regNameErr", "You have entered an invalid name!");
            invalidValues = true;
        }
        
        //Validate password
        String password = request.getParameter("password");
        if (!validator.validatePassword(password))
        {
            session.setAttribute("regPassErr", "Your password needs to be an alphanumeric of at least four characters!");
            invalidValues = true;
        }
        
        if (invalidValues)
        {
            //If anything's wrong, try again.
            dispatcher.include(request, response);
            return;
        }
        else
        {
            String address = request.getParameter("address");

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
                    dispatcher.include(request, response);
                    return;
                }
            }
            Customer customer = new Customer(name, address, email, parseInt(phone), password);
            try
            {
                //Check to make sure email is free
                //if(manager.CheckCustomerExistsByEmail(email))
                //{
                    //session.setAttribute("acctExistsErr", "There is already an account using this email!");
                    //dispatcher.include(request, response);
                    //return;
                //}
                //else
                {
                    manager.UpsertCustomer(customer);
                    customer = manager.getCustomerByLoginDetails(email, password);
                    manager.addAccessLog(new AccessLog(customer.getId(), null, "customerCreated", LocalDateTime.now()));
                    session.setAttribute("customer", customer);
                    session.setAttribute("userType", "customer");
                    request.getRequestDispatcher("customerWelcome.jsp").forward(request, response);
                }
            }
            catch (SQLException e)
            {
               //Database error 
                dispatcher.include(request, response);
                return;
            }
        }
        
}

    private void flushInputErrors() {
        session.setAttribute("regEmailErr", null);
        session.setAttribute("regPhoneErr", null);
        session.setAttribute("regNameErr", null);
        session.setAttribute("regPassErr", null);
    }

}
