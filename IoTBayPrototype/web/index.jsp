<%@page import="java.util.HashMap"%>
<%@page import="uts.isd.group30.model.Customer"%>
<!DOCTYPE html>
<!--
To change this license header, choose License Headers in Project Properties.
To change this template file, choose Tools | Templates
and open the template in the editor.
-->
<html>
    <head>
        <title>Welcome to IoTBay</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="css/IoTBayCSS.css">
    </head>
    <body>
        <jsp:include page="/ConnServlet" flush="true" />
        <%
            Customer customer = (Customer) session.getAttribute("customer");
            HashMap<String, Integer> cart = (HashMap<String, Integer>) session.getAttribute("cart");
        %>
        <div class="header">
            <h1>IoTBay</h1>
        </div>
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
        </div>
        <% if (customer != null) { %>
        <h2>Welcome to IoTBay, ${customer.name}!</h2>
        <p>Currently logged in as ${customer.email}.</p>
        <% } else { %>
        <h2>Welcome to IoTBay!</h2>
        <% }%>
    </body>
</html>
