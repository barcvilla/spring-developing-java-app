/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.packt.webstore.domain.repository.Impl;

import com.packt.webstore.domain.Product;
import com.packt.webstore.domain.repository.ProductRepository;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 * Clase bean administrada por Spring la cual se encarga de todas las operaciones CRUD
 * Clase a la que se le delaga la responsabilidad de recuperar datos relacionados al producto desde la BD
 * @author PC
 */
@Repository
public class ProductRepositoryImpl implements ProductRepository{
    
    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;
    
    /**
     * Retornamos una lista de productos
     * @return 
     */
    @Override
    public List<Product> getAllProducts()
    {
        Map<String, Object> params = new HashMap<String, Object>();
        List<Product> result = jdbcTemplate.query("select * from products", params, new ProductMapper());
        
        return result;
    }
    
    /**
     * Metodo que actualiza un solo producto su stock
     * @param productId id del producto
     * @param noOfUnits numero de unidades
     */
    @Override
    public void updateStock(String productId, long noOfUnits)
    {
        String sql = "UPDATE products SET UNITS_IN_STOCK = :units_in_stock WHERE ID= :id";
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("units_in_stock", noOfUnits);
        params.put("id", productId);
        jdbcTemplate.update(sql, params);
    }
}
