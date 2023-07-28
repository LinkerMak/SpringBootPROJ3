<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<!DOCTYPE HTML>
<html>
<head>
  <title>Главная</title>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
</head>
<body>
<div>
  <h3>${pageContext.request.userPrincipal.name}</h3>

  <sec:authorize access="!isAuthenticated()">
    <h4><a href="/login">Войти</a></h4>
    <h4><a href="/registration">Зарегистрироваться</a></h4>
  </sec:authorize>
  <sec:authorize access="isAuthenticated()">
    <h4><a href="/logout">Выйти</a></h4>
<%--
    <h3><sec:authentication property=""/></h3>
--%>


    <c:url var="showBooksByIdReader" value="showBooksReaderByNameUser">
      <c:param name="nameUser" value="${pageContext.request.userPrincipal.name.toString()}"/>
    </c:url>


    <security:authorize access="hasAnyRole('USER')">
    <td>
      <input type="button" value="Свои книги"
             onClick="window.location.href = '${showBooksByIdReader}'">
    </td>
    </security:authorize>

    <security:authorize access="hasAnyRole('ADMIN')">
      <input type="button" value="Все книги"
             onclick="window.location.href= 'allBooks'"/>
      <br><br>
    </security:authorize>

  </sec:authorize>
  <h4><a href="/news">Новости (только пользователь)</a></h4>
  <h4><a href="/admin">Пользователи (только админ)</a></h4>
</div>
</body>
</html>