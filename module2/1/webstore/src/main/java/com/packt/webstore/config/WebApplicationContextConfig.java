/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.packt.webstore.config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;
import org.springframework.web.util.UrlPathHelper;


/**
 * Web Application context configuration file. Clase que crea un bean (object) por cada definicion de bean mencionado en la clase.
 * @Configuration : Indica que la clase declara uno o mas @Bean method
 * @EnabledWebMvc : Adicionar esta anotacion a una clase con la anotacion @Configuration importa configuraciones spring mvc
 * @ComponentScan : Especificamos el paquete base a scannear para los componentes anotados como @Bean
 * @author PC
 */
@Configuration
@EnableWebMvc
@ComponentScan("com.packt.webstore")
public class WebApplicationContextConfig implements WebMvcConfigurer{
    
    public void ConfigureDefaultServletHandling(DefaultServletHandlerConfigurer configurer)
    {
        configurer.enable();
    }
    
    /**
     * Un ViewResolver ayuda al DispatcherServlet indentificar las vistas que tienen que ser renderizadas como una 
     * respuesta a un especifico web request. 
     * @return 
     */
    @Bean
    public InternalResourceViewResolver getInternalResourceViewResolver()
    {
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setViewClass(JstlView.class);
        resolver.setPrefix("/WEB-INF/views/");
        resolver.setSuffix(".jsp");
        
        return resolver;
    }

    @Override
    public void configurePathMatch(PathMatchConfigurer configurer) {
        UrlPathHelper urlPathHelper = new UrlPathHelper();
        urlPathHelper.setRemoveSemicolonContent(false);
        
        configurer.setUrlPathHelper(urlPathHelper);
    }
    
    @Bean
    public MessageSource messageSource()
    {
        ResourceBundleMessageSource resource = new ResourceBundleMessageSource();
        resource.setBasename("messages");
        return resource;
    }
}
