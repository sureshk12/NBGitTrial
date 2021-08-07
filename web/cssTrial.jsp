<%-- 
    Document   : cssTrial
    Created on : 2 Aug, 2021, 2:37:17 PM
    Author     : sures
--%>






<%@ include file = "header.jsp" %>

<main role="main" class="container">
    <div class="row">
        <div class="col-md-8">
            <form action = "validateLogin" method = "POST">
                
                <select class="form-select" aria-label="Default select example">
                    <option selected>Open this select menu</option>
                    <option value="1">One</option>
                    <option value="2">Two</option>
                    <option value="3">Three</option>
                </select>
                
                <!--
                <div class="mb-3">
                    <label for="exampleInputEmail1" class="form-label">Email address</label>
                    <input type="email" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp" name="userEmail" >
                    <div id="emailHelp" class="form-text">We'll never share your email with anyone else.</div>
                </div>
                <div class="mb-3">
                    <label for="exampleInputPassword1" class="form-label">Password</label>
                    <input type="password" name="userPassword" class="form-control" id="exampleInputPassword1">
                </div>
                <button type="submit" class="btn btn-primary">Submit</button>
                -->
                <% 
                    String msg = request.getParameter("msg");
                    if(msg != null) {
                        %>
                            <div class="alert alert-danger" role=alert">
                                <% out.print(msg);%>
                            </div>
                        <%
                    }
                %>
            </form>
        </div>
    </div>
</main>


<%@ include file = "footer.jsp" %>
