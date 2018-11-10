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
    
    @Override
    public List<Product> getProductsByCategory(String category)
    {
        String sql = "SELECT * FROM products WHERE CATEGORY = :category";
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("category", category);
        
        return jdbcTemplate.query(sql, params, new ProductMapper());
    }
    
    @Override
    public List<Product> getProductsByFilter(Map<String, List<String>> filterParams)
    {
        String sql = "SELECT * FROM products WHERE CATEGORY IN ( :categories) and MANUFACTURER IN ( :brands)";
        return jdbcTemplate.query(sql, filterParams, new ProductMapper());
    }
    
    @Override
    public Product getProductById(String productId)
    {
         String sql = "SELECT * FROM products WHERE ID = :productId";
         Map<String, Object> params = new HashMap<String, Object>();
         params.put("productId", productId);
         return jdbcTemplate.queryForObject(sql, params, new ProductMapper());
    }
    
    @Override
    public void addProduct(Product product)
    {
        String sql = "INSERT INTO PRODUCTS "
                + "(ID, "
                + "NAME, "
                + "DESCRIPTION, "
                + "UNIT_PRICE, "
                + "MANUFACTURER, "
                + "CATEGORY, "
                + "CONDITIONS, "
                + "UNITS_IN_STOCK, "
                + "UNITS_IN_ORDER, "
                + "DISCONTINUED) "
                + "VALUES ("
                + ":id, :name, :desc, :price, :manufacturer, :category, :condition, :inStock, :inOrder, :discontinued)";
        
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("id", product.getProductId());
        params.put("name", product.getName());
        params.put("desc", product.getDescription());
        params.put("price", product.getUnitPrice());
        params.put("manufacturer", product.getManufacturer());
        params.put("category", product.getCategory());
        params.put("condition", product.getCondition());
        params.put("inStock", product.getUnitsInStock());
        params.put("inOrder", product.getUnitsInOrder());
        params.put("discontinued", product.isDiscontinued());
        
        jdbcTemplate.update(sql, params);
    }
}
