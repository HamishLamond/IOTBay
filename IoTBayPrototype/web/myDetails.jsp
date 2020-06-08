
<%@page import="uts.isd.group30.model.Staff"%>
<%@page import="uts.isd.group30.model.Customer"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="css/IoTBayCSS.css">
        <title>Details Page</title>
    </head>
    <body class="body_no_image">
        <div class="header">
            <h1>IoTBay</h1>
        </div>
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
        <div class="top_right_link_div">
            <a href="logout.jsp">Logout</a>
            <a href="myDetails.jsp">My Details</a>
            <a href="OrderListServlet?action=list">Order List</a>
            <a href="PaymentServlet?action=viewList">View Payment list</a>
            <a href="CatalogueServlet?action=list">Catalogue</a>
            <a href="CurrentOrderServlet">View Order [${cart.size()}]</a>
            <%
                if (isStaff) {
            %>
            <a href="staffWelcome.jsp">Home</a>
            <% } else {
            %>
            <a href="index.jsp">Home</a>
            <%
                }
            %>

        </div>
        <h2>These are your current details:</h2>
        <table>
            <tr>
                <td>
                    Email Address:
                </td>
                <td>
                    <%=email%>
                </td>
            </tr>
            <tr>
                <td>
                    Password:
                </td>
                <td>
                    <%=password%>
                </td>
            </tr>
            <tr>
                <td>
                    Name:
                </td>
                <td>
                    <%=name%>
                </td>
            </tr>
            <tr>
                <td>
                    Contact Number
                </td>
                <td>
                    <%=phoneNumber%>
                </td>
            </tr>
            <%
                if (!isStaff) {
            %>
            <tr>
                <td>
                    Delivery Address:
                </td>
                <td>
                    <%=address%>
                </td>
            </tr>

            <% }
            %>


        </table>
        <a class="middle_link_button" href="updateDetails.jsp">Edit my Details</a>
        <a class="middle_link_button" href="deleteAccount.jsp">Delete my Account</a>
    </body>
</html>
