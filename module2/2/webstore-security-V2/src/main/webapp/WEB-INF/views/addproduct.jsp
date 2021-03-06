<%-- 
    Document   : addProduct
    Created on : 03/11/2018, 19:03:25
    Author     : PC
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.2/css/bootstrap.min.css">
        <title>Add Product</title>
    </head>
    <body>
        <section>
            <div class="pull-right" style="padding-right: 50px">
                <a href="?language=en">English</a>|<a href="?language=nl">Dutch</a>
            </div>
        </section>
        <section>
            <div class="jumbotron">
                <div class="container">
                    <h1>Products</h1>
                    <p>Add Products</p>
                </div>
            </div>
        </section>
        <section class="container">
            <!--enlazamos el objeto newProduct adjuntado en el objeto Model con el formulario. Este formulario en
                Spring se llama From Backing Bean
                enctype="multipart/form-data" permite aceptar upload image-->
            <form:form method="POST" modelAttribute="newProduct" class="form-horizontal" enctype="multipart/form-data">
                <fieldset>
                    <legend>Add New Product</legend>

                    <!--Enlazamos el valor de cada input text y otros elementos al correspondiente campo
                        del backing form mediante la propiedad path-->
                    <div class="form-group">
                        <label class="control-label col-lg-2 col-lg-2" for="productId">
                            <!--Accedemos a texto externo. Atributo de tipo key donde Spring en runtime obtiene el valor
                                del archivo messages.properties-->
                            <spring:message code="addProduct.form.productId.label"/>
                        </label>
                        <div class="col-lg-10">
                            <form:input id="productId" path="productId" type="text" class="form:input-large"/>
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="control-label col-lg-2 col-lg-2" for="name">Name: </label>
                        <div class="col-lg-10">
                            <form:input id="name" path="name" type="text" class="form:input-large"/>
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="control-label col-lg-2 col-lg-2" for="unitPrice">Unit price: </label>
                        <div class="col-lg-10">
                            <form:input id="unitPrice" path="unitPrice" type="text" class="form:input-large"/>
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="control-label col-lg-2 col-lg-2" for="manufacturer">Manufacturer: </label>
                        <div class="col-lg-10">
                            <form:input id="manufacturer" path="manufacturer" type="text" class="form:input-large"/>
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="control-label col-lg-2 col-lg-2" for="category">Category: </label>
                        <div class="col-lg-10">
                            <form:input id="category" path="category" type="text" class="form:input-large"/>
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="control-label col-lg-2 col-lg-2" for="unitsInStock">Units in Stock: </label>
                        <div class="col-lg-10">
                            <form:input id="unitsInStock" path="unitsInStock" type="text" class="form:input-large"/>
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="control-label col-lg-2" for="description">Description: </label>
                        <div class="col-lg-10">
                            <form:textarea id="description" path="description" rows="2" />
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="control-label col-lg-2" for="condition">Condition: </label>
                        <div class="col-lg-10">
                            <form:radiobutton path="condition" value="New" />New
                            <form:radiobutton path="condition" value="Old" />Old
                            <form:radiobutton path="condition" value="Refurbished" />Refurbished
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="control-label col-lg-2" for="productImage">
                            <spring:message code="addProduct.form.productImage.label" />
                        </label>
                        <div class="col-lg-10">
                            <form:input id="productImage" path="productImage" type="file" class="form:input-large" />
                        </div>
                    </div>

                    <!--Al hacer click en el boton Add se produce un POST request y se ejecutara el metodo processAddNewProduct
                        ya que este metodo es un post request y dentro de este metodo se llama al metodo add-->
                    <div class="form-group">
                        <div class="col-lg-offset-2 col-lg-10">
                            <input type="submit" id="btnAdd" class="btn btn-primary" value ="Add"/>
                        </div>
                    </div>
                </fieldset>
            </form:form>
        </section>
    </body>
</html>
