package org.packt.spring.ch02.setterinjection.app;

import org.packt.spring.ch02.setterinjection.Employee;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class PayrollSystem {
	public static void main(String[] args)
	{
		ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
		Employee employee = (Employee) context.getBean("employee");
		
		System.out.println("========Lists==============");
		System.out.println(employee.getLists());
		
		System.out.println("==========Set==============");
		System.out.println(employee.getSets());
		
		System.out.println("===========Map=============");
		System.out.println(employee.getMaps());
	}
}
