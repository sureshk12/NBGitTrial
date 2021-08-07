<%-- 
    Document   : create
    Created on : 01 Aug, 2021, 11:26:51 AM
    Author     : suresh
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="org.onida.audio.DatabaseHelper"%>
<%@ include file = "header.jsp" %>

<% 
    int stageInt=0;
    
    //Get Company data
    DatabaseHelper db = new DatabaseHelper();    
    ArrayList<String> arr = new ArrayList<String>();

    String []msg = {"Company","Product","Model"};

    String recdMsg = (String)request.getAttribute("createMsg");
    String company = request.getParameter("comp");
    String product = request.getParameter("prod");
    String model = request.getParameter("modl");    
    String stage = request.getParameter("textInput");
    String selection = request.getParameter("dropDown");
    
    if(recdMsg == null) {
        recdMsg = "";
    }
    if(stage != null) {
        stageInt = Integer.parseInt(stage);
    } else {
        stageInt = 0;
    }  
    
    String err = "";
    stage = Integer.toString(stageInt);
    switch(stageInt){
        case 0:
            arr = db.getCompany();
            if(arr.isEmpty()) {
                err = "No Company resgistered, pls register Company, Product & Model";                
            }
            break;
        case 1:
            company = selection;
            arr = db.getProduct(company);
            if(arr.isEmpty()) {
                err = "No Product resgistered, pls register Product & Model";                
            }
            break;
        case 2: 
            product = selection;
            arr = db.getModel(company,product);
            if(arr.isEmpty()) {
                err = "No Model resgistered, pls register Model";                
            }
            break;
        case 3:
            model = selection;
            break;
        default:
            break;
    }    
%>
<main role="main" class="container">
        <div class="row">
            <div class="col-md-8">
                <%
                if(!err.equals("")) {
                %>
                    <div class="mx-l-5" style="width: 900px;">
                        <p><a href="#" class="text-danger"><%= err %></a></p>
                    </div>
                    <form name="Error" action="create.jsp" method="POST">
                        <input type="hidden" value="<%= err %>" name="createMsg"/>
                        <input type="submit" value="OK" name="ok" />
                    </form>    
                <%                    
                } else {
                    if(stageInt < 3) 
                    {
                        out.println("<br>");
                        out.println(recdMsg);
                        out.println("<br>");
                        out.println("<br>");
                        out.println("<h5>Select :" + msg[stageInt] + "</h5>");
                        %>
                        <form action="createThings.jsp" method="POST">
                            <select class="form-select" aria-label="Default select example" name="dropDown" >
                                <% 
                                    //for(int c = 0; c < msgArray[stageInt].length; c++) {
                                    for(int c = 0; c < (arr.size()); c++) {
                                        %>
                                        <option><%= arr.get(c) %></option>
                                        <%
                                    }
                                    stageInt++;
                                    stage = Integer.toString(stageInt);
                                %>
                            </select>
                            <input type="hidden" name="textInput" value="<%= stage %>" />
                            <input type="hidden" name="comp" value="<%= company %>" />
                            <input type="hidden" name="prod" value="<%= product %>" />
                            <input type="hidden" name="modl" value="<%= model %>" />

                            <input type="submit" value="Select" />
                        </form>
                        <% 
                    }
                    if(company !=null){
                        if(!company.equals("")) {
                            if(!company.equals("null")) {
                                %>Company = <%= company %><br><%
                            }                    
                        }
                    }
                    if(product != null) {
                        if(!product.equals("")) {
                            if(!product.equals("null")) {
                                %>Product = <%= product %><br><%
                            }
                        }
                    }
                    if(model != null) {
                        if(!model.equals("")) {
                            if(!model.equals("null")) {
                                %>Model = <%= model %><br><%
                            }                
                        }
                    }
                }
                %>                            
            </div>            
        </div>                
</main>
    
<%@ include file = "footer.jsp" %>
