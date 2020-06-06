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
     public class AddPaymentServlet extends HttpServlet {
     @Override
     protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
             //1- retrieve the current session
             HttpSession session = request.getSession();
             //2- create an instance of the Validator class
             Validators validator = new Validators();
             //3- capture the posted email
             String CCN = request.getParameter("CCN");
             String isUpdate = request.getParameter("isUpdate");
             String CCE = request.getParameter("CCE");
             String CCCVC = request.getParameter("CCCVC");
             int origin = Integer.parseInt(request.getParameter("origin"));
             //4- capture the posted password
             //5- retrieve the manager instance from session
             try {
                DBConnector connector = new DBConnector();
                Connection conn = connector.openConnection();
                DBManager db = new DBManager(conn);
            //DBManager manager = (DBManager) session.getAttribute("manager");
                if(isUpdate.equals("true")){
                    String oldNumber = request.getParameter("oldNumber");
                 if (!validator.validateCreditCardNumber(CCN)) {
                        session.setAttribute("CCNMsg", "Error:Credit card format incorrect");
                        request.getRequestDispatcher("updatePayment.jsp").include(request, response);
                 }
                 else if (!validator.validateCreditCardExpiry(CCE)){
                     session.setAttribute("CCEMsg", "Error:Credit Card Expiry format incorrect");
                     request.getRequestDispatcher("updatePayment.jsp").include(request, response);
                 }
                 else if (!validator.validateCreditCardCVC(CCCVC)){
                     session.setAttribute("CCCVCMsg", "Error:Credit Card CVC format incorrect");
                     request.getRequestDispatcher("updatePayment.jsp").include(request, response);
                 }
                 else{
                     try{
                         System.out.print(origin);
                         db.updatePaymentDetails(oldNumber,CCN, CCE, CCCVC);
                         session.setAttribute("Success", "Successfully updated payment details");
                         request.getRequestDispatcher("updatePayment.jsp").include(request, response);
                     }
                     catch(Exception e){
                         System.out.print(e);
                         session.setAttribute("Success", "Unable to add payment details");
                         request.getRequestDispatcher("updatePayment.jsp").include(request, response);
                     }
                 }
                }
                else{
                 if (!validator.validateCreditCardNumber(CCN)) {
                     session.setAttribute("CCNErr", "Error:Credit card format incorrect");
                     request.getRequestDispatcher("addPayment.jsp").include(request, response);
                 }
                 else if (!validator.validateCreditCardExpiry(CCE)){
                     session.setAttribute("CCEErr", "Error:Credit Card Expiry format incorrect");
                     request.getRequestDispatcher("addPayment.jsp").include(request, response);
                 }
                 else if (!validator.validateCreditCardCVC(CCCVC)){
                     session.setAttribute("CCCVCErr", "Error:Credit Card CVC format incorrect");
                     request.getRequestDispatcher("addPayment.jsp").include(request, response);
                 }
                 else{
                     try{
                         System.out.print(origin);
                         db.addPaymentDetails(CCN, CCE, CCCVC, origin);
                         session.setAttribute("Success", "Successfully added payment details");
                         request.getRequestDispatcher("addPayment.jsp").include(request, response);
                     }
                     catch(Exception e){
                         System.out.print(e);
                         session.setAttribute("Success", "Unable to add payment details");
                         request.getRequestDispatcher("addPayment.jsp").include(request, response);
                     }
                 }
                }
             }
             catch (Exception ex){
                 System.out.print(ex);
             }
     }
     }