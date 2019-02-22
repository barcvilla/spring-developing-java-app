/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.packt.webstore.exception;

/**
 * 
 * @author PC
 */
public class ProductNotFoundException extends RuntimeException {
    private static final long serialVersionUID = -694354952032299587L;
    private String productId;
    
    public ProductNotFoundException(String productId)
    {
        this.productId = productId;
    }
    
    public String getProductId()
    {
        return productId;
    }
}
