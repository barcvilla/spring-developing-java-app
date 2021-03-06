/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.packt.webstore.service.Impl;

import com.packt.webstore.domain.Product;
import com.packt.webstore.domain.repository.ProductRepository;
import com.packt.webstore.service.ProductService;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 
 * @author PC
 */
@Service
public class ProductServiceImpl implements ProductService{
    //Con el objeto productRepository llevamos a cabo todas las operaciones relacionadas al acceso a datos
    @Autowired
    private ProductRepository productRepository;
    
    @Override
   public List<Product> getAllProducts() {
         return productRepository.getAllProducts();
   }
    
    @Override
    public void updateAllStock() {
        List<Product> allProducts = productRepository.getAllProducts();
        for(Product product : allProducts)
        {
            if(product.getUnitsInStock() < 500)
            {
                productRepository.updateStock(product.getProductId(), product.getUnitsInStock() + 1000);
            }
        }
    }
    
    @Override
    public List<Product> getProductsByCategory(String category)
    {
        return productRepository.getProductsByCategory(category);
    }
    
    @Override
    public List<Product> getProductsByFilter(Map<String, List<String>> filterParams)
    {
        return productRepository.getProductsByFilter(filterParams);
    }
    
    @Override
    public Product getProductById(String productId)
    {
        return productRepository.getProductById(productId);
    }
    
    @Override
    public void addProduct(Product product)
    {
        productRepository.addProduct(product);
    }
}
