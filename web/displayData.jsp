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
      
            
            <table class="table table-striped table-hover table-bordered">
                <thead>
                  <tr>
                    <th scope="col">#</th>
                    <th scope="col">Serial No.</th>
                    <th scope="col">Activation</th>
                    <th scope="col">Mobile</th>
                    <th scope="col">AWS</th>
                  </tr>
                </thead>
                <tbody>
                <%
                    String result="Invalid Operation";
                    try {
                        Service sr = new Service();
                        Service.setBaseUrl("https://uaxjpzyru2.execute-api.us-east-1.amazonaws.com/live/kumar");
                        Service.setJsonInputString("{'name': 'Suresh', 'job': 'Programmer'}");
                        Service.setApiKey("0ePtduBoz33Nw8Qq2iYRM8TAOS3n27xy3Gf9YdGt");
                        
                        result = sr.getOnidaIotDataString();        
                        //result =  "{\"Items\": [{\"mobileCode\": \"e4a262b51c98\", \"awsCode\": \"5799b6da744c\", \"serialNumber\": \"11Q4210004\", \"activationCode\": \"062e72cc37\"}, {\"mobileCode\": \"47784e6ad17e\", \"awsCode\": \"ff806eecf254\", \"serialNumber\": \"11Q4210003\", \"activationCode\": \"612fa2c711\"}, {\"mobileCode\": \"9c49a9395a66\", \"awsCode\": \"d49f280e1c9e\", \"serialNumber\": \"11Q4210002\", \"activationCode\": \"67fb110127\"}, {\"activationCode\": \"4\", \"serialNumber\": \"0000\"}, {\"mobileCode\": \"32c7f8a9ad98\", \"wsCode\": \"2546ff156fd7\", \"serialNumber\": \"11Q4210001\", \"activationCode\": \"42ebaf02da\"}], \"Count\": 5, \"ScannedCount\": 5, \"ResponseMetadata\": {\"RequestId\": \"6NS8BP60AFF361CGTNEO5MV9LBVV4KQNSO5AEMVJF66Q9ASUAAJG\", \"HTTPStatusCode\": 200, \"HTTPHeaders\": {\"x-amzn-requestid\": \"6NS8BP60AFF361CGTNEO5MV9LBVV4KQNSO5AEMVJF66Q9ASUAAJG\", \"x-amz-crc32\": \"126629989\", \"content-type\": \"application/x-amz-json-1.0\", \"content-length\": \"643\", \"date\": \"Mon, 26 Jul 2021 07:41:21 GMT\"}, \"RetryAttempts\": 0}}";
                        //{"mobileCode": "32c7f8a9ad98", "awsCode": "2546ff156fd7", "serialNumber": "11Q4210001", "activationCode": "42ebaf02da"}
                        JSONObject obj_Items = new JSONObject (result.toString());
                        JSONArray arr_Items = obj_Items.getJSONArray("Items");
                        int x = 0;
                        for(int c=0; c < arr_Items.length(); c++) {
                            JSONObject obj_Item = arr_Items.getJSONObject(c);
                            String serialNumber = obj_Item.getString("serialNumber");
                            if(!serialNumber.equals("0000")) {
                                String mobileCode = obj_Item.getString("mobileCode");//innerObj.get("mobileCode");
                                String awsCode = obj_Item.getString("awsCode");            
                                String activationCode = obj_Item.getString("activationCode");                               
                                %>
                                    <tr>
                                        <th scope="row"> <% out.print(x); %> </th>
                                        <td> <% out.print(serialNumber.toString().replace("\"", "")); %> </td>
                                        <td> <% out.print(activationCode.toString().replace("\"", "")); %> </td>
                                        <td> <% out.print(mobileCode.toString().replace("\"", "")); %> </td>
                                        <td> <% out.print(awsCode.toString().replace("\"", "")); %> </td>
                                    </tr>                
                                <%
                                x = x + 1;
                            }            
                        }        
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                %>
                </tbody>                
            </table>
        </div>
    </div>
</main>
                
                
        <%       
    }
%>
<%@ include file = "footer.jsp" %>
