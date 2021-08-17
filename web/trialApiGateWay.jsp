<%-- 
    Document   : listData
    Created on : 18 Jul, 2021, 11:05:21 PM
    Author     : suresh
--%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="org.json.JSONArray"%>
<%@page import="org.json.JSONObject"%>
<%@page import="java.util.Iterator"%>
<%@page import="org.onida.audio.Service"%>

<%@ include file = "header.jsp" %>

<%
    if(logStatus.equals("Suresh")) {
        %>

<main role="main" class="container">
    <div class="row">
        <div class="col-md-8">           
            <p>
                <%
                    String result="Invalid Operation";
                    JSONObject js = new JSONObject();
                    js.put("createNewTable", "YES");
                    js.put("dbName", "ONTV");
                    js.put("createFirstRow", "NO");
                    js.put("company", "Onida");
                    js.put("product", "Television");
                    js.put("createNewThings", "NO");
                    js.put("numOfDevices", 2);
                    //js.put("", "");
                    try {
                        Service srTable = new Service();
                        Service.setBaseUrl("https://e5ggs311eb.execute-api.us-east-1.amazonaws.com/live/createtable");                        
                        Service.setJsonInputString(js.toString());
                        //Service.setApiKey("0ePtduBoz33Nw8Qq2iYRM8TAOS3n27xy3Gf9YdGt");                        
                        result = srTable.getOnidaIotDataString();
                    }catch(Exception e) {
                        e.printStackTrace();
                    }
                    out.print(result + "<br><br><br>");
                    Thread.sleep(10000);
                    try {
                        Service srFirstLine = new Service();
                        Service.setBaseUrl("https://e5ggs311eb.execute-api.us-east-1.amazonaws.com/live/createtable");    
                        js.put("createNewTable", "NO");
                        js.put("createFirstRow", "YES");
                        Service.setJsonInputString(js.toString());
                        result = srFirstLine.getOnidaIotDataString();
                        out.print(result + "<br>");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                %>
            </p>
        </div>
    </div>
</main>
                
                
        <%       
    }
%>
<%@ include file = "footer.jsp" %>
