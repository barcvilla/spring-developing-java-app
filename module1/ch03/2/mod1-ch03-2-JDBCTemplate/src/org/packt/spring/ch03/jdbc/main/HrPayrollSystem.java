package org.packt.spring.ch03.jdbc.main;

import org.packt.spring.ch03.jdbc.dao.EmployeeDao;
import org.packt.spring.ch03.jdbc.dao.EmployeeDaoImpl;
import org.packt.spring.ch03.jdbc.model.Employee;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class HrPayrollSystem 
{
	public static void main(String[] args)
	{
		@SuppressWarnings("resource")
		ApplicationContext context = new ClassPathXmlApplicationContext("Spring.xml");
		
		EmployeeDao employeeDao = (EmployeeDao) context.getBean("employeeDaoImpl", EmployeeDao.class);
		Employee employee = employeeDao.getEmployeeById(3);
		
		System.out.println(employee);
	}
}
