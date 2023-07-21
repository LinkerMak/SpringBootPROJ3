<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html>
<body>
<h2> Book Info</h2>
<br>

<form:form action="saveBook" modelAttribute="book" >

    <form:hidden path="id"/>

    Name <form:input path="name"/>
    <br><br>
    Author <form:input path="author"/>
    <br><br>
    Pages <form:input path="pages"/>
    <br><br>
    Price <form:input path="price"/>
    <br><br>
    Status <form:input path="status"/>
    <br><br>

    <input type="submit" value="OK">
</form:form>

<body>
<html>


