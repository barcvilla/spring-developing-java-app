<%-- 
    Document   : products
    Created on : 27/10/2018, 20:03:46
    Author     : PC
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <!--<link rel="stylesheet" href="css/style.css"/>-->
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.2/css/bootstrap.min.css">
        <title>Products</title>
    </head>
    <body>
        <section>
            <div class="jumbotron">
                <div class="container">
                    <h1>Products</h1>
                    <p>All the available products in our store</p>
                </div>
            </div>
        </section>

        <section class="container">
            <div class="row">
                <c:forEach var="product" items="${products}">
                    <div class="col-sm-6 col-md-3">
                        <div class="thumbnail">
                            <div class="caption">
                                <h3>${product.name}</h3>
                                <p>${product.description}</p>
                                <p>${product.unitPrice}</p>
                                <p>Available ${product.unitsInStock} units in stock</p>
                                <p>
                                    <a 
                                        href="<spring:url value="/market/product?id=${product.productId}" />"
                                        class="btn btn-primary"
                                        <span class="glyphicon-info-sign glyphicon" ></span> Details
                                    </a>
                                </p>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </section>
    </body>
</html>
