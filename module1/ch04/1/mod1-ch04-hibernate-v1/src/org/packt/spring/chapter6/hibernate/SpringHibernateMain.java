package org.packt.spring.chapter6.hibernate;

import org.packt.spring.chapter6.hibernate.model.Employee;
import org.packt.spring.chapter6.hibernate.service.EmployeeService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringHibernateMain {
	
	public static void main(String[] args)
	{
		ApplicationContext context = new ClassPathXmlApplicationContext("/META-INF/spring/app-context.xml");
		EmployeeService employeeService = context.getBean("employeeServiceImpl", EmployeeService.class);
		
		for(Employee emp : employeeService.getAllEmployees())
		{
			System.out.println(emp.toString());
		}
	}
}