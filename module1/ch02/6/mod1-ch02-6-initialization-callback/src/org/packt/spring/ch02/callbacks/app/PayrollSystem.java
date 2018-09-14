package org.packt.spring.ch02.callbacks.app;

import org.packt.spring.ch02.callbacks.EmployeeService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class PayrollSystem {
	public static void main(String[] args)
	{
		ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
		EmployeeService employeeService = (EmployeeService) context.getBean("employeeServiceBean");
		
		System.out.println(employeeService.generateEmployeeID());
		context.close();
	}
}
