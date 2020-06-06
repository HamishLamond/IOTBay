<%-- 
    Document   : register
    Created on : 01/05/2020, 8:39:18 PM
    Author     : USER
--%>

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
            String CCNErr = (String) session.getAttribute("CCNErr");
            String CCEErr = (String) session.getAttribute("CCEErr");
            String CCCVCErr = (String) session.getAttribute("CCCVCErr");
            String Success = (String) session.getAttribute("Success");
            if (CCNErr == null){
                
            }
        %>
        <h2>Add Payment</h2>
        <form action="AddPaymentServlet" method="post">
            <%
                if(Success!=null){
            %>
            <h2><%=Success%></h2>
            <%
                }
            %>
            <table>
                <tr>
                    <td><label for="CCN">Credit Card Number</label></td>
                    <td><input class="form_input_box" type="text" id ="frame" name="CCN" placeholder="<%=(CCNErr != null ? CCNErr : "0421326156548569") %>" required></td>
                </tr>
                <tr>
                    <td><label for="CCE">Credit Card Expiry</label></td>
                    <td><input class="form_input_box" type="text" id ="frame" name="CCE" placeholder="<%=(CCEErr != null ? CCEErr : "12/21") %>" required></td>
                </tr>
                <tr>
                    <td><label for="CCCVC">Credit Card CVC</label></td>
                    <td><input class="form_input_box" type="text" id ="frame" name="CCCVC" placeholder="<%=(CCCVCErr != null ? CCCVCErr : "111") %>" required></td>
                </tr>
                <tr>
                    <td></td>
                    <td><center>
                    <input type="hidden" name="origin"  value="1">
                    <input class="button" type="submit" value="Add" required>
                </center>
                    </td>
                </tr>
            </table>
        </form>
        <a href="PaymentServlet?action=viewList&origin=1">View Payment Details</a>
    </body>
</html>
