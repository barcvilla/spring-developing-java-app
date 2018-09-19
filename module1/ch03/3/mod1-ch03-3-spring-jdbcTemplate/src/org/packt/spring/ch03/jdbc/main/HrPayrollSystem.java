package org.packt.spring.ch03.jdbc.main;

import org.packt.spring.ch03.jdbc.dao.EmployeeDao;
import org.packt.spring.ch03.jdbc.model.Employee;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class HrPayrollSystem {
	
	private static void insertEmployee(EmployeeDao employeeDao)
	{
		Employee emp =  new Employee("Jonathan", "Lara", "Rodriguez", "j.lara@gmail.com", "123654789");
		int reg = employeeDao.insertEmployee(emp);
		System.out.println("Se ha registrado '" + reg + "' persona en la BD");
	}
	
	public static void main(String[] args)
	{
		@SuppressWarnings("resources")
		ApplicationContext context = new ClassPathXmlApplicationContext("Spring.xml");
		EmployeeDao employeeDao = (EmployeeDao)context.getBean("employeeDaoImpl", EmployeeDao.class);
		
		//insertEmployee(employeeDao);
		
		System.out.println("Existen '" + employeeDao.getEmployeeCount() + "' personas en la BD");
	}
}
