/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uts.isd.group30.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import uts.isd.group30.model.Device;
import uts.isd.group30.model.dao.DBManager;

/**
 *
 * @author hoang
 */
public class AddDeviceServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet AddDevice</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AddDevice at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        String name = request.getParameter("name");
        DBManager manager = (DBManager) session.getAttribute("manager");
        try {    
            Device device = manager.getDeviceByName(name);
            if (device!= null){
            session.setAttribute("device", device);
            request.getRequestDispatcher("addDevice.jsp").include(request, response);}
            else{
                request.getRequestDispatcher("main.jsp").include(request, response);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AddDeviceServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

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
        Device device = (Device) session.getAttribute("device");
        int stock = device.getStock();
        String name = device.getName();
        int value = Integer.parseInt(request.getParameter("value"));
        if (value <= stock){
        HashMap <String, Integer> cart = (HashMap <String, Integer>) session.getAttribute("cart");
        cart.put(name,value);
        session.setAttribute("cart", cart);
        session.setAttribute("stockErr","");
        response.sendRedirect("CatalogueServlet?action=list");
        } else {
            session.setAttribute("stockErr","Devices adding to cart exceeded devices in stock");
            request.getRequestDispatcher("addDevice.jsp").forward(request, response);
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
