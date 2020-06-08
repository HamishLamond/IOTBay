<%-- 
    Document   : viewCatalogue
    Created on : Jun 3, 2020, 12:22:19 PM
    Author     : hoang
--%>

<%@page import="uts.isd.group30.model.Customer"%>
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
            <h1>IoTBay</h1>
        </div>
        <%
            String userType = (String) request.getSession().getAttribute("userType");
            Customer customer = (Customer) request.getSession().getAttribute("customer");
        %>
        <div class="top_right_link_div">
            <% if (userType == "staff" || userType=="customer"){ %>
                <a href="logout.jsp">Logout</a>
                <a href="myDetails.jsp">My Details</a>
                <a href="OrderListServlet?action=list">Order List</a>
                <a href="PaymentServlet?action=viewList">View Payment list</a>
            <% } else { %>
                <a href="loginRegister.jsp">Login/Register</a>
            <%}%>
            <a href="CurrentOrderServlet">View Order [${cart.size()}]</a>
            <a href="index.jsp">Home</a>
        </div>
        <h2>Device List
            <span> 
                <%if (userType == "staff"){ %> 
                <button type="button" onclick="location.href = 'StaffAddDeviceServlet'">Add Device</button>
                <%} %>
            </span>
        </h2>
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

                    <th><button type="button" onclick="location.href = 'CatalogueServlet?action=byname'">Sort by name</button>
                        <button type="button" onclick="location.href = 'CatalogueServlet?action=byprice'">Sort by price</button>
                    </th>
                </tr>
            </thead>
            <c:forEach items="${devices}" var="device">
                <tr>
                    <td>${device.name}</td>
                    <td>${device.description}</td>
                    <td>${device.cost}</td>
                    <td>${device.stock}</td>
                    <%if (userType == "staff"){ %>
                    <td><a href="EditDeviceServlet?name=${device.name}">Edit</a></td>
                    <%} else { %>
                    <td><a href="AddDeviceServlet?name=${device.name}">Add to Cart</a></td>
                    <%} %>
                </tr>
            </c:forEach>                       
        </table>
    </body>
</html>
