<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<body>

<h2> Readers</h2>
<br>

<table>
  <tr>
    <th>Name</th>
    <th>Email</th>
    <th>Number</th>
  </tr>

  <c:forEach var="read" items="${rTasks}">

    <c:url var="addToTask" value="taskInfo">
      <c:param name="readerId" value="${read.reader.id}"/>
    </c:url>

    <tr>
      <td>${read.reader.name}</td>
      <td>${read.reader.email}</td>
      <td>${read.reader.number}</td>



      <td>
        <input type="button" value="Выгрузить отчет"
               onClick="window.location.href = '${addToTask}'">
      </td>


    </tr>
  </c:forEach>
</table>


<br>


<input type="button" value="Назад"
       onclick="window.location.href= 'allBooks'"/>
<br><br>

<br>


<html>


