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
import uts.isd.group30.model.Customer;
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
            DBManager manager = (DBManager) session.getAttribute("manager");
            //
            if (action.equalsIgnoreCase("viewList")) {
                try {
                    try {
                        Customer customer = (Customer) session.getAttribute("customer");//Gets customer Id to return payments of customer
                        ArrayList<Payment> paymentList = (ArrayList<Payment>) manager.getPaymentList(customer.getId()); //Calls database to get payment methods of customer
                        session.setAttribute("paymentList", paymentList); //Saves payment methods in session
                        request.getRequestDispatcher("viewPaymentList.jsp").forward(request, response);
                    } catch (Exception ex) {
                        session.setAttribute("paymentList", null);//If customer is anonymous send a null response
                        request.getRequestDispatcher("viewPaymentList.jsp").forward(request, response);
                    }
                } catch (Exception ex) {
                    //System.out.print(ex);
                }
            } else if (action.equalsIgnoreCase("delete")) {//Checks what action user wants
                int number = Integer.parseInt(request.getParameter("number")); //Gets index value of the payment method to delete
                ArrayList<Payment> paymentList = (ArrayList<Payment>) session.getAttribute("paymentList"); //Gets the payment array 
                manager.deletePaymentDetails(paymentList.get(number).getCreditCardNumber()); //Calls database to delete the payment
                paymentList.remove(paymentList.get(number)); //Removes payment from session array
                session.setAttribute("paymentList", paymentList); //Return array
                request.getRequestDispatcher("viewPaymentList.jsp").forward(request, response);
            } else if (action.equalsIgnoreCase("update")) {
                int index = Integer.parseInt(request.getParameter("index"));//Gets index number of payment method to update
                ArrayList<Payment> paymentList = (ArrayList<Payment>) session.getAttribute("paymentList");//Gets array from session
                session.setAttribute("oldPayment", paymentList.get(index));//saves payment in session as old payment to pass onto updatejsp
                session.setAttribute("CCNMsg", null);
                session.setAttribute("CCEMsg", null);
                session.setAttribute("CCCVCMsg", null);
                session.setAttribute("SuccessUpdate", null);//Sets all messages to null
                request.getRequestDispatcher("updatePayment.jsp").forward(request, response);
            }
            //
        } catch (Exception ex) {
            System.out.print("Payment Servlet Database access error: " + ex);
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
