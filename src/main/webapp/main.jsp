<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
    <style><%@include file="resources/css/form.css"%></style>
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

<div class="container">
    <div class="card-login">


        <form class="form-login" action="login" method="post">

            <div class="form-group">
                <caption>
                    <h2>
                        Login
                    </h2>
                </caption>
            </div>

            <div class="form-group">
                <label>Login (ваш email)</label>
                <input type="email"  class="form-control" name="email" required="required" placeholder="email">
            </div>

            <div class="form-group">
                <label>User Password</label>
                <input type="password" class="form-control" name="password" placeholder="password">
            </div>


            <div class="btn">
                <button type="submit" class="btn btn-success">Login</button>
            </div>
        </form>
    </div>


</body>
</html>
