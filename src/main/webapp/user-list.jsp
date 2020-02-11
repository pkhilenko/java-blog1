<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<c:set var="rootPath" value="${pageContext.request.contextPath}"/>

<html>
<head>
    <title>User Management Application</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
</head>

<body>
<header>
    <nav class="navbar navbar-expand-md navbar-dark" style="background-color: tomato">
        <ul class="navbar-nav">
            <li><a href="${rootPath}/list" class="nav-link">Users</a></li>
        </ul>
    </nav>
</header>
<br>

<div class="row">
    <div class="container">
        <h3 class="text-center">List of Users</h3>
        <hr>
        <div class="container text-left">
            <a href="${rootPath}/new" class="btn btn-success">Add New User</a>
        </div>
        <br>
        <table class="table table-bordered">
            <thead>
            <tr>
                <th>№</th>
                <th>Name</th>
                <th>Email</th>
                <th>Country</th>
                <th colspan="2">Actions</th>
            </tr>
            </thead>

            <tbody>
            <c:forEach var="user" items="${listUser}">
                <c:set var="count" value="${count+1}"/>
                <tr>
                    <td>${count}</td>
                    <td>${user.name}</td>
                    <td>${user.email}</td>
                    <td>${user.country}</td>
                    <td>
                        <a href="edit?id=${user.id}" class="btn btn-sm btn-info">Edit</a>
                    </td>
                    <td>
                        <form action="delete" method="post">
                            <input type="hidden" name="id" value="${user.id}" />
                            <input type="submit" value="Delete" class="btn btn-sm btn-danger" />
                        </form>

                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>
</body>
</html>