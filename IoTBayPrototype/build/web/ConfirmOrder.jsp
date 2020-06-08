<%-- 
    Document   : ConfirmOrder
    Created on : 08/06/2020, 4:28:30 PM
    Author     : Hamish Lamond
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="css/IoTBayCSS.css">
        <title>JSP Page</title>
    </head>
    <body><div class="header">
            <h1>IoTBay</h1>
        </div>
        <div class="top_right_link_div">
            <a href="logout.jsp">Logout</a>
            <a href="CatalogueServlet?action=list">Catalogue</a>
            <a href="OrderListServlet?action=list">Order List</a>
            <a href="PaymentServlet?action=viewList&origin=2">View Payment list</a>
            <a href="main.jsp">Home</a>
        </div>
        <c:if test="${customer.getName() != null}">
            <h2>${customer.name}'s current order.</h2>
            <c:if test="${cart.size() > 0}">
                <table class="previous_order_table">
                    <tr>
                        <th>Device</th>
                        <th>Cost</th>
                        <th>Quantity</th>
                        <th>Total</th>
                    </tr>
                    
                    <c:forEach var="iterator" begin="0" end="${deviceArray.size() - 1}">
                        <tr>
                            <td>${deviceArray[iterator].getName()}</td>
                            <td>${deviceArray[iterator].getCost()}</td>
                            <td>${deviceNumbers[iterator]}</td>
                            <td>${deviceArray[iterator].getCost() * deviceNumbers[iterator]}</td>
                        </tr>
                    </c:forEach>
                    <tr>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td>${totalCost}</td>
                    </tr>
                </table>
                <div class="middle_link_div">
                    <a class="middle_link_button" href="CurrentOrderServlet">Back</a>
                    <a class="middle_link_button" href="CreateTransactionServlet">Confirm</a>
                </div>
            </c:if>
        </c:if>
        <c:if test="${customer.getName() == null}">
            <h2>Current order</h2>
            <c:if test="${cart.size() > 0}">
                <table class="order_table">
                    <tr>
                        <th>Device</th>
                        <th>Cost</th>
                        <th>Quantity</th>
                        <th>Total</th>
                        <th></th>
                    </tr>
                    <c:forEach var="iterator" begin="0" end="${deviceArray.size() - 1}">
                        <tr>
                            <td>${deviceArray[iterator].getName()}</td>
                            <td>${deviceArray[iterator].getCost()}</td>
                            <td>${deviceNumbers[iterator]}</td>
                            <td>${deviceArray[iterator].getCost() * deviceNumbers[iterator]}</td>
                        </tr>
                    </c:forEach>
                    <tr>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td>Total Cost</td>
                    </tr>
                </table>
                <div class="middle_link_div">
                    <a class="middle_link_button" href="CurrentOrderServlet">Back</a>
                    <a class="middle_link_button" href="CreateTransactionServlet">Confirm</a>
                </div>
            </c:if>
        </c:if>
    </body>
</html>
