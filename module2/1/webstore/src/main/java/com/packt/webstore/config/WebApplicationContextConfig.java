/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.packt.webstore.config;

import com.packt.webstore.domain.Product;
import java.util.ArrayList;
import javax.faces.application.ResourceHandler;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.web.accept.ContentNegotiationManager;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.multipart.support.MultipartFilter;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.ContentNegotiatingViewResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;
import org.springframework.web.servlet.view.xml.MarshallingView;
import org.springframework.web.util.UrlPathHelper;

/**
 * Web Application context configuration file. Clase que crea un bean (object)
 * por cada definicion de bean mencionado en la clase.
 *
 * @Configuration : Indica que la clase declara uno o mas @Bean method
 * @EnabledWebMvc : Adicionar esta anotacion a una clase con la anotacion
 * @Configuration importa configuraciones spring mvc
 * @ComponentScan : Especificamos el paquete base a scannear para los
 * componentes anotados como @Bean
 * @author PC
 */
@Configuration
@EnableWebMvc
@ComponentScan("com.packt.webstore")
public class WebApplicationContextConfig implements WebMvcConfigurer {

    public void ConfigureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

    /**
     * Un ViewResolver ayuda al DispatcherServlet indentificar las vistas que
     * tienen que ser renderizadas como una respuesta a un especifico web
     * request.
     *
     * @return
     */
    @Bean
    public InternalResourceViewResolver getInternalResourceViewResolver() {
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

    /**
     * Configuracion para upload imagenes al server mediante Multipart
     * request.Un Multipart request es un tipo de HTTP request que se utiliza
     * para enviar archivos y datos al servidor.
     *
     * @return
     */
    @Bean
    public CommonsMultipartResolver multipartResolver() {
        CommonsMultipartResolver resolver = new CommonsMultipartResolver();
        resolver.setDefaultEncoding("utf-8");
        resolver.setMaxUploadSize(20000000);
        resolver.setResolveLazily(false);
        return resolver;
    }

    @Bean
    public MultipartFilter multipartFilter() {

        MultipartFilter multipartFilter = new MultipartFilter();
        multipartFilter.setMultipartResolverBeanName("multipartResolver");
        return multipartFilter;
    }
    
    // Representacion del objeto de dominio en formato json
    @Bean
    public MappingJackson2JsonView jsonView()
    {
        MappingJackson2JsonView jsonView = new MappingJackson2JsonView();
        jsonView.setPrettyPrint(true);
        return jsonView;
    }
    
    // Representacion del objeto de dominio en formato xml
    @Bean
    public MarshallingView xmlView()
    {
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        marshaller.setClassesToBeBound(Product.class);
        MarshallingView xmlView = new MarshallingView(marshaller);
        return xmlView;
    }
    
    /**
     * XML y JSON son formatos populares para el intercambio de datos ampliamente utilizados en la comunicacion de 
     * servicios web. Utilizando ContentNegotiatingViewResolver podemos incorporar muchas View como
     * MappingJackson2JsonView para JSON y MarshallingView para XML para representar la misma informacion del producto en
     * xml y json
     * @param manager
     * @return 
     */
    @Bean
    public ViewResolver contentNegotiatingViewResolver(ContentNegotiationManager manager)
    {
        ContentNegotiatingViewResolver resolver = new ContentNegotiatingViewResolver();
        resolver.setContentNegotiationManager(manager);
        ArrayList<View> views = new ArrayList<>();
        views.add(jsonView());
        views.add(xmlView());
        resolver.setDefaultViews(views);
        return resolver;
    }

    /**
     * Declarando recursos estaticos (images) addResourceLocations define la
     * ubicacion del directorio base de los recursos estaticos que deseamos
     * proveer. En nuestro caso queremos que todas las imagenes se encuentren
     * disponibles bajo la ruta src/main/webapp/resources/images/
     * addResourceHandler si viene un request conla ruta /img esta sera mapeado
     * hacia resources/images
     *
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //Le decimos a Spring donde los archivos de imagenes se encuentran ubicados
        registry.addResourceHandler("/img/**").addResourceLocations("/resources/images/");
    }

    @Bean
    public MessageSource messageSource() {
        ResourceBundleMessageSource resource = new ResourceBundleMessageSource();
        resource.setBasename("messages");
        return resource;
    }
}
