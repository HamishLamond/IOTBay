<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@page import="uts.isd.group30.model.Customer"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="css/IoTBayCSS.css">
        <title>Welcome Page</title>
    </head>
    <body>
        <div class="header">
            <h1>IoTBay Home Page</h1>
        </div>
        <div class="top_right_link_div">
            <a href="myDetails.jsp">My Details</a>
            <a href="PaymentServlet?action=viewList">View Payment list</a>
            <a href="CatalogueServlet?action=list">Catalogue</a>
            <a href="viewOrderList.jsp">Order List</a>
            <a href="logout.jsp">Logout</a>
        </div>
        <h2>You have successfully logged into IoTBay!</h2>
    </body>
</html>
