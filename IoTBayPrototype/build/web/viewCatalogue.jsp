<%-- 
    Document   : viewCatalogue
    Created on : Jun 3, 2020, 12:22:19 PM
    Author     : hoang
--%>

<%@page import="java.util.ArrayList"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="uts.isd.group30.model.Device"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="css/IoTBayCSS.css">
        <title>Catalogue</title>
    </head>
    <body class="body_no_image">
        <div class="header">
            <h1>Device List</h1>
        </div>
        <div class="top_right_link_div">
            <a href="logout.jsp">Logout</a>
            <a href="OrderListServlet?action=list">Order List</a>
            <a href="currentOrder.jsp">View Order [${cart.size()}]</a>
        </div>
        <form method="post" action="CatalogueServlet">
            <table>
                <tr><td><input type="text" name="name"></td>
                    <td><input class="button" type="submit" value="Search"></td>
                </tr>
            </table>
        </form>
        <table class="order_list_table">
            <thead>
                <tr>
                    <th>Device Name</th>
                    <th>Description</th>
                    <th>Price</th>
                    <th>In Stock</th>
                    <th></th>
                    <th><button type="button" onclick="location.href='CatalogueServlet?action=byname'">Sort by name</button>
                        <button type="button" onclick="location.href='CatalogueServlet?action=byprice'">Sort by price</button>
                    </th>
                </tr>
            </thead>
            <c:forEach items="${devices}" var="device">
                <tr>
                    <td>${device.name}</td>
                    <td>${device.description}</td>
                    <td>${device.cost}</td>
                    <td>${device.stock}</td>
                    <td><a href="main.jsp">Edit</a></td>
                    <td><a href="AddDeviceServlet?name=${device.name}">Add</a></td>
                </tr>
            </c:forEach>                       
        </table>
    </body>
</html>
