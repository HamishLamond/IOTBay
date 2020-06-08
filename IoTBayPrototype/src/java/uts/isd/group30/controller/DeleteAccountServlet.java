/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uts.isd.group30.controller;

import java.io.IOException;
import java.io.PrintWriter;
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
public class DeleteAccountServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        DBManager manager = (DBManager) session.getAttribute("dbmanager");
        RequestDispatcher dispatcher = request.getRequestDispatcher("deleteAccount.jsp");
        if (manager == null) {
            try {
                manager = new DBManager((new DBConnector()).openConnection());
                session.setAttribute("dbmanager", manager);
            } catch (Exception e) {
                //Unspecified error occurred
                return;
            }

        }

        if (session.getAttribute("userType") == "customer") {
            try {
                Customer customer = (Customer) session.getAttribute("customer");
                manager.addAccessLog(new AccessLog(null, customer.getId(), "customerDeletion", LocalDateTime.now()));
                manager.deleteCustomerByEmail(customer.getEmail());
                session.invalidate();
                request.getRequestDispatcher("index.jsp").forward(null, null);
            } catch (Exception e) {
                dispatcher.include(request, response);
            }

        } else {
            try {
                Staff staff = (Staff) session.getAttribute("staff");
                manager.addAccessLog(new AccessLog(null, staff.getId(), "staffDeletion", LocalDateTime.now()));
                manager.deleteStaffByEmail(staff.getEmail());
                session.invalidate();
                request.getRequestDispatcher("index.jsp").forward(null, null);
            } catch (Exception e) {

            }

        }

    }

}
