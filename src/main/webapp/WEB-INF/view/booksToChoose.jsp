<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>

<body>

<h2>
  All Books
</h2>


<table>
  <tr>
    <th>Name</th>
    <th>Author</th>
    <th>pages</th>
    <th>price</th>
    <th>status</th>
  </tr>

  <c:forEach var="book" items="${books}">

    <c:url var="chooseBook" value="chooseBook">
      <c:param name="bookId" value="${book.id}"/>
      <c:param name="readerId" value="${readerId}"/>
    </c:url>

    <tr>
      <td>${book.name}</td>
      <td>${book.author}</td>
      <td>${book.pages}</td>
      <td>${book.price}</td>
      <td>${book.status}</td>

      <td>
        <input type="button" value="Выбрать"
               onClick="window.location.href = '${chooseBook}'">
      </td>

    </tr>
  </c:forEach>
</table>

</body>
</html>