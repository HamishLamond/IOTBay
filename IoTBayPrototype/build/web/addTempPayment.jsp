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
        <div class="header">
            <h1>IoTBay</h1>
        </div>
        <%
            Customer customer = (Customer) session.getAttribute("customer");
            //gets customer bean from the session
            String CCNErr = (String) session.getAttribute("CCNErr");
            String CCEErr = (String) session.getAttribute("CCEErr");
            String CCCVCErr = (String) session.getAttribute("CCCVCErr");
            String Success = (String) session.getAttribute("Success");
            String isUpdate = (String) session.getAttribute("isUpdate");
            //Gets information from the servlet about any input errors
            //This JSP is for the creation of payment beans for anonymous customers
        %>
        <div class="top_right_link_div">
            <% if (customer != null) { %>
            <a href="logout.jsp">Logout</a>
            <a href="myDetails.jsp">My Details</a>
            <a href="OrderListServlet?action=list">Order List</a>
            <a href="PaymentServlet?action=viewList">View Payment list</a>
            <% } else { %>
            <a href="loginRegister.jsp">Login/Register</a>
            <% } %>
            <a href="CatalogueServlet?action=list">Catalogue</a>
            <a href="CurrentOrderServlet">View Order [${cart.size()}]</a>
            <a href="index.jsp">Home</a>
        </div>
        <h2>Add Payment</h2>
        <form action="AddPaymentServlet" method="post">
            <%
                if (Success != null) {
            %>
            <h4><%=Success%></h4>
            <%
                }
            %>
            <table>
                <tr>
                    <td><label for="CCN">Credit Card Number</label></td>
                    <td><input class="form_input_box" type="text" id ="frame" name="CCN" placeholder="<%=(CCNErr != null ? CCNErr : "0421326156548569")%>" required></td>
                </tr>
                <tr>
                    <td><label for="CCE">Credit Card Expiry</label></td>
                    <td><input class="form_input_box" type="text" id ="frame" name="CCE" placeholder="<%=(CCEErr != null ? CCEErr : "12/21")%>" required></td>
                </tr>
                <tr>
                    <td><label for="CCCVC">Credit Card CVC</label></td>
                    <td><input class="form_input_box" type="text" id ="frame" name="CCCVC" placeholder="<%=(CCCVCErr != null ? CCCVCErr : "111")%>" required></td>
                </tr>
                <tr>
                    <td><label for="isDefault">Make default</label></td>
                    <td><input type="checkbox" name="Chkbox"></td>
                </tr>
                <tr>
                    <td></td>
                    <td>
                <center>
                    <input type="hidden" name="isUpdate" value="false">
                    <input type="hidden" name="oldPayment" value="null">
                    <input type="hidden" name="tempPayment" value="yes">
                    <input type="hidden" name="origin" value="0">
                    <input class="button" type="submit" value="Add" required>
                </center>
                </td>
                </tr>
            </table>
        </form>
    </body>
</html>
