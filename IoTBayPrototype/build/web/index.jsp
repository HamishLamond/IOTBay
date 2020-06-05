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
        <div class="header">
            <h1>IoTBay</h1>
        </div>
        <div class="middle_link_div">
            <a class="middle_link_button" href="login.jsp">Login</a>
            <a class="middle_link_button" href="register.jsp">Register</a>
        </div>
        <jsp:include page="/ConnServlet" flush="true" />
    </body>
</html>
