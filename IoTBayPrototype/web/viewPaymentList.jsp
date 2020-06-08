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
            String DefaultString = "False";
         %>
        <h1>IoTBay</h1>
        <div class="top_right_link_div">
            <a href="logout.jsp">Logout</a>
            <a href="CatalogueServlet?action=list">Catalogue</a>
            <a href="OrderListServlet?action=list">Order List</a>
            <a href="CurrentOrderServlet">View Order [${cart.size()}]</a>
            <a href="main.jsp">Home</a>
        </div>
        <h2>Payment History</h2>
            <table class="order_list_table">
                <tr>
                    <th>Credit Card Number</th>
                    <th>Credit Card Expiry</th>
                    <th>Credit Card CVC</th>
                    <th>Default</th>
                    <th>Delete</th>
                    <th>Update</th>
                </tr>
                <%
                    try{
                        
                        if (paymentList.size() > 0){
                        for (int i = 0; i < paymentList.size(); i++){
                            if(paymentList.get(i).getIsDefault()==1){
                                DefaultString = "True";
                            }
                            else{
                                DefaultString = "False";
                            }
                    %>
                <tr>
                    <td><%=paymentList.get(i).getCreditCardNumber()%></td>
                    <td><%=paymentList.get(i).getCreditCardExpiry()%></td>
                    <td><%=paymentList.get(i).getCreditCardCVC()%></td>
                    <td><%=DefaultString%></td>
                    <td><a href="PaymentServlet?action=delete&number=<%=paymentList.get(i).getCreditCardNumber()%>">Delete</a></td>
                    <td><a href="PaymentServlet?action=update&CCN=<%=paymentList.get(i).getCreditCardNumber()%>&CCE=<%=paymentList.get(i).getCreditCardExpiry()%>&CCCVC=<%=paymentList.get(i).getCreditCardCVC()%>&CId=<%=1%>&Default=<%=paymentList.get(i).getIsDefault()%>">Update</a></td>
                </tr>
                <%
                        }
                        }
                    }
                    catch (Exception ex) {
                        ;
                    }
                    %>
                <tr>
                    <td colspan="6"><a href="addPayment.jsp">Add new payment</a></td>
                </tr>
            </table>
                
    </body>
</html>
