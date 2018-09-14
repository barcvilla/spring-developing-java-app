package org.packt.spring.ch02.beanscope.app;

import org.packt.spring.ch02.beanscope.EmployeeService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class PayrollSystem {
	
	public static void main(String[] args)
	{
		ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
		EmployeeService employeeServiceA = (EmployeeService)context.getBean("employeeServiceBean");
		
		employeeServiceA.setMessage("Message by service A");
		System.out.println("employeeServiceA: " + employeeServiceA.getMessage());
		
		//Lo recuperamos nuevamente
		EmployeeService employeeServiceB = (EmployeeService)context.getBean("employeeServiceBean");
		System.out.println("employeeServiceB: " + employeeServiceB.getMessage());
	}
}
