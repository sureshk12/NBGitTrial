<%-- 
    Document   : thingsDashBoard
    Created on : 21-Aug-2021, 2:06:23 PM
    Author     : Suresh
--%>

<%@page import="java.security.SecureRandom"%>
<%@page import="org.onida.audio.Thing"%>
<%@page import="org.onida.audio.ThingsDashBoard"%>
<%@page import="java.util.ArrayList"%>
<%@page import="org.onida.audio.DatabaseHelper"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <!-- Required meta tags -->
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <!-- Bootstrap CSS -->        
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
            integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
        <link rel="stylesheet" type="text/css" href="static/css/main.css">
        <title>Onida IOT - Things Dashboard</title>
        <link rel="shortcut icon" href="static/image/onida_transparent.ico" type="image/x-icon"/>
    </head>
    <body>
        <header class="site-header">
            <nav class="navbar navbar-expand-md navbar-dark bg-steel fixed-top">
                <div class="container">
                    <div class="nav-item nav-link">
                        <image src="static/image/onida_transparent_big.png" ></image>
                        <a class="navbar-brand mr-4" href="index.jsp">Onida IOT</a>
                    </div>                    
                    <!-- <a class="navbar-brand mr-4" href="index.jsp">Onida IOT</a> -->
                    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarToggle"
                        aria-controls="navbarToggle" aria-expanded="false" aria-label="Toggle navigation">
                        <span class="navbar-toggler-icon"></span>
                    </button>
                </div>
            </nav>
        </header>
        <main role="main" class="container">
            <p>To Update Data to AWS Server, Press </p>
            <form action="thingsDynamoRegister.jsp" method="POST">
                <button type="submit" class="btn btn-primary">SUBMIT</button>
            </form>
            <%
            String mode = request.getParameter("mode");
            if(mode.equals("START")) {
                %>
                <div class="row">
                    <div class="col"><h6>Company : <%=ThingsDashBoard.company%></h6></div>
                    <div class="col"><h6>Product : <%=ThingsDashBoard.product%></h6></div>
                    <div class="col"><h6>Model : <%=ThingsDashBoard.model%></h6></div>
                    <div class="col"><h6>Thing Code : <%=ThingsDashBoard.thingsName%></h6></div>
                    <div class="col"><h6>Database: <%=ThingsDashBoard.thingsDbName  %></h6></div>
                </div>
                <br>
                <div class="row">
                    <div class="col"><h6>Serial Number</h6></div>
                    <div class="col"><h6>Activation Code</h6></div>
                    <div class="col"><h6>AWS Code</h6></div>
                    <div class="col"><h6>Mobile Code</h6></div>
                    <div class="col"><h6>MAC Id</h6></div>
                </div>          
                <%
                ArrayList nonRegThings = ThingsDashBoard.getNonRegThings(ThingsDashBoard.thingsDbName, false);
                for(int i = 1; i< nonRegThings.size(); i++ ) {
                    Thing thing = (Thing)nonRegThings.get(i);
                    if(thing.getDynamo() == 0) {
                        %>
                        <div class="row">
                            <div class="col"><%= thing.getSerialNumber() %></div>
                            <div class="col"><%= thing.getActivationCode() %></div>
                            <div class="col"><%= thing.getAwsCode() %></div>
                            <div class="col"><%= thing.getMobileCode() %></div>
                            <div class="col"><%= thing.getMacCode() %></div>
                        </div>               
                        <%
                    }
                }
            } else {
                %>
                <table>
                <%
                //TODO
                //Disply things not registered in Dynamo DB
                %>
                <div class="row">
                    <div class="col"><h6>Company : <%=ThingsDashBoard.company%></h6></div>
                    <div class="col"><h6>Product : <%=ThingsDashBoard.product%></h6></div>
                    <div class="col"><h6>Model : <%=ThingsDashBoard.model%></h6></div>
                    <div class="col"><h6>Thing Code : <%=ThingsDashBoard.thingsName%></h6></div>
                    <div class="col"><h6>Database: <%=ThingsDashBoard.thingsDbName  %></h6></div>
                </div>
                <br>
                <div class="row">
                    <div class="col"><h6>Serial Number</h6></div>
                    <div class="col"><h6>Activation Code</h6></div>
                    <div class="col"><h6>AWS Code</h6></div>
                    <div class="col"><h6>Mobile Code</h6></div>
                    <div class="col"><h6>MAC Id</h6></div>
                </div>          
                <%
                ArrayList nonRegThings = ThingsDashBoard.getNonRegThings(ThingsDashBoard.thingsDbName, false);
                for(int i =0 ; i < nonRegThings.size(); i++) {
                    Thing thg = (Thing)nonRegThings.get(i);
                    if(thg.getDynamo() == 0) {
                        %>
                        <div class="row">
                            <div class="col"><%= thg.getSerialNumber() %></div>
                            <div class="col"><%= thg.getActivationCode() %></div>
                            <div class="col"><%= thg.getAwsCode() %></div>
                            <div class="col"><%= thg.getMobileCode() %></div>
                            <div class="col"><%= thg.getMacCode() %></div>
                        </div>               
                        <%
                    }                
                }
                //Receive Mac
                String mac = request.getParameter("mac");
                if (mac != null) {
    //                String temp = ThingsDashBoard.thingsDbName;
                    String retVal = Thing.storeThing(mac);
                    if(retVal.equals("ERROR")) {
                        out.println("ERROR could not create new Thing");
                    } else {
                        out.println(retVal);
                        
                        String [] retValArr = retVal.split(",");
                        %>
                        <div class="row">
                            <div class="col"><p class="text-danger"><%= retValArr[0] %></p></div>
                            <div class="col"><p class="text-danger"><%= retValArr[1] %></p></div>
                            <div class="col"><p class="text-danger"><%= retValArr[2] %></p></div>
                            <div class="col"><p class="text-danger"><%= retValArr[3] %></p></div>
                            <div class="col"><p class="text-danger"><%= retValArr[4] %></p></div>
                        </div>               
                        <%
                    }
                }
                //Send back Serial, activation code, awws code, mobile code in string form with comma separted.           
                %>
                </table>
                <%
            }
    //    // util to print bytes in hex
    //    private static String convertBytesToHex(byte[] bytes) {
    //        StringBuilder result = new StringBuilder();
    //        for (byte temp : bytes) {
    //            result.append(String.format("%02x", temp));
    //        }
    //        return result.toString();
            %>
        </main>
    </body>
</html>
