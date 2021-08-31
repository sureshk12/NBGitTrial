<%-- 
    Document   : createNewData
    Created on : 2 Aug, 2021, 4:22:54 PM
    Author     : sures
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="org.onida.audio.DatabaseHelper"%>
<%@ include file = "header.jsp" %>


<%
    if(logStatus.equals("Suresh")) {
        %>

<main role="main" class="container">
    <div class="row">
        <div class="col-md-8">
            <%
            String err = "";
            String senerio = request.getParameter("stage");
            DatabaseHelper db1 = new DatabaseHelper();           
            ArrayList<String> compArray = db1.getCompany();
            DatabaseHelper db2 = new DatabaseHelper(); 
            ArrayList<String> prodArray = db2.getProduct("ALL");
            
            switch(senerio) {
                case "1":
                    break;
                case "2":
                    if(compArray.isEmpty()) {
                        err = "No Company resgistered, pls register Company and Product";
                    }
                    break;
                case "3" :
                    if(compArray.isEmpty()) {
                        err = "No Company resgistered, pls register Company and Product"; 
                    } else if (prodArray.isEmpty()) {
                        err = "No Product resgistered, pls register Product";
                    }
                    break;
                default:
                    err = "ERROR";
            }
            
               
            if(!err.equals("")) {
            %>
                <div class="mx-l-5" style="width: 800px;">
                    <p><a href="#" class="text-danger"><%= err %></a></p>
                </div>
                <form name="Error" action="create.jsp" method="POST">
                    <input type="hidden" value="<%= err %>" name="createMsg"/>
                    <input type="submit" value="OK" name="ok" />
                </form>    
            <%                    
            } else {
            String compStr = request.getParameter("company");
            String prodStr = request.getParameter("product");
   

            %>
            <form action = "createNewDataHelperServlet" method = "POST">
            <%
            switch(senerio) {
            case "1":
            %>
                <div class="mx-auto" style="width: 200px;">
                    <h6>Enter Company Details</h6>
                </div>
                <div class="mb-3">
                    <label for="exampleInputEmail1" class="form-label">Company Name</label>
                    <input type="text" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp" name="company_name"> 
                </div>
                <div class="mb-3">
                    <label for="exampleInputEmail1" class="form-label">Company Code</label>
                    <input type="text" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp" name="company_code" style="text-transform:lowercase" maxlength="2" > 
                </div>
                <div class="mb-3">
                    <label for="exampleInputEmail1" class="form-label">Representative</label>
                    <input type="text" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp" name="company_person"> 
                </div>
                <div class="mb-3">
                    <label for="exampleInputEmail1" class="form-label">Email</label>
                    <input type="text" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp" name="company_email"> 
                </div>
                <div class="mb-3">
                    <label for="exampleInputEmail1" class="form-label">Phone</label>
                    <input type="text" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp" name="company_phone"> 
                </div>
                <div class="mb-3">
                    <label for="exampleInputEmail1" class="form-label">Address</label>
                    <input type="text" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp" name="company_address"> 
                </div>
            <%
                break;
            case "2":
            %>
                <div class="mx-auto" style="width: 200px;">
                    <h6>Enter Product Details</h6>
                </div>                    
                <div class="mb-3">
                    <label for="exampleInputEmail1" class="form-label">Product Name</label>
                    <input type="text" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp" name="product_name" style="text-transform:lowercase" maxlength="2"> 
                </div>
                <div class="mb-3">
                    <label for="exampleInputEmail1" class="form-label">Product Code(2 Characters Upper Case)</label>
                    <input type="text" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp" name="product_code"> 
                </div>
                <div class="mb-3">
                    <label for="exampleInputEmail1" class="form-label">Description</label>
                    <input type="text" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp" name="product_description"> 
                </div>         
            <%
                break;
            case "3":
            %>
                <div class="mx-auto" style="width: 200px;">
                    <h6>Enter Model Details</h6>
                </div> 
                <select class="form-select" aria-label="Default select example" name="dropDown1" >
                    <% 
                        
                        for(int c = 0; c < (compArray.size()); c++) {
                            %>
                            <option><%= compArray.get(c) %></option>
                            <%
                        }
                    %>
                </select>
                <select class="form-select" aria-label="Default select example" name="dropDown2" >
                    <% 
                        for(int c = 0; c < (prodArray.size()); c++) {
                            %>
                            <option><%= prodArray.get(c) %></option>
                            <%
                        }
                    %>
                </select>
            
                <div class="mb-3">
                    <label for="exampleInputEmail1" class="form-label">Model Name</label>
                    <input type="text" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp" name="model_name" >
                </div>           
            <%
                break;
            default:
                out.println("ERROR");
                break;
            }
            %>
                <input type="hidden" name ="stage" value="<%= senerio %>" />
                <input type="hidden" name ="compStr" value="<%= compStr %>" />
                <input type="hidden" name ="prodStr" value="<%= prodStr %>" />
                <button type="submit" class="btn btn-primary">Submit</button>
            </form>
        </div>
    </div>
</main>
<%
    }
%>

        <%       
    }
%>

<%@ include file = "footer.jsp" %>
<!--
                            <input type="hidden" name ="stage" value="1" />
                            <button type="submit" class="btn btn-primary">Submit</button>



                            <input type="hidden" name ="stage" value="2" />
                            <input type="hidden" name ="company" value="compStr" />
                            <button type="submit" class="btn btn-primary">Submit</button>
                        </form>
                    </div>
                </div>
            </main> 
            
            

                        </form>
                    </div>
                </div>
            </main>

-->