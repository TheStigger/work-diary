<%@ page import="java.util.Date" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%--
  Created by IntelliJ IDEA.
  User: maxim
  Date: 10/14/15
  Time: 6:05 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Work Diary</title>
    <jsp:include page="includes/references.jsp"/>
</head>
<body>
    <h1>Notes for <%= new SimpleDateFormat("E dd.MM.yyyy").format(new Date()) %></h1>
</body>
</html>
