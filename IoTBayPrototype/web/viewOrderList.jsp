<%-- 
    Document   : viewOrderList
    Created on : 03/06/2020, 10:47:14 AM
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
        <title>Customer Order List</title>
    </head>
    <body>
        <%
            Customer customer = (Customer)session.getAttribute("customer");
            HashMap<String, Integer> cart = (HashMap<String, Integer>)session.getAttribute("cart");
         %>
        <h1>IoTBay</h1>
        <hr>
        <div class="top_right_link_div">
            <a href="logout.jsp">Logout</a>
            <% if (cart != null){ %>
            <a href="currentOrder.jsp">View Order [${cart.size()}]</a>
            <% } %>
        </div>
        <% if (customer.getName() != null){ %>
            <h2>${customer.name}'s Order List</h2>
            <table class="order_list_table">
                <tr>
                    <th>Transaction Number</th>
                    <th>Date Last Modified</th>
                    <th>Status</th>
                </tr>
            </table>
        <% } else { %>
            <h2>Order List</h2>
            <p>Please log in to load your transaction list</p>
        <% } %>
    </body>
</html>
