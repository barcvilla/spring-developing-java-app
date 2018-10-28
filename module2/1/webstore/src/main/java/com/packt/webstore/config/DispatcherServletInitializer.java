/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.packt.webstore.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/**
 * DispatchServlet es aquel que examina la HTTP request entrante e invoca al metodo del controlador correspondiente. En nuestro
 * caso, el metodo welcome de la clase HomeController necesita ser invocado si nosotros hacemos un http request ingresando 
 * la url htt://localhost:8080/webstore
 * @author PC
 */
public class DispatcherServletInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[]{RootApplicationContextConfig.class};
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[]{WebApplicationContextConfig.class};
    }
    
    /**
     * Metodo para controlar cada request entrante
     * @return 
     */
    @Override
    protected String[] getServletMappings() {
        /**
         * Cuando retornamos un array que solo contiene "/" este indica al DispatcherServlet como servlet default de la app
         * Asi que cada request entrante sera controlada por el DispatcherServlet.
         */
        return new String[] {"/"};
    }
    
}
