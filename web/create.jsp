<%-- 
    Document   : create
    Created on : 01 Aug, 2021, 11:26:51 AM
    Author     : suresh
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="org.onida.audio.DatabaseHelper"%>
<%@ include file = "header.jsp" %>

<%
    String recdMsg = (String)request.getAttribute("createMsg");
    if(recdMsg == null) {
        recdMsg = "";
    }
%>
<main role="main" class="container">
    <div class="row">
        <div class="col-md-8">
            <div class="mx-l-5" style="width: 800px;">
                <p><a href="#" class="text-danger"><%= recdMsg %></a></p>
            </div>
            <div class="mx-l-5" style="width: 200px;">
                <h4>Create</h4>
            </div>
            <br>
            <form action = "createThings.jsp" method = "POST">
                <button type="submit" class="btn btn-primary btn-lg">Things</button>
            </form>
            <br>
            <form action="createNewData.jsp" method="POST">
                <button type="submit" class="btn btn-warning btn-lg">Company</button>
                <input type="hidden" name="stage" value="1" />
            </form>
            <br>
            <form action="createNewData.jsp" method="POST">
                <button type="submit" class="btn btn-success btn-lg">Product</button>
                <input type="hidden" name="stage" value="2" />
            </form>
            <br>
            <form action="createNewData.jsp" method="POST">
                <button type="submit" class="btn btn-danger btn-lg">Model</button>
                <input type="hidden" name="stage" value="3" />
            </form>
        </div>
    </div>
</main>
  