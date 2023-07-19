<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<body>

<h2> Reader Info</h2>
<br>

<form:form action="saveReader" modelAttribute="reader" >

  <form:hidden path="id"/>

Name <form:input path="name"/>
<br><br>
Email <form:input path="email"/>
<br><br>
Number <form:input path="number"/>
<br><br>

<input type="submit" value="OK">
</form:form>

<body>
<html>


