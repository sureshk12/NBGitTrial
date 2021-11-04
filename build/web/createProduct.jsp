<%-- 
    Document   : createCompany
    Created on : 25-Oct-2021, 5:21:37 PM
    Author     : Suresh
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="org.onida.audio.DatabaseHelper"%>
<%@ include file = "header.jsp" %>

<%
if(logStatus.equals("Suresh")) {
    %>
    <main role="main" class="container">
        <div class="row">
            <div class="col-md-8">
                <form action = "CreateProductServlet" method = "POST">
                    <div class="mx-auto" style="width: 200px;">
                        <h6>Enter Company Details</h6>
                    </div>
                    <div class="mb-3">
                        <label for="exampleInputEmail1" class="form-label">Product Name</label>
                        <input type="text" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp" name="name" maxlength="30" minlength="3"> 
                    </div>
                    <div class="mb-3">
                        <label for="exampleInputEmail1" class="form-label">Product Code</label>
                        <input type="text" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp" name="code" style="text-transform:lowercase" maxlength="2" minlength="2" > 
                    </div>
                    <div class="mb-3">
                        <label for="exampleInputEmail1" class="form-label">Description</label>
                        <input type="text" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp" name="description" maxlength="45" minlength="3"> 
                    </div>
                    <button type="submit" class="btn btn-primary">Create Product</button>                    
                </form>
                <%
                String msg = (String)request.getAttribute("msg");
                if(msg != null) {
                    %>
                    <div class="alert alert-danger" role=alert">
                        <p><a href="#" class="text-danger"><%= msg %></a></p>
                    </div>
                    <%                    
                    }
                %>
                    
            </div>
        </div>
    </main>
    <%
}
%>

<%@ include file = "footer.jsp" %>
        
