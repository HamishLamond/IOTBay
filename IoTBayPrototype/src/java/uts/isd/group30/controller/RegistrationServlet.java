/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uts.isd.group30.controller;

import java.io.IOException;
import static java.lang.Integer.parseInt;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import uts.isd.group30.model.dao.DBManager;

/**
 *
 * @author Zunther
 */
public class RegistrationServlet extends HttpServlet {

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
        Boolean invalidValues = false;
        HttpSession session = request.getSession();

        Validators validator = new Validators();
        
        String email = request.getParameter("email");        
        
        if (!validator.validateEmail(email))
        {
             session.setAttribute("invalidEmail", true);
        }
        try 
        {
            int phone = parseInt(request.getParameter("phone"));
        }
        catch(Exception e)
        {
            //Failed to parse phone!
            session.setAttribute("invalidPhone", true);
        }
        String name = request.getParameter("name");
        Boolean isCustomer = request.getParameter("isCustomer") != null;
        Boolean isStaff = request.getParameter("isStaff") != null;
        String password = request.getParameter("password");
        DBManager manager = (DBManager) session.getAttribute("dbmanager");
        
        if (isCustomer)
        {
            //Validate customer specific info
        }
        else if (isStaff)
        {
            //Validate staff specific info
        }
}

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
