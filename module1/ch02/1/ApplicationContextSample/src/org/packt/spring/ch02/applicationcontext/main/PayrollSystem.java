package org.packt.spring.ch02.applicationcontext.main;

import org.packt.spring.ch02.applicationcontext.EmployeeService;
import org.packt.spring.ch02.applicationcontext.EmployeeServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class PayrollSystem {
	
	public static void main(String[] args)
	{
		ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
		EmployeeService employeeService = (EmployeeServiceImpl)context.getBean("empServiceBean");
		
		System.out.println("Unique Employee Id: " + employeeService.generateEmployeeId());
	}
}
