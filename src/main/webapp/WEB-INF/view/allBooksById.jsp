<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>

<body>

<h2>
  У читателя ${reader.name} имеются следующие книги:
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

    <c:url var="returnBook" value="returnBook">
      <c:param name="bookId" value="${book.id}"/>
      <c:param name="readerId" value="${reader.id}"/>
    </c:url>

    <tr>
      <td>${book.name}</td>
      <td>${book.author}</td>
      <td>${book.pages}</td>
      <td>${book.price}</td>
      <td>${book.status}</td>

      <td>
      <input type="button" value="Вернуть"
             onclick="window.location.href= 'returnBook'"/>
      </td>
    </tr>
  </c:forEach>
</table>

<br>
<br>



<c:url var="addNewBookForReader" value="addNewBookForReader">
  <c:param name="readerId" value="${reader.id}"/>
</c:url>


<input type="button" value="Книги"
       onClick="window.location.href = '${addNewBookForReader}'">

<br><br>


</body>
</html>