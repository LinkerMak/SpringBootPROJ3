<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html>
<body>

<h2> Дополнительная информация о книге ${name}(${author}) </h2>

<form:form action="allBooks" modelAttribute="book" >

    <form:hidden path="id"/>

Name <form:label path="name"/>
<br><br>
Author <form:textarea path="author"/>
<br><br>
Pages <form path="pages"/>
<br><br>

    Status <form:select path="status"/>
    <br><br>

<input type="submit" value="OK">
</form:form>
<br>

<body>
<html>