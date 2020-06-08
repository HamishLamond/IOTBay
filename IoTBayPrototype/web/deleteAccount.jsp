<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="css/IoTBayCSS.css">
        <title>JSP Page</title>
    </head>
    <body>
        <div class="header">
            <h1>IoTBay</h1>
        </div>
        <h2>Delete your Account</h2>
        <div>
            <form action="DeleteAccountServlet" method="post">
                <div>This process cannot be reversed! Are you sure?</div>
                <input class="button" type ="submit" value="Yes" required>
            </form>
        </div>
        <div><a href="myDetails.jsp">Take me back!</a></div>

    </body>
</html>
