<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page isELIgnored="false" %>
<html>

<head>
    <title>User Management Application</title>
    <style>
        html, body, * {
            padding: 0;
            margin: 0;
            box-sizing: border-box;

        }

        .container {
            margin-top: -19px;
            padding-top: 100px;
            width: 1000px;
            margin-right: auto;
            margin-left: auto;
            background-color: honeydew;
            min-height: 100vh;
        }

        header {
            box-sizing: border-box;
            padding: 10px;
            background-color: tomato;
            font-size: 18px;
        }

        .navbar {
            display: flex;
            padding: 15px;
            justify-content: left;
        }

        ul {
            list-style: none;
        }

        .nav-link {
            color: rgba(255, 255, 255, .5);
        }

        a {
            color: transparent;
            text-decoration: none;
        }


        .card {
            width: 800px;
            height: 460px;
            margin-left: auto;
            margin-right: auto;
            box-shadow: 5px 5px 6px 0px black;
            background-color: azure;
        }

        .form {
            margin: auto;
            display: flex;
            flex-direction: column;
            justify-content: space-between;
            height: 400px;
            width: 600px;
            padding: 15px;
        }

        .form-group {
            padding: 15px;
            display: flex;
            flex-direction: column;
            justify-content: space-between;
        }

        input.form-control {
            width: 100%;
            padding: 8px;
        }

        .btn.btn-success {
            margin: 15px;
            padding: 8px;
            background-color: cornflowerblue;
            border-radius: 3px;
            color: white;
        }

        .btn.btn-success:hover {
            background-color: dodgerblue;
        }
    </style>
    <link rel="stylesheet" type="text/css" href="resources/form.css">
</head>

<body>
<header>
    <nav class="navbar">
        <ul class="navbar-nav">
            <li><a href="<%=request.getContextPath()%>/list" class="nav-link">users</a></li>
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
                <input type="hidden" name="id" value="<c:out value='${user.id}' />"/>
            </c:if>


            <div class="form-group">
                <label>User Name</label>
                <input type="text" value="<c:out value='${user.name}' />"
                                                class="form-control" name="name" required="required">
            </div>

            <div class="form-group">
                <label>User Email</label>
                <input type="text" value="<c:out value='${user.email}' />"
                                                 class="form-control" name="email">
            </div>

            <div class="form-group">
                <label>User Country</label>
                <input type="text" value="<c:out value='${user.country}' />"
                                                   class="form-control" name="country">
            </div>

            <div class="btn">
                <button type="submit" class="btn btn-success">Save</button>
                <div class="form-group">
        </form>
    </div>
</div>
</body>

</html>