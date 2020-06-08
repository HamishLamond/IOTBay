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
        <link rel="stylesheet" href="css/IoTBayCSS.css">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Delete your Account</h1>
        <div>
            <form action="DeleteAccountServlet" method="post">
                <div>This process cannot be reversed! Are you sure?</div>
                <input class="button" type ="submit" value="Yes" required>
            </form>
        </div>
        <div><a href="myDetails.jsp">Take me back!</a></div>
        
    </body>
</html>
