<%-- 
    Document   : create
    Created on : 01 Aug, 2021, 11:26:51 AM
    Author     : suresh
--%>

<%@page import="org.onida.audio.ThingsDashBoard"%>
<%@page import="org.onida.audio.createIotThings"%>
<%@page import="java.util.ArrayList"%>
<%@page import="org.onida.audio.DatabaseHelper"%>
<%@ include file = "headerNoTimeOut.jsp" %>


<%
    if(logStatus.equals("Suresh")) {
        %>

<% 
    int stageInt=0;
    
    //Get Company data
    DatabaseHelper db = new DatabaseHelper();    
    ArrayList<String> arr = new ArrayList<>();

    String []msg = {"Company","Product","Model"};

    String recdMsg = (String)request.getAttribute("createMsg");
    String company = request.getParameter("comp");
    String product = request.getParameter("prod");
    String model = request.getParameter("modl");    
    String stage = request.getParameter("textInput");
    String selection = request.getParameter("dropDown");
    String numThing = request.getParameter("numThing");
    
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
                    } else if(stageInt == 3) {
                        if(product != null) {
                            if(!model.equals("")) {
                                if(!model.equals("null")) {
                                    %>Company = <%= company %><br><%
                                    %>Product = <%= product %><br><%
                                    %>Model = <%= model %><br><%
                                }
                            }
                        }
                        stageInt ++;
                        stage = Integer.toString(stageInt);
                        ThingsDashBoard tdb = new ThingsDashBoard();
                        ThingsDashBoard.company = company;
                        ThingsDashBoard.product = product;
                        ThingsDashBoard.model = model;
                        String thingsName = db.getThingsCode(company, product, model);
                        ThingsDashBoard.thingsName = thingsName;
                        String thingsDbName = thingsName.substring(0,4).toLowerCase();
                        ThingsDashBoard.thingsDbName = thingsDbName;
                        ThingsDashBoard.arrDisplayThings.clear();

                        %>
                        <form action="thingsDashBoard.jsp" method="POST">
                        <!-- <form action="createThings.jsp" method="POST"> -->
                            <!-- <input type="text" name="numThing" /> -->
                            <input type="hidden" name="textInput" value="<%= stage %>" />
                            <input type="hidden" name="comp" value="<%= company %>" />
                            <input type="hidden" name="prod" value="<%= product %>" />
                            <input type="hidden" name="modl" value="<%= model %>" />
                            <input type="hidden" name="databaseName" value="<%= thingsDbName %>" />
                            <input type="hidden" name="mode" value="START" />
                            <button type="submit" class="btn btn-primary">Submit</button>
                        </form>    
                <%  
                    } else {
                        String thingStr = db.getThingsCode(company, product, model);
                        String dbName = thingStr.substring(0,4);
                        int num = Integer.parseInt(numThing);
                        //createIotThings.createThings(dbName, company, product, thingStr, num);
                        out.println("DB = "+dbName+"<br>Company = "+company+"<br>Product = "+product+"<br>ThingCode = "+ thingStr + "<br>Number = "+ num);
                    }
                }
                %>                            
            </div>            
        </div>                
</main>
            
<%       
    }
%>
    
<%@ include file = "footer.jsp" %>
