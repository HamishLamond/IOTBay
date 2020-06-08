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
        Validators validator = new Validators();
        //3- capture the posted email
        String CCN = request.getParameter("CCN");
        String isUpdate = request.getParameter("isUpdate");
        String CCE = request.getParameter("CCE");
        String tempPayment = request.getParameter("tempPayment");
        String CCCVC = request.getParameter("CCCVC");
        String Checkbox = request.getParameter("Chkbox");
        //Gets information from the view page
        int isDefault = 0;
        if (Checkbox != null) {
            isDefault = 1;//Converts checkbox value into Int value for the isDefault field
        }
        int origin = Integer.parseInt(request.getParameter("origin"));//Gets customerId
        try {
            DBManager db = (DBManager) session.getAttribute("manager");
            if (isUpdate.equals("true")) {
                String oldNumber = request.getParameter("oldNumber");
                if (!validator.validateCreditCardNumber(CCN)) {
                    session.setAttribute("CCNMsg", "Error:Credit card format incorrect");
                    request.getRequestDispatcher("updatePayment.jsp").include(request, response);
                } else if (!validator.validateCreditCardExpiry(CCE)) {
                    session.setAttribute("CCEMsg", "Error:Credit Card Expiry format incorrect");
                    request.getRequestDispatcher("updatePayment.jsp").include(request, response);
                } else if (!validator.validateCreditCardCVC(CCCVC)) {
                    session.setAttribute("CCCVCMsg", "Error:Credit Card CVC format incorrect");
                    request.getRequestDispatcher("updatePayment.jsp").include(request, response);
                } else {
                    try {
                        if (isDefault == 1) {//Checks to see is customer wants this as new default
                            Payment defaultPaymentId = db.getDefaultPayment(origin);
                            try {
                                db.updatePaymentDetails(defaultPaymentId.getCreditCardNumber(), defaultPaymentId.getCreditCardNumber(), defaultPaymentId.getCreditCardExpiry(), defaultPaymentId.getCreditCardCVC(), 0);
                                //Checks which payment was default before and removes it from default
                            } catch (Exception e) {
                                System.out.print("Unable to remove default payment:" + e);
                            }
                        }
                        db.updatePaymentDetails(oldNumber, CCN, CCE, CCCVC, isDefault);//Updates payment
                        session.setAttribute("SuccessUpdate", "Successfully updated payment details");
                        session.setAttribute("CCNMsg", CCN);
                        session.setAttribute("CCEMsg", CCE);
                        session.setAttribute("CCCVCMsg", CCCVC);
                        request.getRequestDispatcher("updatePayment.jsp").include(request, response);//Dispatches it back with success message
                    } catch (Exception e) {
                        System.out.print(e);
                        session.setAttribute("Success", "Unable to add payment details");
                        request.getRequestDispatcher("updatePayment.jsp").include(request, response);//Dispatches with error message
                    }
                }
            } else if (tempPayment.equals("yes")) {//Checks to see if it is an anonymous customer
                if (!validator.validateCreditCardNumber(CCN)) {
                    session.setAttribute("CCNErr", "Error:Credit card format incorrect");
                    request.getRequestDispatcher("addTempPayment.jsp").include(request, response);
                } else if (!validator.validateCreditCardExpiry(CCE)) {
                    session.setAttribute("CCEErr", "Error:Credit Card Expiry format incorrect");
                    request.getRequestDispatcher("addTempPayment.jsp").include(request, response);
                } else if (!validator.validateCreditCardCVC(CCCVC)) {
                    session.setAttribute("CCCVCErr", "Error:Credit Card CVC format incorrect");
                    request.getRequestDispatcher("addTempPayment.jsp").include(request, response);//Validates all the input
                } else {
                    Payment tempPaymentMethod = new Payment(CCN, CCE, CCCVC, 0, 0);//Creates new beans with the anonymous customer payment
                    session.setAttribute("tempPaymentMethod", tempPaymentMethod);//Saves the beans in the session for reuse
                    request.getRequestDispatcher("index.jsp").include(request, response);//Dispatches back home
                }
            } else {
                if (!validator.validateCreditCardNumber(CCN)) {
                    session.setAttribute("CCNErr", "Error:Credit card format incorrect");
                    request.getRequestDispatcher("addPayment.jsp").include(request, response);
                } else if (!validator.validateCreditCardExpiry(CCE)) {
                    session.setAttribute("CCEErr", "Error:Credit Card Expiry format incorrect");
                    request.getRequestDispatcher("addPayment.jsp").include(request, response);
                } else if (!validator.validateCreditCardCVC(CCCVC)) {
                    session.setAttribute("CCCVCErr", "Error:Credit Card CVC format incorrect");
                    request.getRequestDispatcher("addPayment.jsp").include(request, response);//validates input
                } else {
                    try {
                        if (isDefault == 1) {//checks to see if user wants this to be new default
                            Payment defaultPaymentId = db.getDefaultPayment(origin);
                            try {
                                db.updatePaymentDetails(defaultPaymentId.getCreditCardNumber(), defaultPaymentId.getCreditCardNumber(), defaultPaymentId.getCreditCardExpiry(), defaultPaymentId.getCreditCardCVC(), 0);
                                //Sets old default to 0
                            } catch (Exception e) {
                                System.out.print("Unable to remove default payment:" + e);
                            }
                        }
                        db.addPaymentDetails(CCN, CCE, CCCVC, isDefault, origin);
                        //adds payment method
                        session.setAttribute("Success", "Successfully added payment details");
                        request.getRequestDispatcher("addPayment.jsp").include(request, response);
                    } catch (Exception e) {
                        System.out.print(e);
                        session.setAttribute("Success", "Unable to add payment details");
                        request.getRequestDispatcher("addPayment.jsp").include(request, response);
                    }
                }
            }
        } catch (Exception ex) {
            System.out.print(ex);
        }
    }
}
