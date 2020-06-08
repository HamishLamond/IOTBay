<%-- 
    Document   : staffAddDevice
    Created on : Jun 9, 2020, 6:10:42 AM
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
        <title>Add Device</title>
    </head>
    <body class="body_no_image">
        <div class="header">
            <h1>IoTBay</h1>
        </div>
        <%
            Device device = (Device) request.getSession().getAttribute("device");
            String formatErr = (String) session.getAttribute("formatErr");
            String existErr = (String) session.getAttribute("existErr");

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

        <h2>Add Device to Catalogue</h2>
        <P><i><%=(formatErr != null)? formatErr : ""%></i></p>
        <P><i><%=(existErr != null)? existErr : ""%></i></p>
        <form method="post" action="StaffAddDeviceServlet">
            <table>
                <tr><td>Device Name</td><td><input name="name" </td>
                <tr><td>Description</td><td><input name="desc" </td></tr>
                <tr><td>Price</td><td><input type="number" name="cost" </td></tr>
                <tr><td>Stock</td><td><input type="number" name="stock" </td></tr>
                <tr><td>Stock Warning at</td><td><input type="number" name="threshold" </td></tr>
                <tr><td></td>
                    <td><input class="button" type="submit" value="Add"</td>
                </tr>
            </table>
        </form>
    </body>
</html>
