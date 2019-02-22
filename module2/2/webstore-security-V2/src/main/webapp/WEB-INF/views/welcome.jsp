<%-- 
    Document   : welcome
    Created on : 26/10/2018, 23:23:49
    Author     : PC
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <title>Welcome</title>
        <!--<link rel="stylesheet" href="css/style.css"/>-->
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.2/css/bootstrap.min.css">
    </head>
    <body>
        <div class="jumbotron">
            <h1>${greeting}</h1>
            <p>${tagline}</p>
        </div>
    </body>
</html>
