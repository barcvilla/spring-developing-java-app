/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.packt.webstore.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Creamos un RuntimeException que indica que ningun producto fue encontrado en una categoria
 * @ResponseStatus indica a Spring MVC retornar un especifico HTTP status si esta excepcion ha sido lanzado desde un 
 * request mapping.
 * HttpStatus.NOT_FOUND indicamos que HTTP status necesitamos retornar 
 * @author PC
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "No products found under this category")
public class NoProductsFoundUnderCategoryException extends RuntimeException{
    private static final long serialVersionUID = 3935230281455340039L;
}
