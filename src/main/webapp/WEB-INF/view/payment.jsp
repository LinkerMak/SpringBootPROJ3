<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: improver2
  Date: 19.07.2023
  Time: 14:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <h2> Оплатите задолженность размером: ${sum} </h2>
</head>
<body>

<c:url var="showBooksReaderById" value="showBooksReaderById">
  <c:param name="readerId" value="${readerId}"/>
</c:url>

<input type="button" value="Оплатить"
       onclick="window.location.href= 'showBooksReaderById'"/>
<br><br>
</body>
</html>
