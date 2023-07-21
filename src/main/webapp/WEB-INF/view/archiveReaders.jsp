<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>

<body>

<h2>
  Archive Readers
</h2>


<table>
  <tr>
    <th>Name</th>
    <th>Email</th>
    <th>Number</th>
  </tr>

  <c:forEach var="reader" items="${arReaders}">

    <c:url var="showBooksByIdReaderArchive" value="showBooksByIdReaderArchive">
      <c:param name="reader" value="${reader}"/>
    </c:url>

    <tr>
      <td>${reader.name}</td>
      <td>${reader.email}</td>
      <td>${reader.number}</td>

      <td>
        <input type="button" value="Взятые книги"
               onClick="window.location.href = '${showBooksByIdReaderArchive}'">
      </td>

    </tr>
  </c:forEach>
</table>

<br>
<br>
<input type="button" value="Вернуться"
       onclick="window.location.href= 'allBooks'"/>
<br><br>


</body>
</html>