/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uts.isd.group30.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
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
 * @author Hamish Lamond
 */
public class CurrentOrderServlet extends HttpServlet {

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
            out.println("<title>Servlet CurrentOrderServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet CurrentOrderServlet at " + request.getContextPath() + "</h1>");
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
        DBManager manager = (DBManager) session.getAttribute("manager");
        HashMap<String, Integer> cart = (HashMap<String, Integer>) session.getAttribute("cart");
        Double totalCost = 0.0;
        try {
//            TreeMap<Device, Integer> cartDevices = new TreeMap<>();
            ArrayList<Device> deviceArray = new ArrayList();
            ArrayList<Integer> deviceNumbers = new ArrayList();
            for (Map.Entry<String, Integer> entry : cart.entrySet()){
                String deviceName = entry.getKey();
                int numberOfDevices = entry.getValue();
                Device device = manager.getDeviceByName(deviceName);
                deviceArray.add(device);
                deviceNumbers.add(numberOfDevices);
                //cartDevices.put(device, numberOfDevices);
                totalCost += device.getCost() * numberOfDevices;
            }
            request.setAttribute("cart", cart);
            //request.setAttribute("cartDevices", cartDevices);
            request.setAttribute("deviceArray", deviceArray);
            request.setAttribute("deviceNumbers", deviceNumbers);
            request.setAttribute("totalCost", totalCost);
            request.getRequestDispatcher("currentOrder.jsp").forward(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(CatalogueServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        processRequest(request, response);
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
        processRequest(request, response);
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
