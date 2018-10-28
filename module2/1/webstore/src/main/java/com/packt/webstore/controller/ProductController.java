/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.packt.webstore.controller;

import com.packt.webstore.domain.Product;
import com.packt.webstore.domain.repository.ProductRepository;
import java.math.BigDecimal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author PC
 */
@Controller
public class ProductController {
    
    @Autowired
    private ProductRepository productRepository;
    
    @RequestMapping("/products")
    public String list(Model model)
    {
        /**
        Product iPhone = new Product("P1234", "iPhone 6s", new BigDecimal(500));
        iPhone.setDescription("Apple iPhone 6s smartphone with 4.00-inch 640x1136 display and 8-megapixel rear camera");
        iPhone.setCategory("Smartphone");
        iPhone.setManufacturer("Apple");
        iPhone.setUnitsInStock(1000);
        */
        model.addAttribute("products", productRepository.getAllProducts());
        
        return "products";
    }
}
