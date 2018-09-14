package org.packt.spring.ch02.callbacks;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

public class EmployeeServiceImpl implements EmployeeService, InitializingBean, DisposableBean {

	@Override
	public Long generateEmployeeID() {
		
		return System.currentTimeMillis();
	}
	
	/**
	 * Init method en el archivo de configuracion xml
	 */
	public void myInit()
	{
		System.out.println("Employee myInit...");
	}
	
	/**
	 * Callback de Inicializacion
	 * Este metodo es llamado por Sprig cuando el bean EmployeeServiceImpl es inicializado y todas las propiedades establecidas
	 */
	@Override
	public void afterPropertiesSet() throws Exception {
		System.out.println("Employee afterPropertiesSet...");
	}

	/**
	 * Callback de destruccion
	 */
	@Override
	public void destroy() throws Exception {
		System.out.println("Employee destroy...");
	}
	
}
