<%-- 
    Document   : logout
    Created on : 01/05/2020, 8:40:06 PM
    Author     : USER
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="css/IoTBayCSS.css">
        <title>Logout</title>
    </head>
    <body>
        <h1>IoTBay</h1>
        <hr>
        <p>You have successfully logged out of your account.</p>
        <a class ="middle_link_button" href="index.jsp">IoTBay Landing Page</a>
        <% session.invalidate(); %>
    </body>
</html>
