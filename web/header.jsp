<%-- 
    Document   : headerjsp
    Created on : 17 Jul, 2021, 10:21:56 PM
    Author     : suresh
--%>

<%@page import="java.lang.String"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import = " java.util.* " %>
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
        <%                                       
            String [] urlStr = { 
                "index.jsp?title=0",
                //"blog.jsp?title=1",
                "displayData.jsp?title=1",//was 2
                "create.jsp?title=2",//was 3
                "about.jsp?title=3"//was 4
            };                                
            String [] titleStr = {
                "Home",
                //"Blog",
                "Display",
                "Create",
                "About"
            };
            //Logging
            String logStatus = (String)session.getAttribute("u_name");
            if(logStatus == null) {
                logStatus = "NO";
            }
            if(!logStatus.equals("Suresh")) {
                for (int s = 0; s < urlStr.length; s++) {
                    urlStr[s] = "";
                    titleStr[s]= "";
                }                    
            }
            
            String title = request.getParameter("title");
            int titleInt = 0;
            if(title != null) {
                titleInt = Integer.parseInt(title);
            }
            out.print("Onida IOT - " + titleStr[titleInt]);
        %>
        </title>              
        <link rel="shortcut icon" href="static/image/onida_transparent.ico" type="image/x-icon"/>
    </head>
    <body>        
        <header class="site-header">
            <nav class="navbar navbar-expand-md navbar-dark bg-steel fixed-top">
                <div class="container">
                    <div class="nav-item nav-link">
                        <image src="static/image/onida_transparent_big.png" ></image>
                    </div>                    
                    <a class="navbar-brand mr-4" href="index.jsp">Onida IOT</a>
                    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarToggle"
                        aria-controls="navbarToggle" aria-expanded="false" aria-label="Toggle navigation">
                        <span class="navbar-toggler-icon"></span>
                    </button>
                    <div class="collapse navbar-collapse" id="navbarToggle">
                        <div class="navbar-nav mr-auto">
                            <%
                                for(int i = 0; i < urlStr.length; i++) {
                                    %>
                                        <a class="nav-item nav-link" href=<%out.print(urlStr[i] + ">" + titleStr[i]);%></a>
                                    <%
                                }
                            %>
                        </div>
                        <!-- Navbar Right Side -->                        
                        <div class="navbar-nav">
                            <a class="nav-item nav-link" href="login.jsp">Login</a>
                            <!-- <a class="nav-item nav-link" href="/register">Register</a> -->
                        </div>
                    </div>
                </div>
            </nav>
        </header>
        <%
            if(!logStatus.equals("Suresh")) {
               %>
                       <div class="alert alert-danger" role=alert">
                           <h5>You have not Logged in, Please log in to use the System</h5>
                       </div>
               <%
            }                   
        %>

