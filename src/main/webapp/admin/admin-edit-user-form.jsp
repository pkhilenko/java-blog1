<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page isELIgnored="false" %>
<c:set var="rootPath" value="${pageContext.request.contextPath}"/>

<html>
<head>
    <title>existingUser Management Application</title>
    <style>
        <%@include file="../resources/css/form.css"%>
    </style>
</head>

<body>
<header>
    <nav class="navbar">
        <ul class="navbar-nav">
            <li><a href="${rootPath}/list" class="navbar-link">Users</a></li>
        </ul>
        <ul class="navbar-nav">
            <li><a href="${rootPath}/logout" class="navbar-link">Logout</a></li>
        </ul>
    </nav>
</header>
<br>
<div class="container">
    <div class="card">

        <form class="form" action="/admin/edit" method="post">

        <div class="form-group">
            <caption>
                <h2>
                    <c:if test="${existingUser != null}">
                        Edit existingUser
                    </c:if>
                </h2>
            </caption>
        </div>

        <c:if test="${existingUser != null}">
            <input type="hidden" name="id" value="${existingUser.id}"/>
        </c:if>


        <div class="form-group">
            <label>existingUser Name</label>
            <input type="text" value="${existingUser.name}" class="form-control" name="name" required="required">
        </div>

        <div class="form-group">
            <label>existingUser Email</label>
            <input type="text" value="${existingUser.email}" class="form-control" name="email">
        </div>

        <div class="form-group">
            <label>existingUser Password</label>
            <input type="text" value="${existingUser.password}" class="form-control" name="password">
        </div>

        <div class="form-group">
            <label>existingUser Country</label>
            <input type="text" value="${existingUser.country}" class="form-control" name="country">
        </div>

        <div class="form-group">
            <label>role</label>
            <select class="form-control" name="role">
                <option value="${existingUser.role}">${existingUser.role}</option>
                <option value="existingUser">existingUser</option>
                <option value="admin">admin</option>
            </select>
        </div>

        <div class="btn">
            <button type="submit" class="btn btn-success">Save</button>
        </div>
    </form>
</div>
</body>

</html>