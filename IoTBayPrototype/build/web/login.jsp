<%-- 
    Document   : login
    Created on : 01/05/2020, 8:39:08 PM
    Author     : USER
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="css/IoTBayCSS.css">
        <title>Login</title>
    </head>
    <body>
        <h1>IoTBay</h1>
        <hr>
        <h2>Login</h2>
        <form action="welcome.jsp" method="post">
            <table>
                <tr>
                    <td><label for="email">Email</label></td>
                    <td><input class="form_input_box" type="email" id ="frame" name="email" placeholder="JohnSmith@gmail.com" required></td>
                </tr>
                <tr>
                    <td><label for="password">Password</label></td>
                    <td><input class="form_input_box" type="password" id ="frame" name="password" placeholder="Password123_" required></td>
                </tr>
                <tr>
                    <td></td>
                    <td><center>
                    <input type="hidden" name="origin"  value="login">
                    <input class="button" type ="submit" value="Login" required>
                </center>
                    </td>
                </tr>
            </table>
        </form>
    </body>
</html>
