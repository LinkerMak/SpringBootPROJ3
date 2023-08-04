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
    <th> Date Take</th>
    <th> Date Return</th>
    <th>Merge</th>
  </tr>

  <c:forEach var="buf" items="${forms}">

    <c:url var="returnBook" value="/returnBook">
      <c:param name="bookId" value="${buf.book.id}"/>
      <c:param name="readerId" value="${reader.id}"/>
    </c:url>

    <tr>
      <td>${buf.book.name}</td>
      <td>${buf.book.author}</td>
      <td>${buf.book.pages}</td>
      <td>${buf.book.price}</td>
      <td>${buf.form.date_take}</td>
      <td>${buf.form.date_return}</td>
      <td>${buf.form.merge}</td>

      <td>
        <input type="button" value="Вернуть"
               onClick="window.location.href = '${returnBook}'">
      </td>
    </tr>
  </c:forEach>
</table>

<br>
<br>



<c:url var="addNewBookForReader" value="addNewBookForReader">
  <c:param name="readerId" value="${reader.id}"/>
</c:url>


<input type="button" value="Выбрать книгу"
       onClick="window.location.href = '${addNewBookForReader}'">

<br><br>


</body>
</html>