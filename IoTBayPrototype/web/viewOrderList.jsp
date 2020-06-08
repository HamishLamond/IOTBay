<%-- 
    Document   : viewOrderList
    Created on : 03/06/2020, 10:47:14 AM
    Author     : Hamish Lamond
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
            <a href="myDetails.jsp">My Details</a>
            <a href="PaymentServlet?action=viewList">View Payment list</a>
            <a href="CatalogueServlet?action=list">Catalogue</a>
            <a href="CurrentOrderServlet">View Order [${cart.size()}]</a>
            <a href="index.jsp">Home</a>
        </div>
        <% if (customer.getName() != null) { %>
        <h2>${customer.name}'s Order List</h2>
        <div>
            <p>Order by: </p>
            <button type="button" onclick="location.href = 'OrderListServlet'">ID</button>
            <button type="button" onclick="location.href = 'OrderListServlet?filter=date'">Modified Date</button>
        </div>
        <table class="order_list_table">
            <thead>
                <tr>
                    <th>Transaction Number</th>
                    <th>Date Last Modified</th>
                    <th>Status</th>
                </tr>
            </thead>
            <c:forEach items="${transactions}" var="transaction">
                <tr>
                    <td><a href="PreviousTransactionServlet?id=${transaction.id}">${transaction.id}</a></td>
                    <td>${transaction.lastUpdated}</td>
                    <c:if test="${transaction.status == 0}">
                        <td>Active</td>
                    </c:if>
                    <c:if test="${transaction.status == 1}">
                        <td>Completed</td>
                    </c:if>
                    <c:if test="${transaction.status == 2}">
                        <td>Canceled</td>
                    </c:if>
                </tr>
            </c:forEach>
        </table>
        <% } else { %>
        <h2>Order List</h2>
        <p>Please log in to load your transaction list</p>
        <% }%>
    </body>
</html>
