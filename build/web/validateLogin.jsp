<%-- 
    Document   : validateLogin
    Created on : 18 Jul, 2021, 12:29:32 PM
    Author     : sures
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import = " java.util.* " %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <% 
            //String dataIn = request.getParameter("comp");
            String usrEmail = request.getParameter("userEmail");
            String usrPwd = request.getParameter("userPassword");
            int err = 0;
            if(usrEmail != null && usrEmail.equals("suresh@ashank.com")) {
                if(usrPwd != null && usrPwd.equals("Vidya@100")) {
                    err = 1;

                    String idData = new String("Suresh");
                    session.setAttribute("u_name", idData);
                    session.setMaxInactiveInterval(300);
                    //response.sendRedirect("index.jsp");
                    %>
                        <jsp:forward page="index.jsp">
                            <jsp:param name="msg" value="You are now Logged In" />
                        </jsp:forward>
                    <%
                }
            }
            if(err == 0) {
                String idData = new String("kumar");
                session.setAttribute("u_name", idData);
                //response.sendRedirect("login.jsp");
                %>
                        <jsp:forward page="login.jsp">
                            <jsp:param name="msg" value="Invalid Credentials, Try Once again" />
                        </jsp:forward>
                <%
            }
        %>
    </body>
</html>
