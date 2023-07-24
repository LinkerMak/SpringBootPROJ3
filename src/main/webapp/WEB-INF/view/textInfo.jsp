<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<body>

<h2> Text Info</h2>
<br>

<table>
  <tr>
    <th>Name</th>
    <th>Email</th>
    <th>Number</th>
  </tr>

  <c:forEach var="read" items="${rTasks}">

    <tr>
      <td>${read.reader.name}</td>
      <td>${read.reader.email}</td>
      <td>${read.reader.number}</td>

      <td>
        <input type="checkbox"
               onClick="${read.flag=true}">
      </td>



    </tr>
  </c:forEach>
</table>

<c:url var="addToTask" value="addToTask">
  <c:param name="rTasks" value="${rTasks}"/>
</c:url>

<td>
  <input type="button" value="Книги"
         onClick="window.location.href = '${addToTask}'">
</td>

<br>


<html>


