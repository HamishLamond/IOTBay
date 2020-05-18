<%-- 
    Document   : main
    Created on : 01/05/2020, 8:39:39 PM
    Author     : USER
--%>

<%@page import="uts.isd.group30.model.Customer" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="css/IoTBayCSS.css">
        <title>JSP Page</title>
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
        <% if (customer.getName() != null){ %>
        <h2>Welcome to IoTBay, ${customer.name}!</h2>
        <p>Currently logged in as ${customer.email}.</p>
        <p>Our system will be ready for use 5/6/20!</p>
        <p>Stay tuned!</p>
        <% } else { %>
        <h2>Welcome back to IoTBay!</h2>
        <p>Currently logged in as ${customer.email}.</p>
        <p>Our system will be ready for use 5/6/20!</p>
        <p>Stay tuned!</p>
        <% } %>
    </body>
</html>
