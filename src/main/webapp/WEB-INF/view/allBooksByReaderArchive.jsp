<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>

<body>

<h2>
  Читатель ${arReadersBooks.reader.reader.name} брал следующие книги:
</h2>


<table>
  <tr>
    <th>Name</th>
    <th>Author</th>
    <th>pages</th>
    <th>price</th>
    <th>Date Take</th>
    <th>Date Return</th>
    <th>Date Fact Return</th>
    <th>Merge</th>

  </tr>

  <c:forEach var="entry" items="${arReadersBooks}">

    <tr>
      <td>${entry.book.name}</td>
      <td>${entry.book.author}</td>
      <td>${entry.book.pages}</td>
      <td>${entry.book.price}</td>
      <td>${entry.reader.date_take}</td>
      <td>${entry.reader.date_return}</td>
      <td>${entry.reader.date_fact_return}</td>
      <td>${entry.reader.merge}</td>


    </tr>
  </c:forEach>
</table>

<br>
<br>



<c:url var="showArchiveReaders" value="showArchiveReaders">
</c:url>


<input type="button" value="Вернуться"
       onClick="window.location.href = '${showArchiveReaders}'">

<br><br>


</body>
</html>