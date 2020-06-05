<%-- 
    Document   : viewOrder
    Created on : 25/05/2020, 1:07:20 PM
    Author     : Hamish Lamond
--%>

<%@page import="java.util.HashMap"%>
<%@page import="uts.isd.group30.model.Customer"%>
<%@page import="uts.isd.group30.model.Payment"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="css/IoTBayCSS.css">
        <title>All Payments</title>
    </head>
    <body>
        <%
            Customer customer = (Customer) session.getAttribute("customer");
            Payment payment = (Payment) session.getAttribute("payment");
         %>
        <h1>IoTBay</h1>
        <hr>
        <div class="top_right_link_div">
            <a href="logout.jsp">Logout</a>
        </div>
        <h2>Payment details</h2>
            <table class="payment_details">
                <tr>
                    <th>Payment Id</th>
                    <th>Is default</th>
                    <th>Credit Card Number</th>
                    <th>Credit Card Expiry</th>
                    <th>Credit Card CVC</th>
                </tr>
                <%
                    %>
                <tr>
                    <td><%=payment.getId()%></td>
                    <td><%=payment.isIsDefault()%></td>
                    <td><%=payment.getCreditCardNumber()%></td>
                    <td><%=payment.getCreditCardExpiry()%></td>
                    <td><%=payment.getCreditCardCVC()%></td>
                </tr>
                <%
                        }
                    }
                    %>
                <tr>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                </tr>
            </table>
    </body>
</html>
