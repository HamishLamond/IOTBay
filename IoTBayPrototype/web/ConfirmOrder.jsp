<%-- 
    Document   : ConfirmOrder
    Created on : 08/06/2020, 4:28:30 PM
    Author     : Hamish Lamond
--%>

<%@page import="uts.isd.group30.model.Payment"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="css/IoTBayCSS.css">
        <title>JSP Page</title>
    </head>
    <body class="body_no_image">
        <div class="header">
            <% Payment paymentMethod = (Payment) session.getAttribute("paymentMethod");%>
            <h1>IoTBay</h1>
        </div>
        <div class="top_right_link_div">
            <c:if test="${customer != null}">
            <a href="logout.jsp">Logout</a>
            <a href="myDetails.jsp">My Details</a>
            <a href="OrderListServlet?action=list">Order List</a>
            <a href="PaymentServlet?action=viewList">View Payment list</a>
            </c:if>
            <c:if test="${customer == null}">
            <a href="loginRegister.jsp">Login/Register</a>
            </c:if>
            <a href="CatalogueServlet?action=list">Catalogue</a>
            <a href="CurrentOrderServlet">View Order [${cart.size()}]</a>
            <a href="index.jsp">Home</a>
        </div>
        <c:if test="${customer != null}">
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
                
            </c:if>
            <h2>Payment method</h2>
            <table class="order_list_table">
                <tr>
                    <th>Credit Card Number</th>
                    <th>Credit Card Expiry</th>
                    <th>Credit Card CVC</th>
                </tr>
                <% if (paymentMethod != null){ %>
                <tr>
                    <th>${paymentMethod.getCreditCardNumber()}</th>
                    <th>${paymentMethod.getCreditCardExpiry()}</th>
                    <th>${paymentMethod.getCreditCardCVC()}</th>
                </tr>
            </table>
            <div class="middle_link_div">
                <a class="middle_link_button" href="CurrentOrderServlet">Back</a>
                <a class="middle_link_button" href="PaymentServlet?action=viewList">Set Payment methods</a>
                <a class="middle_link_button" href="CreateTransactionServlet">Confirm</a>
            </div>
                <%
                    }
                else
                {
                %>
                <tr>
                    <th colspan="3">Please set default payment before continuing</th>
                </tr>
            </table>
            <div class="middle_link_div">
                <a class="middle_link_button" href="CurrentOrderServlet">Back</a>
                <a class="middle_link_button" href="PaymentServlet?action=viewList">Change default payment methods</a>
            </div>
                <%
                    }
                    %>
        </c:if>
        <c:if test="${customer == null}">
            <h2>Current order</h2>
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
                        <td>Total Cost</td>
                    </tr>
                </table>
                <h2>Payment method</h2>
            <table class="order_list_table">
                <tr>
                    <th>Credit Card Number</th>
                    <th>Credit Card Expiry</th>
                    <th>Credit Card CVC</th>
                </tr>
                <% if (paymentMethod != null){ %>
                <tr>
                    <th>${paymentMethod.getCreditCardNumber()}</th>
                    <th>${paymentMethod.getCreditCardExpiry()}</th>
                    <th>${paymentMethod.getCreditCardCVC()}</th>
                </tr>
            </table>
            <div class="middle_link_div">
                <a class="middle_link_button" href="CurrentOrderServlet">Back</a>
                <a class="middle_link_button" href="addTempPayment.jsp">Reset Payment methods</a>
                <a class="middle_link_button" href="CreateTransactionServlet">Confirm</a>
            </div>
                <%
                    }
                else
                {
                %>
                <tr>
                    <th colspan="3">Please set default payment before continuing</th>
                </tr>
            </table>
            <div class="middle_link_div">
                <a class="middle_link_button" href="CurrentOrderServlet">Back</a>
                <a class="middle_link_button" href="addTempPayment.jsp">Set payment method</a>
            </div>
                <%
                    }
                    %>
            </c:if>
        </c:if>
    </body>
</html>
