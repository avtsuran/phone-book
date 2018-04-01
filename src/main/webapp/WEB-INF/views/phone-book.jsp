<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: avtsu
  Date: 25.03.2018
  Time: 19:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Contact book</title>


    <link href="webjars/bootstrap/3.3.6/css/bootstrap.min.css"
          rel="stylesheet">
    <link type="text/css" href="css/dataTables.bootstrap4.min.css" rel="stylesheet">

    <script  type="text/javascript" src="js/jquery-1.12.4.js" ></script>
    <script  type="text/javascript" src="js/jquery.dataTables.min.js"></script>
    <script  type="text/javascript" src="js/dataTables.bootstrap4.min.js"></script>

   </head>
   <body>

   <nav class="navbar navbar-default" role="navigation">
    <div class="navbar-header">
        <a class="navbar-brand " href="#">LARDI</a>
    </div>

    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
        <ul class="nav navbar-nav">
            <li class="active"><a href="#">Home</a></li>
            <li><a href="/add-contact" >Add contact</a></li>
        </ul>
        <ul class="nav navbar-nav navbar-right">
            <li><a href="/logout">Logout</a></li>
        </ul>
    </div>
</nav>

<div class="container">
    <table id="example" class="table table-striped table-bordered" style="width:100%" >
        <thead>
        <tr>
            <td>Full name</td>
            <td>Phone number</td>
            <td>Home phone</td>
            <td>Email</td>
            <td>Address</td>
            <td>Action</td>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${contacts}" var="contact">
            <tr>
                <td>${contact.secondName} ${contact.firstName} ${contact.middleName}</td>
                <td>${contact.phone}</td>
                <td>${contact.homePhone}</td>
                <td>${contact.email}</td>
                <td>${contact.address}</td>
                <td style="text-align: center !important;">
                    <a type="button" class="btn btn-sm btn-info" href="/update-contact?id=${contact.id}">Edit</a>
                    <a type="button" class="btn btn-sm btn-warning" href="/delete-contact?&id=${contact.id}">Delete</a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
<script>

    $(document).ready(function() {
        $('#example').DataTable();
    } );
</script>
</body>
</html>
