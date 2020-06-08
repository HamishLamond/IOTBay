<%-- 
    Document   : updateDetails
    Created on : 08/06/2020, 4:21:50 PM
    Author     : Zunther
--%>

<%@page import="uts.isd.group30.model.Customer"%>
<%@page import="uts.isd.group30.model.Staff"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="css/IoTBayCSS.css">
        <title>UpdateDetails</title>
    </head>
    <body>
        <%
            Customer customer;
            Staff staff;
            String name = "";
            String email = "";
            String password = "";
            String address = "";
            Integer phoneNumber = null;
            Boolean isStaff = session.getAttribute("userType") == "staff";

            if (!isStaff) {
                customer = (Customer) session.getAttribute("customer");
                name = customer.getName();
                email = customer.getEmail();
                password = customer.getPassword();
                phoneNumber = customer.getPhoneNumber();
                address = customer.getAddress();
            } else if (isStaff) {
                staff = (Staff) session.getAttribute("staff");
                name = staff.getName();
                email = staff.getEmail();
                password = staff.getPassword();
                phoneNumber = staff.getPhone();
            }
        %>
        <div class="header">
            <h1>Update Details</h1>
        </div>
        <div class="top_right_link_div">
            <a href="logout.jsp">Logout</a>
            <a href="myDetails.jsp">My Details</a>
            <a href="OrderListServlet?action=list">Order List</a>
            <a href="PaymentServlet?action=viewList">View Payment list</a>
            <a href="CatalogueServlet?action=list">Catalogue</a>
            <a href="CurrentOrderServlet">View Order [${cart.size()}]</a>
            <a href="index.jsp">Home</a>
        </div>
        <hr>
        <form action="UpdateDetailsServlet" method="post">
            <table>
                <tr>
                    <td><label for="name">Full name</label></td>
                    <td><input class="form_input_box" type="text" id ="frame" name="name" placeholder="John Smith" required value="<%=name%>">
                        <%
                            if (session.getAttribute("upNameErr") != null) {
                        %>
                        <div><span class="err-msg"><%=session.getAttribute("upNameErr")%></span></div>

                        <% }
                        %></td>
                </tr>
                <tr>
                    <td><label for="email">Email</label></td>
                    <td><input class="form_input_box" type="email" id ="frame" name="email" disabled placeholder="JohnSmith@gmail.com" required value="<%=email%>">
                        <%
                            if (session.getAttribute("upEmailErr") != null) {
                        %>
                        <div><span class="err-msg"><%=session.getAttribute("upEmailErr")%></span></div>

                        <% }
                        %></td>
                </tr>
                <tr>
                    <td><label for="password">Password</label></td>
                    <td><input class="form_input_box" type="password" id ="frame" name="password" placeholder="Password123_" required value="<%=password%>">
                        <%
                            if (session.getAttribute("upPassErr") != null) {
                        %>
                        <div><span class="err-msg"><%=session.getAttribute("upPassErr")%></span></div>

                        <% }
                        %></td>
                </tr>

                <%
                    if (!isStaff) {
                %>
                <tr>
                    <td><label for="address">Address</label></td>
                    <td><input class="form_input_box" type="text" id ="frame" name="address" placeholder="123 George Street, Sydney" required value="<%=address%>"></td>
                </tr>

                <% }
                %>
                <tr>
                    <td><label for="phoneNumber">Phone Number</label></td>
                    <td><input class="form_input_box" type="tel" id ="frame" name="phoneNumber" placeholder="0#########" required value="<%=phoneNumber%>" >
                        <%
                            if (session.getAttribute("upPhoneErr") != null) {
                        %>
                        <div><span class="err-msg"><%=session.getAttribute("upPhoneErr")%></span></div>

                        <% }
                        %></td>
                </tr>
                <tr>
                    <td></td>
                    <td><center>
                    <input class="button" type ="submit" value="Update" required>
                </center>
                </td>
                </tr>
            </table>
        </form>
    </body>
</html>
