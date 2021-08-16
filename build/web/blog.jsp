<%-- 
    Document   : index
    Created on : 17 Jul, 2021, 7:25:38 PM
    Author     : sures
--%>

<%@ include file = "header.jsp" %>

<%
    String titleB = request.getParameter("title");
//    if(titleB == null){
//        titleB = "home";
//    }
    if(titleB.equals("1")) {
        String posts[][] = {
            {"Suresh", "16-07-2021", "Title1","Content1"},
            {"Vidya", "17-07-2021", "Title2","Content2"}
        };                
        %>
            <main role="main" class="container">
                <div class="row">
                    <div class="col-md-8">
                        <% for(int x = 0; x < posts.length; x++) { %>
                        <article class="media content-section">
                          <div class="media-body">
                            <div class="article-metadata">
                                <a class="mr-2" href="#"><% out.print(posts[x][0]); %></a>
                              <small class="text-muted"><% out.print(posts[x][1]); %></small>
                            </div>
                            <h2><a class="article-title" href="#"><% out.print(posts[x][2]); %></a></h2>
                            <p class="article-content"><% out.print(posts[x][3]); %></p>
                          </div>
                        </article>
                        <% } %>
                    </div>
                    <div class="col-md-4">
                        <div class="content-section">
                            <h3>Our Sidebar</h3>
                            <p class='text-muted'>You can put any information here you'd like.
                            <ul class="list-group">
                                <li class="list-group-item list-group-item-light">Latest Posts</li>
                                <li class="list-group-item list-group-item-light">Announcements</li>
                                <li class="list-group-item list-group-item-light">Calendars</li>
                                <li class="list-group-item list-group-item-light">etc</li>
                            </ul>
                            </p>
                        </div>
                    </div>
                </div>
            </main>                
        <%                
    }
%>


<%@ include file = "footer.jsp" %>
