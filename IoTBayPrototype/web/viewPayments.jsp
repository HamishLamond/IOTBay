<%-- 
    Document   : viewOrder
    Created on : 25/05/2020, 1:07:20 PM
    Author     : Hamish Lamond
--%>

<%@page import="java.util.HashMap"%>
<%@page import="uts.isd.group30.model.Customer"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="css/IoTBayCSS.css">
        <title>All Payments</title>
    </head>
    <body>
        <%
            Customer customer = (Customer)session.getAttribute("customer");
            
         %>
        <h1>IoTBay</h1>
        <hr>
        <div class="top_right_link_div">
            <a href="logout.jsp">Logout</a>
        </div>
        <h2>Payment History</h2>
            <table class="payment_history">
                <tr>
                    <th>Payment Id</th>
                    <th>Is default</th>
                    <th>Credit Card Number</th>
                    <th>Credit Card Expiry</th>
                    <th>Credit Card CVC</th>
                </tr>
                <%
                    if (cart.size() > 0){
                        for (int i = 0; i < cart.size(); i++){
                    %>
                <tr>
                    <td>TLI Device ID</td>
                    <td>TRI Device Cost</td>
                    <td>TLI Quantity</td>
                    <td>TLI total cost</td>
                </tr>
                <%
                        }
                    }
                    %>
                <tr>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td>Total Cost</td>
                </tr>
            </table>
    </body>
</html>
