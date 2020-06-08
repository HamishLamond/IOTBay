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
import uts.isd.group30.model.Staff;
import uts.isd.group30.model.dao.DBConnector;
import uts.isd.group30.model.dao.DBManager;

/**
 *
 * @author Zunther
 */
public class StaffRegistrationServlet extends HttpServlet {
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
                    return;
                }
            }
            Staff staff = new Staff(name, email, password, parseInt(phone), false, null);
            try
            {
                //Check to make sure email is free
                if(manager.CheckStaffExistsByEmail(email))
                {
                    session.setAttribute("acctExistsErr", "There is already an account using this email!");
                    dispatcher.include(request, response);
                    return;
                }
                else
                {
                    manager.UpsertStaff(staff);
                    staff = manager.getStaffByLoginDetails(email, password);
                    manager.addAccessLog(new AccessLog(staff.getId(), null, "customerCreated", LocalDateTime.now()));
                    session.setAttribute("staff", staff);
                    session.setAttribute("userType", "staff");
                    request.getRequestDispatcher("staffWelcome.jsp");
                }
            }
            catch (SQLException e)
            {
                
            }
        }
}

    private void flushInputErrors() {
        session.setAttribute("regEmailErr", null);
        session.setAttribute("regPhoneErr", null);
        session.setAttribute("regNameErr", null);
        session.setAttribute("regPassErr", null);
        session.setAttribute("acctExistsErr", null);
    }
}
