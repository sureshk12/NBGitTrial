<%-- 
    Document   : index
    Created on : 17 Jul, 2021, 7:25:38 PM
    Author     : sures
--%>

<%@ include file = "header.jsp" %>

<main role="main" class="container">
    <div class="row">
        <div class="col-md-8">
            <h1>Welcome to Onida IOT System</h1>
            <% 
                String msg = request.getParameter("msg");
                if(msg != null) {
                    %>
                        <div class="alert alert-success" role=alert">
                            <% out.print(msg);%>
                        </div>                  
                    <%
                }
            %>
        </div>
    </div>
</main>

<%@ include file = "footer.jsp" %>
