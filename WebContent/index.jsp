<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
  <head>
    <title></title>
  </head>
  <body>
       <%! int i = 0;%>
       <% while (i < 5) {%>
       <h1><%=i%></h1>
       <% i++;%>
       <% }%>
  </body>
</html>