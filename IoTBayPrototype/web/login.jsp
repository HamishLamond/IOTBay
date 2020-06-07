<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="css/IoTBayCSS.css">
        <title>Login</title>
    </head>
    <body>
        <div class="header">
            <h1>IoTBay</h1>
        </div>
        <%
            String loginError = (String)session.getAttribute("InvalidLogin");
        %>
        <h2>Login</h2>
        <form action="LoginServlet" method="post">
            <% 
                if (loginError != null) 
                { 
                    %>
                    <td><span style="color:red"><%=loginError%></span></td>

                    <% } 
                %>
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
                    <td><input type="radio" name="user" value="customer" checked="true"/>Customer</td>
                    <td><input type="radio" name="user" value="staff"/>Staff</td>
                    
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
