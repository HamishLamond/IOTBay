<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="css/IoTBayCSS.css">
        <title>Register</title>
    </head>
    <body>
        <div class="header">
            <h1>IoTBay</h1>
        </div>
        <h2>Account Creation (Customer)</h2>
        <form action="CustomerRegistrationServlet" method="post">
            <table>
                <tr>
                    <td><label for="name">Full name</label></td>
                    <td><input class="form_input_box" type="text" id ="frame" name="name" placeholder="John Smith" required>
                        <%
                            if (session.getAttribute("regNameErr") != null) {
                        %>
                        <div><span class="err-msg"><%=session.getAttribute("regNameErr")%></span></div>

                        <% }
                        %></td>
                </tr>
                <tr>
                    <td><label for="email">Email</label></td>
                    <td><input class="form_input_box" type="email" id ="frame" name="email" placeholder="JohnSmith@gmail.com" required>
                        <%
                            if (session.getAttribute("regEmailErr") != null) {
                        %>
                        <div><span class="err-msg"><%=session.getAttribute("regEmailErr")%></span></div>

                        <% }
                        %></td>
                </tr>
                <tr>
                    <td><label for="password">Password</label></td>
                    <td><input class="form_input_box" type="password" id ="frame" name="password" placeholder="Password123_" required>
                        <%
                            if (session.getAttribute("regPassErr") != null) {
                        %>
                        <div><span class="err-msg"><%=session.getAttribute("regPassErr")%></span></div>

                        <% }
                        %></td>
                </tr>
                <tr>
                    <td><label for="address">Address</label></td>
                    <td><input class="form_input_box" type="text" id ="frame" name="address" placeholder="123 George Street, Sydney" required></td>
                </tr>
                <tr>
                    <td><label for="phoneNumber">Phone Number</label></td>
                    <td><input class="form_input_box" type="tel" id ="frame" name="phoneNumber" placeholder="0#########" pattern="[0]{1}[0-9]{9}" required title="A valid 10 digit phone number starting with 0">
                        <%
                            if (session.getAttribute("regPhoneErr") != null) {
                        %>
                        <div><span class="err-msg"><%=session.getAttribute("regPhoneErr")%></span></div>

                        <% }
                        %></td>
                </tr>
                <tr>
                    <td></td>
                    <td><center>
                    <input type="hidden" name="origin"  value="register">
                    <input class="button" type ="submit" value="Register" required>
                </center>
                </td>
                </tr>
            </table>
        </form>
    </body>
</html>
