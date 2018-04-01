<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: avtsu
  Date: 25.03.2018
  Time: 18:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Registration</title>
    <link href="webjars/bootstrap/3.3.6/css/bootstrap.min.css"
          rel="stylesheet">
</head>
<body>
<nav role="navigation" class="navbar navbar-default">
    <div class="">
        <a href="https://www.facebook.com/avtsuran" class="navbar-brand">LARDI</a>
    </div>
    <div class="navbar-collapse">
        <ul class="nav navbar-nav ">
            <li class="active"><a href="/management.org/">Home</a></li>
        </ul>
        <ul class="nav navbar-nav navbar-right">
            <li><a href="/login">Log in</a></li>
        </ul>
    </div>
</nav>
<div class="container"  style="display: flex; justify-content: center;">
    <div class="col-md-4">
        <form:form method="post" modelAttribute="user" action="/registration">
            <form:hidden path="id" />
            <fieldset class="form-group">
                <form:label path="firstName">First name</form:label>
                <form:input path="firstName" type="text" class="form-control"/>
                <form:errors path="firstName" cssClass="text-warning" />
            </fieldset>
            <fieldset class="form-group">
                <form:label path="secondName">Second name</form:label>
                <form:input path="secondName" type="text" class="form-control"/>
                <form:errors path="secondName" cssClass="text-warning" />
            </fieldset>
            <fieldset class="form-group">
                <form:label path="middleName">Middle name</form:label>
                <form:input path="middleName" type="text" class="form-control"/>
                <form:errors path="middleName" cssClass="text-warning" />
            </fieldset>
            <fieldset class="form-group">
                <form:label path="">Login</form:label>
                <form:input path="login" type="text" class="form-control"/>
                <form:errors path="login" cssClass="text-warning" />
            </fieldset>
            <fieldset class="form-group">
                <form:label path="password">Password</form:label>
                <form:input path="password" type="password" class="form-control"/>
                <form:errors path="password" cssClass="text-warning" />
            </fieldset>
            <button type="submit" class="btn btn-success">Submit</button>
            <a type="button" class="btn btn-default" href="/login">I have account!</a>
        </form:form>
    </div>
</div>
</body>
</html>
