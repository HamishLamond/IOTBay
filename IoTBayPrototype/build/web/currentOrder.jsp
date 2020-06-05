<%-- 
    Document   : currentOrder
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
        <title>Current Order</title>
    </head>
    <body>
        <%
            Customer customer = (Customer)session.getAttribute("customer");
            HashMap<String, Integer> cart = (HashMap<String, Integer>)session.getAttribute("cart");
         %>
         <div class="header">
            <h1>IoTBay</h1>
         </div>
        <div class="top_right_link_div">
            <a href="logout.jsp">Logout</a>
            <a href="CatalogueServlet?action=list">Catalogue</a>
            <a href="viewOrderList.jsp">Order List</a>
        </div>
        <% if (customer.getName() != null){ %>
            <h2>${customer.name}'s current order.</h2>
            <%
                    if (cart.size() > 0){ %>
            <table class="order_table">
                <tr>
                    <th>Device</th>
                    <th>Cost</th>
                    <th>Quantity</th>
                    <th>Total</th>
                    <th></th>
                </tr>
                <%
                        for (int i = 0; i < cart.size(); i++){ 
                    %>
                <tr>
                    <td>TLI Device ID</td>
                    <td>TRI Device Cost</td>
                    <td>TLI Quantity</td>
                    <td>TLI total cost</td>
                    <td>X</td>
                </tr>
                <%
                        } %>
                <tr>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td>Total Cost</td>
                </tr>
            </table>
            <a class="middle_link_button">Cancel</a>
            <a class="middle_link_button" href="main.jsp">Checkout</a>
            <%
                    } else {
                    %>
                    <p>No items found in cart.</p>
                    <% } %>
        <% } else { %>
            <h2>Current order</h2>
            <%
                    if (cart.size() > 0){ %>
            <table class="order_table">
                <tr>
                    <th>Device</th>
                    <th>Cost</th>
                    <th>Quantity</th>
                    <th>Total</th>
                    <th></th>
                </tr>
                <%
                        for (int i = 0; i < cart.size(); i++){
                    %>
                <tr>
                    <td>TLI Device ID</td>
                    <td>TRI Device Cost</td>
                    <td>TLI Quantity</td>
                    <td>TLI total cost</td>
                    <td>X</td>
                </tr>
                <%
                        } %>
                <tr>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td>Total Cost</td>
                </tr>
            </table>
            <a class="middle_link_button">Cancel</a>
            <a class="middle_link_button" href="main.jsp">Checkout</a>
            <%
                    } else {
                    %>
                    <p>No items found in cart.</p>
                    <% } %>
        <% } %>
        <a class="middle_link_button" href="main.jsp">Main Page</a>
    </body>
</html>