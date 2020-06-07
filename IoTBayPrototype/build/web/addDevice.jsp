<%-- 
    Document   : AddDevice
    Created on : Jun 6, 2020, 5:28:06 AM
    Author     : hoang
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="uts.isd.group30.model.Device"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="css/IoTBayCSS.css">
        <title>JSP Page</title>
    </head>
    <body>
        <%
            Device device = (Device) request.getSession().getAttribute("device");
            String stockErr = (String) session.getAttribute("stockErr");
        %>
        <table class="order_list_table">
            <thead>
                <tr>
                    <th>Device Name</th>
                    <th>Description</th>
                    <th>Price</th>
                    <th>In Stock</th>
                </tr>
            </thead>

                <tr>
                    <td>${device.name}</td>
                    <td>${device.description}</td>
                    <td>${device.cost}</td>
                    <td>${device.stock}</td>
                </tr>

        </table>
        <form method="post" action="AddDeviceServlet">
            <table>
                <tr>
                    <td>Quantity: </td>
                    <td><input type="number" name="value"></td>
                    <td><input class="button" type="submit" value="Add"></td>
                    <td><%=(stockErr!=null ? stockErr : "")%></td>
                </tr>
                
            </table>
            <P></p>
        </form>
    </body>
</html>
