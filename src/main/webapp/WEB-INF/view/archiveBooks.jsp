<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>

<body>

<h2>
    Archive Books
</h2>


<table>
    <tr>
        <th>Name</th>
        <th>Author</th>
        <th>pages</th>
        <th>price</th>
    </tr>

    <c:forEach var="book" items="${arBooks}">

        <tr>
            <td>${book.name}</td>
            <td>${book.author}</td>
            <td>${book.pages}</td>
            <td>${book.price}</td>

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