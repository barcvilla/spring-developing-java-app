/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.packt.webstore.interceptor;

import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * Sabemos que los interceptores son una forma para adicionar logica que necesitamos ejecutar durante cada request
 * en nuestra aplicacion web. La mejor parte sobre Interceptors es que, no solo pueden tener una logica que se
 * ejecute antes y/o despues de la request, nosotros podemos baypass o redireccionar la web request orginal misma.
 * Vimos algunos ejemplos de uso de interceptors como, registrar un log de eventos y la intercionalizacion de la app.
 * Pero el problema con estos dos ejemplos es que no hay una forma apropiada de detenerlos.
 * 
 * Por ejemplo, podemos tener un web request especifico que no necesita ejecutar una logica adicional en nuestro
 * interceptor. Como podemos decirle a Spring MVC que selecitvamente incluya o excluya interceptors? Los Mapped
 * Interceptors nos ayudan para esa tarea. Utilizando Mapped Interceptors podemos selectivamente incluir o
 * excluir interceptors, basados en un URL mapped. 
 * 
 * Consideremos el siguiente caso, queremos mostrar una pagina especial de productos ofertados solo a aquellos
 * usuarios que posean un codigo valido de promocion, pero los otros que intentan acceder a esta oferta especial
 * de productos con un codigo de promocion invalido deberian ser direccionados a una pagina de error. Para este ejemplo
 * usaremos Mapped Interceptors
 * 
 * La clase PromoCodeInterceptor es similar a la clase ProcessingTimeLogInterceptor, la unica diferencia es que se ha
 * extendido HandlerInterceptorAdapter y sobreescrito el metodo preHandle
 * @author PC
 */
public class PromoCodeInterceptor extends HandlerInterceptorAdapter {
    /**
     * usado para configurar el codigo valido de promocion, en este caso sera OFF3R, asi cualquiera que acceda al
     * a la pagina de oferta de productos debera ingresa el codigo OFF3R como codigo valido
    */
    private String promoCode;
    /**
     * Estos dos atributos son usados para la redireccion, errorRedirect indica el URL mapping redireccionado en caso de
     * un codigo de promocion invalido y offerRedirect indica el URL mapping redireccionado para un codigo de promocion
     * valido
     */
    private String errorRedirect;
    private String offerRedirect;   
    
    /**
     * Verificamos si el request contiene el codigo de promocion correcto como parametro HTTP
     * si es asi, redirigimos el request a la pagina configurada como specialoffert, en otro caso
     * a la pagina configurada de error
     */
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException
    {
        String givenPromoCode = request.getParameter("promo");
        if(promoCode.equals(givenPromoCode))
        {
            response.sendRedirect(request.getContextPath() + "/" + offerRedirect);
        }
        else
        {
            response.sendRedirect(errorRedirect);
        }
        
        return false;
    }
    
    public void setPromoCode(String promoCode)
    {
        this.promoCode = promoCode;
    }
    
    public void setErrorRedirect(String errorRedirect)
    {
        this.errorRedirect = errorRedirect;
    }
    
    public void setOfferRedirect(String offerRedirect)
    {
        this.offerRedirect = offerRedirect;
    }
    
}
