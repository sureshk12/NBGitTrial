<%-- 
    Document   : thingsDynamoRegister
    Created on : 25-Aug-2021, 1:30:51 PM
    Author     : Suresh
--%>

<%@page import="org.onida.audio.DatabaseHelper"%>
<%@page import="org.onida.audio.Thing"%>
<%@page import="java.util.ArrayList"%>
<%@page import="org.onida.audio.ThingsDashBoard"%>
<%@page import="org.onida.audio.CreateDbTable"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>thingsDynamoRegister</title>
    </head>
    <body>
        <%
            String msg = "Dynamo DB Updated";
            int cou = 0;
            ArrayList nonRegThings = ThingsDashBoard.getNonRegThings(ThingsDashBoard.thingsDbName, false);
            for(int i = 1; i < nonRegThings.size(); i++) {
                Thing thing = (Thing) nonRegThings.get(i);
                if(thing.getDynamo() == 0){
                    String retVal = CreateDbTable.thingsUpdate(thing.getSerialNumber(), thing.getActivationCode(), thing.getAwsCode(), thing.getMobileCode());

                    DatabaseHelper db = new DatabaseHelper();
                    db.updatedynamoLocal(ThingsDashBoard.thingsDbName, thing.getSerialNumber());
                    cou++;
                } 
            }
            if(cou == 0) {
                msg = "NOTHING to UPDATE all Things  are updated";
            } else {
                msg = Integer.toString(cou) + " Things update in Dynamo DB";
            }
        %>
        <jsp:forward page="index.jsp">
            <jsp:param name="msg" value= "<%= msg %>" />
        </jsp:forward>
    </body>
</html>
