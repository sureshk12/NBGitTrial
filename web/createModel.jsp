<%-- 
    Document   : createModel
    Created on : 25-Oct-2021, 10:39:08 PM
    Author     : Suresh
--%>

<%@page import="org.onida.audio.Product"%>
<%@page import="org.onida.audio.Company"%>
<%@page import="java.util.ArrayList"%>
<%@page import="org.onida.audio.DatabaseHelper"%>
<%@ include file = "header.jsp" %>

<%
if(logStatus.equals("Suresh")) {
    %>
    <main role="main" class="container">
        <div class="row">
            <div class="col-md-8">
                <form action = "CreateModelServlet" method = "POST">
                    <div class="mx-auto" style="width: 200px;">
                        <h6>Enter Model Details</h6>
                    </div> 
                    <select class="form-select" aria-label="Default select example" name="company" >
                        <% 
                            Company comp = new Company();
                            ArrayList<Company> companies = comp.getAllCompanies();
                            for(int c = 0; c < (companies.size()); c++) {
                                %>
                                <option><%= companies.get(c).getName() %></option>
                                <%
                            }
                        %>
                    </select>
                    <select class="form-select" aria-label="Default select example" name="product" >
                        <% 
                            Product prod = new Product();
                            ArrayList<Product> products = prod.getAllProducts();
                            for(int c = 0; c < (products.size()); c++) {
                                %>
                                <option><%= products.get(c).getName() %></option>
                                <%
                            }
                        %>
                    </select>

                    <div class="mb-3">
                        <label for="exampleInputEmail1" class="form-label">Model Name</label>
                        <input type="text" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp" name="model" >
                    </div>
                    <button type="submit" class="btn btn-primary">Create Model</button> 
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
