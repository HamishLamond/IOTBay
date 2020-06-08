/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uts.isd.group30.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import uts.isd.group30.model.Payment;

/**
 *
 * @author yash_
 */
public class searchPaymentServlet extends HttpServlet {

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
            out.println("<title>Servlet searchPaymentServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet searchPaymentServlet at " + request.getContextPath() + "</h1>");
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
        HttpSession session = request.getSession();
        String searchRequest = request.getParameter("searchBox");
        String searchOption = request.getParameter("searchOption");
        ArrayList<Payment> paymentSearch = new ArrayList<Payment>();//Defines new array
        ArrayList<Payment> paymentList = (ArrayList<Payment>) session.getAttribute("paymentList");//Gets array of all payment methods which is saved in session
        if (searchOption.equals("creditCardNumberSearch")) {//Checks which option is selected for search
            for (int i = 0; i < paymentList.size(); i++) {
                if (paymentList.get(i).getCreditCardNumber().equals(searchRequest)) {
                    paymentSearch.add(paymentList.get(i));//Searches through payment method array for searchbox value and adds it to another array of payments
                }
            }
            session.setAttribute("paymentSearch", paymentSearch);//Returns array of found payments
            request.getRequestDispatcher("viewPaymentSearch.jsp").include(request, response);
        } else if (searchOption.equals("dateSearch")) {//Checks which option is selected for search
            String[] searchRequestSplit = searchRequest.split("-");
            for (int i = 0; i < paymentList.size(); i++) { //iterates through all customer payments
                Timestamp created = paymentList.get(i).getCreated();//Gets timetamps for payments
                Timestamp updated = paymentList.get(i).getLastUpdated();
                if ((created.getMonth() == Integer.parseInt(searchRequestSplit[1])) | (created.getDate() == Integer.parseInt(searchRequestSplit[0])) | (created.getYear() == Integer.parseInt(searchRequestSplit[2]))) {
                    //converts timestamps into ints for comparison
                    paymentSearch.add(paymentList.get(i));//adds any found values to array
                } else if ((updated.getMonth() == Integer.parseInt(searchRequestSplit[1])) | (updated.getDate() == Integer.parseInt(searchRequestSplit[0])) | (updated.getYear() == Integer.parseInt(searchRequestSplit[2]))) {
                    //converts timestamps into int for comparison for updated date
                    paymentSearch.add(paymentList.get(i));//adds any found values to array
                }
            }
            session.setAttribute("paymentSearch", paymentSearch);
            request.getRequestDispatcher("viewPaymentSearch.jsp").include(request, response);
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
