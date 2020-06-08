
<%@page import="uts.isd.group30.model.Staff"%>
<%@page import="uts.isd.group30.model.Customer"%>
<%@page import="java.time.LocalDateTime"%>
<%@page import="uts.isd.group30.model.AccessLog"%>
<%@page import="uts.isd.group30.model.dao.DBManager"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="css/IoTBayCSS.css">
        <title>Logout</title>
    </head>
    <body>
        <div class="header">
            <h1>IoTBay Log Out</h1>
        </div>
        <p>You have successfully logged out of your account.</p>
        <a class ="middle_link_button" href="index.jsp">IoTBay Landing Page</a>
        <% 
            Integer userId;
            if (session.getAttribute("userType") == "customer")
            {
                Customer customer = (Customer)session.getAttribute("customer");
                userId = customer.getId();
            }
            else
            {
                Staff staff = (Staff)session.getAttribute("staff");
                userId = staff.getId();
            }
            //Do logout access log addition here.
            DBManager manager = (DBManager)session.getAttribute("dbmanager");
            manager.addAccessLog(new AccessLog(userId, null, "logout", LocalDateTime.now()));
            session.invalidate(); %>
    </body>
</html>
