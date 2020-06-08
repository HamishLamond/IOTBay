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
        <title>Update Payment Details</title>
    </head>
    <body>
        <div class="header">
            <h1>IoTBay</h1>
        </div>
        <div class="top_right_link_div">
            <a href="logout.jsp">Logout</a>
            <a href="myDetails.jsp">My Details</a>
            <a href="OrderListServlet?action=list">Order List</a>
            <a href="PaymentServlet?action=viewList">View Payment list</a>
            <a href="CatalogueServlet?action=list">Catalogue</a>
            <a href="CurrentOrderServlet">View Order [${cart.size()}]</a>
            <a href="index.jsp">Home</a>
        </div>
        <hr>
        <%
            Customer customer = (Customer)session.getAttribute("customer");
            String CCNMsg = (String) session.getAttribute("CCNErr");
            String CCEMsg = (String) session.getAttribute("CCEErr");
            String CCCVCMsg = (String) session.getAttribute("CCCVCErr");
            String SuccessUpdate = (String) session.getAttribute("SuccessUpdate");
            String isUpdate = (String) session.getAttribute("isUpdate");
            //Integer chckMsg = (Integer) session.getAttribute("chckEnable");
            //System.out.print("chckMsg: " + chckMsg);
            Payment oldPayment = (Payment) session.getAttribute("oldPayment");
            %>
            <h4>Update Payment</h4>
        
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
                    <td><label for="isDefault">Make default</label></td>
                    <td><input type="checkbox" name="Chkbox"></td>
                </tr>
                <tr>
                    <td></td>
                    <td><center>
                    <input type="hidden" name="isUpdate" value="true">
                    <input type="hidden" name="oldNumber" value="<%=oldPayment.getCreditCardNumber()%>">
                    <input type="hidden" name="origin"  value="<%=customer.getId()%>">
                    <input type="hidden" name="tempPayment" value="yes">
                    <input class="button" type="submit" value="Update" required>
                </center>
                    </td>
                </tr>
            </table>
        </form>
    </body>
</html>
