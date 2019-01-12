/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.packt.webstore.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * Interceptor son usados para interceptar la web request actual antes y despues de ser procesadas. Las clases
 * interceptor deben implementar la inteface HandlerInterceptor el cual define 3 importantes metodos:
 * preHandle: este metodo sera llamado antes que la web request encuentre el controller para su ejecucion.
 * postHandle: este metodo sera llamado despues de la ejecucion del metodo en el controller
 * afterCompletion: este metodo sera llamado luego que se haya completado el ciclo entero del web request
 * @author PC
 */
public class ProcessingTimeLogInterceptor implements HandlerInterceptor{
    private static final Logger LOGGER = Logger.getLogger(ProcessingTimeLogInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        long startTime = System.currentTimeMillis();
        request.setAttribute("startTime", startTime);
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        String queryString = request.getQueryString() == null ? "" : "?" + request.getQueryString();
        String path = request.getRequestURI() + queryString;
        
        long startTime = (Long)request.getAttribute("startTime");
        long endTime = System.currentTimeMillis();
        
        LOGGER.info(String.format("%s millisecond take to process the request %$. ", (endTime - startTime), path));
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        
    }
    
    
}
