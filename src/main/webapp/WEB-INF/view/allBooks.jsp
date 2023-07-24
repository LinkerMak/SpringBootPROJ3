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

    <c:forEach var="book" items="${allBooks}">

        <c:url var="updateButton" value="updateBook">
            <c:param name="bookId" value="${book.id}"/>
        </c:url>

        <c:url var="deleteButton" value="deleteBook">
            <c:param name="bookId" value="${book.id}"/>
        </c:url>

        <c:url var="shoInformationButton" value="showInformationBookById">
            <c:param name="bookId" value="${book.id}"/>
        </c:url>

        <tr>
            <td>${book.name}</td>
            <td>${book.author}</td>
            <td>${book.pages}</td>
            <td>${book.price}</td>
            <td>${book.status}</td>

            <td>
                <input type="button" value="Update"
                       onClick="window.location.href = '${updateButton}'">
            </td>
            <td>
                <input type="button" value="Delete"
                       onClick="window.location.href = '${deleteButton}'">
            </td>

            <td>
                <input type="button" value="Information"
                       onClick="window.location.href = '${shoInformationButton}'">
            </td>
        </tr>
    </c:forEach>
</table>

<br>
<br>
<input type="button" value="Добавить"
       onclick="window.location.href= 'addNewBook'"/>
<br><br>

<input type="button" value="Читатели"
       onclick="window.location.href= 'readers'"/>
<br><br>

<input type="button" value="Архив книг"
       onclick="window.location.href= 'showArchiveBooks'"/>

<input type="button" value="Архив читателей"
       onclick="window.location.href= 'showArchiveReaders'"/>
<br><br>

<input type="button" value="Выгрузка отчета"
       onclick="window.location.href= 'showTextInfo'"/>
<br><br>
</body>
</html>