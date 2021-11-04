<%-- 
    Document   : create
    Created on : 01 Aug, 2021, 11:26:51 AM
    Author     : suresh
--%>

<%@page import="org.onida.audio.createIotThings"%>
<%@page import="java.util.ArrayList"%>
<%@page import="org.onida.audio.DatabaseHelper"%>

<!DOCTYPE html>
<html>
    <head>
        <!-- Required meta tags -->
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

        <!-- Bootstrap CSS -->        
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
            integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
        <link rel="stylesheet" type="text/css" href="static/css/main.css">
        <title>
            Trial
        </title>

<%

/*
NEW IMPLEMENTATION
*/
    DatabaseHelper db = new DatabaseHelper();
    ArrayList<String> arr = new ArrayList<>();
    arr = db.getCompany();
    String msg="";
    for(int x = 0; x < arr.size(); x++){
        if (msg.equals("")) {
            msg += arr.get(x) ;
        } else {
            msg += "," + arr.get(x) ;
        }        
    }
%>
<main role="main" class="container">
    <div class="row">
        <div class="col-md-8">
            <ul id="ul-id"></ul>
            <select id="form-select-company" aria-label="Default select example" name="dropDown" >
                <option>Select Company</option>
            </select>
            <br>
            <select id="form-select-product" aria-label="Default select example" name="dropDown" >
                <option>Select Product</option>
            </select>
            <br>
            <select id="form-select-model" aria-label="Default select example" name="dropDown"  >
                <option>Select Model</option>
            </select>
            <br>
            <input type="text" name="numThing" id="numthings" />
            <input type="hidden" name="compList" id="complist" value=<%=msg%> />
        </div>            
    </div>                
</main>
    <script src="JavaScript/newCreateThings.js"></script>
    
<%@ include file = "footer.jsp" %>
