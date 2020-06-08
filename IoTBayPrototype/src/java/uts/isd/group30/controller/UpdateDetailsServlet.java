/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uts.isd.group30.controller;

import java.io.IOException;
import java.io.PrintWriter;
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
import uts.isd.group30.model.Staff;
import uts.isd.group30.model.dao.DBConnector;
import uts.isd.group30.model.dao.DBManager;

/**
 *
 * @author Zunther
 */
public class UpdateDetailsServlet extends HttpServlet {

    
    HttpSession session;
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        Boolean invalidValues = false;
        session = request.getSession();
        RequestDispatcher dispatcher = request.getRequestDispatcher("updateDetails.jsp");
        Boolean isStaff = session.getAttribute("userType") == "staff";
        Validators validator = new Validators();
               
        flushInputErrors();
        
        //Validate phone
        String phone = request.getParameter("phoneNumber");
        if (!validator.validatePhoneNumber(phone))
        {
            session.setAttribute("upPhoneErr", "You have entered an invalid phone number!");
            invalidValues = true;
        }
        
        //Validate name
        String name = request.getParameter("name");
        if (!validator.validateName(name))
        {
            session.setAttribute("upNameErr", "You have entered an invalid name!");
            invalidValues = true;
        }
        
        //Validate password
        String password = request.getParameter("password");
        if (!validator.validatePassword(password))
        {
            session.setAttribute("upPassErr", "Your password needs to be an alphanumeric of at least four characters!");
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
            if (isStaff)
            {
                String email = ((Staff)session.getAttribute("staff")).getEmail();
                Staff staff = new Staff(name, email, password, parseInt(phone), false, null);
                try
                {
                    manager.UpdateStaff(staff);
                    staff = manager.getStaffByLoginDetails(email, password);
                    manager.addAccessLog(new AccessLog(staff.getId(), null, "staffUpdated", LocalDateTime.now()));
                    session.setAttribute("staff", staff);
                    request.getRequestDispatcher("myDetails.jsp").forward(request, response);
                }
                catch (SQLException e)
                {
                   //Database error 
                    dispatcher.include(request, response);
                    return;
                }
            }
            else
            {
                String email = ((Customer)session.getAttribute("customer")).getEmail();
                String address = request.getParameter("address");
                Customer customer = new Customer(name, address, email, parseInt(phone), password);
                try
                {
                    manager.UpdateCustomer(customer);
                    customer = manager.getCustomerByLoginDetails(email, password);
                    manager.addAccessLog(new AccessLog(customer.getId(), null, "customerUpdated", LocalDateTime.now()));
                    session.setAttribute("customer", customer);
                    request.getRequestDispatcher("myDetails.jsp").forward(request, response);
                }
                catch (SQLException e)
                {
                   //Database error 
                    dispatcher.include(request, response);
                    return;
                }
            }
            
        }

    }
    
    private void flushInputErrors() {
        session.setAttribute("upEmailErr", null);
        session.setAttribute("upPhoneErr", null);
        session.setAttribute("upNameErr", null);
        session.setAttribute("upPassErr", null);
    }

}
