/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.packt.webstore.domain.repository.Impl;

import com.packt.webstore.domain.Product;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

/**
 *
 * @author PC
 */
public class ProductMapper implements RowMapper<Product>{
    @Override
    public Product mapRow(ResultSet rs, int rowNum) throws SQLException
    {
        Product product = new Product();
        product.setProductId(rs.getString("ID"));
        product.setName(rs.getString("NAME"));
        product.setDescription(rs.getString("DESCRIPTION"));
        product.setUnitPrice(rs.getBigDecimal("UNIT_PRICE"));
        product.setManufacturer(rs.getString("MANUFACTURER"));
        product.setCategory(rs.getString("CATEGORY"));
        product.setCondition(rs.getString("CONDITIONS"));
        product.setUnitsInStock(rs.getLong("UNITS_IN_STOCK"));
        product.setUnitsInOrder(rs.getLong("UNITS_IN_ORDER"));
        product.setDiscontinued(rs.getBoolean("DISCONTINUED"));
        
        return product;
    }
}
