<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html>
<body>
<h2> Введите даты</h2>
<br>

<form:form action="addToTask" modelAttribute="task" >

    <form:hidden path="id_reader"/>
    <form:hidden path="status"/>

Date From <form:input path="dateFrom"/>
<form:errors path="dateFrom"/>
<br><br>
Date To <form:input path="dateTo"/>
    <form:errors path="dateTo"/>
<br><br>

<input type="submit" value="OK">
</form:form>

<body>
<html>

