<%@page import="uts.isd.group30.model.Payment"%>
<%@page import="java.util.ArrayList"%>
<%@page import="uts.isd.group30.model.Customer"%>
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
            ArrayList<Payment> paymentList = (ArrayList<Payment>) session.getAttribute("paymentList");
            //gets the array of payments that the user has
            String DefaultString = "False";
            //Sets the default answer for the isDefault field
        %>
        <div class="header">
            <h1>IoTBay</h1>
        </div>
        <div class="top_right_link_div">
            <a href="logout.jsp">Logout</a>
            <a href="myDetails.jsp">My Details</a>
            <a href="OrderListServlet?action=list">Order List</a>
            <a href="CatalogueServlet?action=list">Catalogue</a>
            <a href="CurrentOrderServlet">View Order [${cart.size()}]</a>
            <a href="index.jsp">Home</a>
        </div>
        <h2>Payment Methods</h2>
        <%
            if (customer != null) {
                //Checks if customer is anonymous
                try {
        %>
        <form action="searchPaymentServlet" method="post">
            <table>
                <tr>
                    <th>Search</th>
                    <th> 
                        <select name="searchOption">
                            <option value="creditCardNumberSearch">Credit Card Number</option>
                            <option value="dateSearch">Date(dd-mm-yyyy)</option>
                        </select>
                    </th>
                    <th><input type="text" name="searchBox" required></th>
                    <th><input type="submit" name="submitSearch"></th>
                </tr>
            </table>
        </form>
        <table class="order_list_table">
            <tr>
                <th>Credit Card Number</th>
                <th>Credit Card Expiry</th>
                <th>Credit Card CVC</th>
                <th>First added</th>
                <th>Last modified</th>
                <th>Default</th>
                <th>Update</th>
                <th>Delete</th>
            </tr>
            <%
                if (paymentList.size() > 0) {
                    for (int i = 0; i < paymentList.size(); i++) {
                        if (paymentList.get(i).getIsDefault() == 1) {
                            DefaultString = "True";
                        } else {
                            DefaultString = "False";
                        }
                        //For loop to iterate through the entire paymentList and convert the default varaible from a int to a String
            %>
            <tr>
                <td><%=paymentList.get(i).getCreditCardNumber()%></td>
                <td><%=paymentList.get(i).getCreditCardExpiry()%></td>
                <td><%=paymentList.get(i).getCreditCardCVC()%></td>
                <td><%=paymentList.get(i).getCreated()%></td>
                <td><%=paymentList.get(i).getLastUpdated()%></td>
                <td><%=DefaultString%></td>
                <td><a href="PaymentServlet?action=update&index=<%=i%>">Update</a></td><!--Sends information about which Payment has been selected for updates-->
                <td><a href="PaymentServlet?action=delete&number=<%=i%>">Delete</a></td> <!--This sends information about which Payment has been selected for deletion-->
            </tr>
            <%
                    }
                }
            %>
            <tr>
                <td colspan="8"><a href="addPayment.jsp">Add new payment</a></td><!--After the if statement so it is always present-->
            </tr>
        </table>
        <%
            } catch (Exception ex) {
                ;
            }
        } else {
        %>
        <h4>Please log in to load your Payment methods</h4><!--For anonymous customers-->
        <%
            }
        %>
    </body>
</html>