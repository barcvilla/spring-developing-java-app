/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.packt.webstore.controller;
import com.packt.webstore.domain.repository.Impl.ProductRepositoryImpl;
import com.packt.webstore.domain.repository.ProductRepository;
import com.packt.webstore.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Clase ProductContoller anotada como controller que indica a Spring crear y administrar este objeto como un spring bean
 * @author PC
 */
@Controller
public class ProductController {
    /**
     * Hacemos una referencia al ProductRepository.
     * Mediante @Autowired Spring asigna internamente una referencia de la clase ProductRepositoryImpl el cual es un bean
     * administrado en el spring container (web application context)
     * al objeto productRepository
     */
    //@Autowired
    //private ProductRepository productRepository; // referencia para acceder a los objetos del dominio Product
    
    @Autowired
    private ProductService productService;
    
    @RequestMapping("/products")
    public String list(Model model)
    {
        model.addAttribute("products", productService.getAllProducts());
        
        return "products";
    }
    
    @RequestMapping("/update/stock")
    public String updateStock(Model model)
    {
        productService.updateAllStock();
        return "redirect:/products";
    }
}
