  package uts.isd.group30.controller;

 

  import java.io.IOException;
  import java.sql.Connection;
  import javax.servlet.ServletException;
  import javax.servlet.http.HttpServlet;
  import javax.servlet.http.HttpServletRequest;
  import javax.servlet.http.HttpServletResponse;
  import javax.servlet.http.HttpSession;
import uts.isd.group30.model.Payment;
  import uts.isd.group30.model.dao.*;
     public class PaymentDetailsServlet extends HttpServlet {
     @Override
     protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
             //1- retrieve the current session
             HttpSession session = request.getSession();
             //2- create an instance of the Validator class
             Validators validator = new Validators();
             //3- capture the posted email
             int paymentId = Integer.parseInt(request.getParameter("paymentId"));
             int origin = Integer.parseInt(request.getParameter("origin"));
             //4- capture the posted password
             //5- retrieve the manager instance from session
             try {
                DBConnector connector = new DBConnector();
                Connection conn = connector.openConnection();
                DBManager db = new DBManager(conn);
            //DBManager manager = (DBManager) session.getAttribute("manager");
                try{
                    System.out.print(origin);
                    Payment payment = db.getPaymentDetails(origin, paymentId);
                    session.setAttribute("Payment", payment);
                    request.getRequestDispatcher("viewPaymentDetails.jsp").include(request, response);
                }
                catch(Exception e){
                    System.out.print(e);
                    session.setAttribute("Success", "Unable to add payment details");
                    request.getRequestDispatcher("addPayment.jsp").include(request, response);
                }
             }
             catch (Exception ex){
                 ;
             }
     }
     }