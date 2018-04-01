<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: avtsu
  Date: 25.03.2018
  Time: 19:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Contact book</title>
    <link href="webjars/bootstrap/3.3.6/css/bootstrap.min.css"
          rel="stylesheet">
</head>
<body>

<nav class="navbar navbar-default" role="navigation">
    <!-- Brand and toggle get grouped for better mobile display -->
    <div class="navbar-header">
        <a class="navbar-brand" href="#">LARDI</a>
    </div>

    <!-- Collect the nav links, forms, and other content for toggling -->
    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
        <ul class="nav navbar-nav">
            <li class="active"><a href="#">Home</a></li>
            <li class=""><a href="/contacts">Phone book</a></li>
        </ul>
        <ul class="nav navbar-nav navbar-right">
            <li><a href="/logout">Logout</a></li>
        </ul>
    </div>
</nav>

<div class="container"  style="display: flex; justify-content: center;">
    <div class="col-md-6">
    <form:form method="post" modelAttribute="contact">
        <form:hidden path="id" />
        <fieldset class="form-group ">
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
            <form:errors path="middleName" cssClass="text-warning"/>
        </fieldset>
        <fieldset class="form-group">
            <form:label path="phone">Phone</form:label>
            <form:input path="phone" type="text" class="form-control"/>
            <form:errors path="phone" cssClass="text-warning" />
        </fieldset>
        <fieldset class="form-group">
            <form:label path="email">Email</form:label>
            <form:input path="email" type="text" class="form-control"/>
            <form:errors path="email" cssClass="text-warning" />
        </fieldset>
        <fieldset class="form-group">
            <form:label path="address">Address</form:label>
            <form:input path="address" type="text" class="form-control"/>
            <form:errors path="address" cssClass="text-warning" />
        </fieldset>
        <fieldset class="form-group">
            <form:label path="homePhone">Home phone</form:label>
            <form:input path="homePhone" type="text" class="form-control"/>
            <form:errors path="homePhone" cssClass="text-warning" />
        </fieldset>
        <button type="submit" class="btn btn-success">Submit</button>
    </form:form>
    </div>
</div>
</body>
</html>
