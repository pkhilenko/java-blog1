<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page isELIgnored="false" %>
<c:set var="rootPath" value="${pageContext.request.contextPath}"/>

<html>
<head>
    <title>User Management Application</title>
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

        <form class="form" action="/admin/new" method="post">

            <div class="form-group">
                <caption>
                    <h2>
                        Add New User
                    </h2>
                </caption>
            </div>

            <div class="form-group">
                <label>User Name</label>
                <input type="text" class="form-control" name="name" required="required">
            </div>

            <div class="form-group">
                <label>User Email</label>
                <input type="text" class="form-control" name="email">
            </div>

            <div class="form-group">
                <label>User Password</label>
                <input type="text" class="form-control" name="password">
            </div>

            <div class="form-group">
                <label>User Country</label>
                <input type="text" class="form-control" name="country">
            </div>

            <div class="form-group">
                <label>role</label>
                <select class="form-control" name="role">
                    <option value="user">user</option>
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