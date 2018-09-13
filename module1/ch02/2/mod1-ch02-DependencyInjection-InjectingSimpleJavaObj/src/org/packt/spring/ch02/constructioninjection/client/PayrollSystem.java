package org.packt.spring.ch02.constructioninjection.client;

import org.packt.spring.ch02.constructioninjection.simplejavatype.Employee;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class PayrollSystem {
	
	public static void main(String[] args)
	{
		ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
		Employee employee = (Employee) context.getBean("employee");
		System.out.println(employee.toString());
	}
}
