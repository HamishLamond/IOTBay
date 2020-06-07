<%-- 
    Document   : viewPreviousTransaction
    Created on : 06/06/2020, 4:42:59 PM
    Author     : Hamish Lamond
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="uts.isd.group30.model.Customer"%>
<%@page import="java.util.HashMap"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="css/IoTBayCSS.css">
        <title>Previous Transaction</title>
    </head>
    <body class="body_no_image">
        <%
            Customer customer = (Customer) session.getAttribute("customer");
            HashMap<String, Integer> cart = (HashMap<String, Integer>) session.getAttribute("cart");
        %>
        <div class="header">
            <h1>IoTBay</h1>
        </div>
        <div class="top_right_link_div">
            <a href="logout.jsp">Logout</a>
            <a href="CatalogueServlet?action=list">Catalogue</a>
            <a href="OrderListServlet?action=list">Order List</a>
            <% if (cart != null) { %>
            <a href="currentOrder.jsp">View Order [${cart.size()}]</a>
            <% }%>
        </div>
        <h2>Transaction ${request.getParameter(id)}</h2>
        <table class="previous_order_table">
            <thead>
                <tr>
                    <th>Device Name</th>
                    <th>No.</th>
                    <th>Cost</th>
                </tr>
            </thead>
            <c:forEach items="${devices}" var="device">
                <tr>
                    <td>${device.key}</td>
                    <td>x${device.value.quantity}</td>
                    <td>${device.value.cost}</td>
                </tr>
            </c:forEach>
                <tr>
                    <td class="empty_cell_bottom"></td>
                    <td class="empty_cell_bottom"></td>
                    <td>${value}</td>
                </tr>
        </table>
    </body>
</html>
