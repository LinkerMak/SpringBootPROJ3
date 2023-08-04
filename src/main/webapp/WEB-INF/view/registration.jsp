<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <title>Регистрация</title>
</head>

<body>
<div>
  <form:form method="POST" modelAttribute="userForm">
    <h2>Регистрация</h2>
    <div>
      <form:input type="text" path="user.username" placeholder="Username"
                  autofocus="true"></form:input>
      <form:errors path="user.username"></form:errors>
        ${usernameError}
    </div>
    <div>
      <form:input type="password" path="user.password" placeholder="Password"></form:input>
    </div>
    <div>
      <form:input type="password" path="user.passwordConfirm"
                  placeholder="Confirm your password"></form:input>
      <form:errors path="user.password"></form:errors>
        ${passwordError}
    </div>
    <div>
    Name <form:input path="reader.name"/>
      <div/>
      <div>
    Email <form:input path="reader.email"/>
        <div/>

        <div>
    Number <form:input path="reader.number"/>
          <div/>
    <button type="submit">Зарегистрироваться</button>
  </form:form>
  <a href="/">Главная</a>
</div>
</body>
</html>
