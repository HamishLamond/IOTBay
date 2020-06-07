/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uts.isd.group30.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import uts.isd.group30.model.Payment;
import uts.isd.group30.model.dao.DBConnector;
import uts.isd.group30.model.dao.DBManager;

/**
 *
 * @author yash_
 */
public class PaymentServlet extends HttpServlet {

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
            out.println("<title>Servlet PaymentServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet PaymentServlet at " + request.getContextPath() + "</h1>");
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
        String action = request.getParameter("action");
            
            try {
                DBConnector connector = new DBConnector();
                Connection conn = connector.openConnection();
                DBManager manager = new DBManager(conn);
                //DBManager manager = (DBManager) session.getAttribute("manager");
                //
                    if (action.equalsIgnoreCase("viewList")){
                        try {
                            int origin = Integer.parseInt(request.getParameter("origin"));
                            ArrayList<Payment> paymentList = (ArrayList<Payment>) manager.getPaymentList(origin);
                            session.setAttribute("paymentList", paymentList);
                            request.getRequestDispatcher("viewPaymentList.jsp").forward(request, response);
                        } 
                        catch (Exception ex) {
                            System.out.print(ex);
                        }
                    }
                    else if(action.equalsIgnoreCase("delete")){
                        String number = request.getParameter("number");
                        manager.deletePaymentDetails(number);
                        int origin = Integer.parseInt(request.getParameter("origin"));
                        ArrayList<Payment> paymentList = (ArrayList<Payment>) manager.getPaymentList(origin);
                        session.setAttribute("paymentList", paymentList);
                        request.getRequestDispatcher("viewPaymentList.jsp").forward(request, response);
                    }
                    else if(action.equalsIgnoreCase("update")){
                        String creditCardNumber = request.getParameter("CCN");
                        String creditCardExpiry = request.getParameter("CCE");
                        String creditCardCVC = request.getParameter("CCCVC");
                        int isDefault = Integer.parseInt(request.getParameter("Default"));
                        if (isDefault==1){
                            session.setAttribute("chckMsg", 1);
                        }
                        else{
                            session.setAttribute("ckckMsg", 0);
                        }
                        int customerId = Integer.parseInt(request.getParameter("CId"));
                        Payment oldPayment = new Payment(creditCardNumber, creditCardExpiry, creditCardCVC, isDefault, customerId);
                        session.setAttribute("oldPayment", oldPayment);
                        session.setAttribute("CCNMsg", null);
                        session.setAttribute("CCEMsg", null);
                        session.setAttribute("CCCVCMsg", null);
                        session.setAttribute("SuccessUpdate", null);
                        request.getRequestDispatcher("updatePayment.jsp").forward(request, response);
                    }
                //
            }
            catch (Exception ex){
                System.out.print(ex);
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
