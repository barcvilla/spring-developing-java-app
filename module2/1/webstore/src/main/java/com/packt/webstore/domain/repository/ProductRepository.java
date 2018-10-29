/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.packt.webstore.domain.repository;

import com.packt.webstore.domain.Product;
import java.util.List;

/**
 * Persitence Layer. ProductController sera aquel que utilizara esta capa de persistencia, pero no es un buena practica
 * conectar la capa de persistencia directamente con la capa de control por esta razon tenemos una interface como 
 * referencia en el controlador
 * @author PC
 */
public interface ProductRepository {
    List<Product> getAllProducts();
    void updateStock(String productId, long noOfUnits);
}
