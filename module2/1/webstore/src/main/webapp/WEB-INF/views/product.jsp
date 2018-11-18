<%-- 
    Document   : product
    Created on : 02/11/2018, 21:30:31
    Author     : PC
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <!--<link rel="stylesheet" href="css/style.css"/>-->
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.2/css/bootstrap.min.css">
        <title>Product</title>
    </head>
    <body>
        <section>
            <div class="jumbotron">
                <div class="container">
                    <h1>Product</h1>
                </div>
            </div>
        </section>
        <section class="container">
            <div class="row">
                <div class="col-md-5">
                    <h3>${product.name}</h3>
                    <p>${product.description}</p>
                    <p>
                        <strong>Item Code: </strong><span class="label warning">${product.productId}</span>
                    </p>
                    <p>
                        <strong>manufacturer</strong>: ${product.manufacturer}
                    </p>
                    <p>
                        <strong>category</strong>: ${product.category}
                    </p>
                    <p>
                        <strong>Available units in stock</strong>: ${product.unitsInStock}
                    </p>
                    <h4>${product.unitPrice} USD</h4>
                    <p>
                        <a href="<spring:url value="/market/products"/>"
                           class="btn btn-default">
                            <span class="glyphicon-hand-left glyphicon"></span> Back  
                        </a>
                    
                        <a href="#" class="btn btn-warning btn-large">
                            <span class="glphycon-shopping-cart glyphicon"> </span> Order Now
                        </a>
                    </p>
                </div>
            </div>
        </section>
    </body>
</html>
