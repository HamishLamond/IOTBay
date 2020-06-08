<%-- 
    Document   : editDevice
    Created on : Jun 9, 2020, 6:03:35 AM
    Author     : hoang
--%>


<%@page import="uts.isd.group30.model.Customer"%>
<%@page import="java.util.ArrayList"%>
<%@page import="uts.isd.group30.model.Device"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="css/IoTBayCSS.css">
        <title>Edit Device</title>
    </head>
    <body class="body_no_image">
        <div class="header">
            <h1>IoTBay</h1>
        </div>
        <%
            Device device = (Device) request.getSession().getAttribute("device");
            String deleteErr = (String) session.getAttribute("deleteErr");
            String format2Err = (String) session.getAttribute("format2Err");

            Customer customer = (Customer) request.getSession().getAttribute("customer");
        %>
        <div class="top_right_link_div">
            <% if (customer != null) { %>

                <a href="logout.jsp">Logout</a>
                <a href="myDetails.jsp">My Details</a>
                <a href="OrderListServlet?action=list">Order List</a>
                <a href="PaymentServlet?action=viewList">View Payment list</a>

            <%} %>
           
            <a href="CatalogueServlet?action=list">Catalogue</a>
            <a href="CurrentOrderServlet">View Order [${cart.size()}]</a>
            <a href="index.jsp">Home</a>
        </div>

        <h2>Edit Device</h2><P><i><%=(deleteErr != null) ? deleteErr : ""%></i></P>
            <P><i><%=(format2Err != null) ? format2Err : ""%></i></P>
        <form method="post" action="EditDeviceServlet">
            <table>
                <tr><td>Device Name</td><td><input name="name" value="${device.name}"</td>
                <tr><td>Description</td><td><input name="desc" value="${device.description}"</td></tr>
                <tr><td>Price</td><td><input type="number" name="cost" value="${device.cost}"</td></tr>
                <tr><td>Stock</td><td><input type="number" name="stock" value="${device.stock}"</td></tr>
                <tr><td>Stock Warning at</td><td><input type="number" name="threshold" value="${device.stockWarnThreshold}"</td></tr>
                <tr><td></td>
                    <td><input class="button" type="submit" value="Edit"><button type="button" onclick="location.href = 'DeleteDeviceServlet'">Delete Device</button></td>
                </tr>
            </table>
        </form>
    </body>
</html>

