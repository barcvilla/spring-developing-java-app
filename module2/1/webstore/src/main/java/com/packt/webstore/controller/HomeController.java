/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.packt.webstore.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author PC
 */
@Controller
public class HomeController {
    @RequestMapping("/")
    public String welcome(Model model)
    {
        /**
         * model.Attribute() es un equivalente a request.setParamter()
         * Cualquier valor que colocamos en el modelo podemos recuperarlo desde la vista (jsp) utilizando
         * la clave correspondiente con la ayuda de la expresion ${}
         */
        model.addAttribute("greeting", "Welcome to Web Store");
        model.addAttribute("tagline", "The one and only amazing web store");
        return "welcome";
    }
}
