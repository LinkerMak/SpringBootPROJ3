<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html>

<body>

<h2>
    All Readers
</h2>


<table>
    <tr>
        <th>Name</th>
        <th>Email</th>
        <th>Number</th>
    </tr>

    <c:forEach var="reader" items="${allReaders}">

        <c:url var="updateButton" value="updateReader">
            <c:param name="readerId" value="${reader.id}"/>
        </c:url>

        <c:url var="deleteButton" value="deleteReader">
            <c:param name="readerId" value="${reader.id}"/>
        </c:url>

        <c:url var="shoInformationButton" value="showInformationReaderById">
            <c:param name="readerId" value="${reader.id}"/>
        </c:url>

        <c:url var="showBooksByIdReader" value="showBooksReaderById">
            <c:param name="readerId" value="${reader.id}"/>
        </c:url>

        <tr>
            <td>${reader.name}</td>
            <td>${reader.email}</td>
            <td>${reader.number}</td>

            <td>
                <input type="button" value="Update"
                       onClick="window.location.href = '${updateButton}'">
            </td>
            <td>
                <input type="button" value="Delete"
                       onClick="window.location.href = '${deleteButton}'">
            </td>

            <td>
                <input type="button" value="DI"
                       onClick="window.location.href = '${shoInformationButton}'">
            </td>

            <td>
                <input type="button" value="Книги"
                       onClick="window.location.href = '${showBooksByIdReader}'">
            </td>



        </tr>
    </c:forEach>
</table>

<br>
<br>
<input type="button" value="Добавить"
       onclick="window.location.href= 'addNewReader'"/>
<br><br>

<input type="button" value="Книги"
       onclick="window.location.href= 'allBooks'"/>
<br><br>

</body>
</html>