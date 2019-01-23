<%--
  Created by IntelliJ IDEA.
  User: bigyo
  Date: 2019-01-09
  Time: 10:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>hello.jsp</title>
</head>
<body>
    <h1>hello jsp</h1>
    <%
        for(int i = 0; i<100; i++){
            out.write(i+"<br>");}
    %>
</body>
</html>

<!--
http://localhost8080/hello.jsp
>