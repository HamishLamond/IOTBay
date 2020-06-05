<%-- 
    Document   : welcome
    Created on : 01/05/2020, 8:39:30 PM
    Author     : USER
--%>

<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@page import="uts.isd.group30.model.Customer"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="css/IoTBayCSS.css">
        <title>Welcome Page</title>
    </head>
    <body>
        <div class="header">
            <h1>IoTBay</h1>
        </div>
        <%
            String origin = request.getParameter("origin");
            if (origin.equals("register")){
                String name = request.getParameter("name");
                String email = request.getParameter("email");
                String password = request.getParameter("password");
                int phoneNumber = Integer.parseInt(request.getParameter("phoneNumber"));
                String address = request.getParameter("address");
        %>
        <h2>You have successfully registered for IoTBay!</h2>
        <p> Your name is: <%=name %></p>
        <p> Your email is: <%=email %></p>
        <p> Your password is: <%=password %></p>
        <p> Your phone number is: 0<%=phoneNumber %></p>
        <p> Your address is: <%=address %></p>
        <a class="middle_link_button" href="main.jsp">Click to Enter IoTBay</a>
        
        
        <%
            Customer customer = new Customer(name, address, email, phoneNumber, password);
            session.setAttribute("customer", customer);
            } else{
                String email = request.getParameter("email");
                String password = request.getParameter("password");
        %>
        <h2>You have successfully logged into IoTBay!</h2>
        <p> Your email is: <%=email %></p>
        <p> Your password is: <%=password %></p>
        <a class="middle_link_button" href="main.jsp">Click to Enter IoTBay</a>
        
        
        <%
                Customer customer = new Customer();
                customer.setEmail(email);
                customer.setPassword(password);
                session.setAttribute("customer", customer);
                }
            HashMap<String, Integer> cart = new HashMap<String, Integer>();
            session.setAttribute("cart", cart);
             %>
    </body>
</html>
