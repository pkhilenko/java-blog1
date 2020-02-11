<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page isELIgnored="false" %>
<c:set var="rootPath" value="${pageContext.request.contextPath}"/>

<html>
<head>
    <title>User Management Application</title>
    <style>
        <%@include file="resources/css/form.css"%>
    </style>
</head>

<body>
<header>
    <nav class="navbar">
        <ul class="navbar-nav">
            <li><a href="${rootPath}/list" class="nav-link">users</a></li>
        </ul>
    </nav>
</header>
<br>
<div class="container">
    <div class="card">
        <c:if test="${user != null}">
        <form class="form" action="update" method="post">
            </c:if>
            <c:if test="${user == null}">
            <form class="form" action="create" method="post">
                </c:if>
                <div class="form-group">
                    <caption>
                        <h2>
                            <c:if test="${user != null}">
                                Edit User
                            </c:if>
                            <c:if test="${user == null}">
                                Add New User
                            </c:if>
                        </h2>
                    </caption>
                </div>

                <c:if test="${user != null}">
                    <input type="hidden" name="id" value="${user.id}"/>
                </c:if>


                <div class="form-group">
                    <label>User Name</label>
                    <input type="text" value="${user.name}" class="form-control" name="name" required="required">
                </div>

                <div class="form-group">
                    <label>User Email</label>
                    <input type="text" value="${user.email}" class="form-control" name="email">
                </div>

                <div class="form-group">
                    <label>User Country</label>
                    <input type="text" value="${user.country}" class="form-control" name="country">
                </div>

                <div class="btn">
                    <button type="submit" class="btn btn-success">Save</button>
                </div>
            </form>
    </div>
</body>

</html>