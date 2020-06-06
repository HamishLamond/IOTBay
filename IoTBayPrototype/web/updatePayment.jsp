<%-- 
    Document   : register
    Created on : 01/05/2020, 8:39:18 PM
    Author     : USER
--%>

<%@page import="uts.isd.group30.model.Payment"%>
<%@page import="uts.isd.group30.model.Customer" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="css/IoTBayCSS.css">
        <title>Add Payment Details</title>
    </head>
    <body>
        <h1>IoTBay</h1>
        <hr>
        <%
            Customer customer = (Customer)session.getAttribute("customer");
            String CCNMsg = (String) session.getAttribute("CCNErr");
            String CCEMsg = (String) session.getAttribute("CCEErr");
            String CCCVCMsg = (String) session.getAttribute("CCCVCErr");
            String SuccessUpdate = (String) session.getAttribute("SuccessUpdate");
            String isUpdate = (String) session.getAttribute("isUpdate");
            Payment oldPayment = (Payment) session.getAttribute("oldPayment");
            %>
            <h2>Update Payment</h2>
        
        <form action="AddPaymentServlet" method="post">
            <%
                if(SuccessUpdate!=null){
            %>
            <h2><%=SuccessUpdate%></h2>
            <%
                }
            %>
            <table>
                <tr>
                    <td><label for="CCN">Credit Card Number</label></td>
                    <td><input class="form_input_box" type="text" id ="frame" name="CCN" placeholder="<%=(CCNMsg != null ? CCNMsg : oldPayment.getCreditCardNumber()) %>" required></td>
                </tr>
                <tr>
                    <td><label for="CCE">Credit Card Expiry</label></td>
                    <td><input class="form_input_box" type="text" id ="frame" name="CCE" placeholder="<%=(CCEMsg != null ? CCEMsg : oldPayment.getCreditCardExpiry()) %>" required></td>
                </tr>
                <tr>
                    <td><label for="CCCVC">Credit Card CVC</label></td>
                    <td><input class="form_input_box" type="text" id ="frame" name="CCCVC" placeholder="<%=(CCCVCMsg != null ? CCCVCMsg : oldPayment.getCreditCardCVC()) %>" required></td>
                </tr>
                <tr>
                    <td></td>
                    <td><center>
                    <input type="hidden" name="isUpdate" value="true"/>
                    <input type="hidden" name="oldNumber" value="<%=oldPayment.getCreditCardNumber()%>"/>
                    <input type="hidden" name="origin"  value="1">
                    <input class="button" type="submit" value="Update" required>
                </center>
                    </td>
                </tr>
            </table>
        </form>
        <a href="PaymentServlet?action=viewList&origin=1">View Payment Details</a>
    </body>
</html>
