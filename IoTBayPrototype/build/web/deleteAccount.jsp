<%-- 
    Document   : deleteAccount
    Created on : 08/06/2020, 5:31:50 PM
    Author     : Zunther
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Delete your Account</h1>
        <form action="DeleteAccountServlet" method="post">
            <div>This process cannot be reversed! Are you sure?</div>
            <input class="button" type ="submit" value="Yes" required>
        </form>
        <a href="myDetails.jsp">Take me back!</a>
    </body>
</html>
