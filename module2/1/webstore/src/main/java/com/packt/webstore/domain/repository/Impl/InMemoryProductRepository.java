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
import javax.swing.tree.RowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 *
 * @author PC
 */
@Repository
public class InMemoryProductRepository implements ProductRepository{
    
    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;
    
    @Override
    public List<Product> getAllProducts()
    {
        Map<String, Object> params = new HashMap<String, Object>();
        List<Product> result = jdbcTemplate.query("select * from products", params, new ProductMapper());
        
        return result;
    }
}
