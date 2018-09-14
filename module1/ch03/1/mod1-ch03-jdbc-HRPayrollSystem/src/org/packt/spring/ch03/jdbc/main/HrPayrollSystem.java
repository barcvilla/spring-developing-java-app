package org.packt.spring.ch03.jdbc.main;

import org.packt.spring.ch03.jdbc.dao.EmployeeDao;
import org.packt.spring.ch03.jdbc.dao.EmployeeDaoImpl;
import org.packt.spring.ch03.jdbc.model.Employee;

public class HrPayrollSystem {
	
	public static void main(String[] args)
	{
		EmployeeDao employeeDao = new EmployeeDaoImpl();
		
		// 1. Buscamos un empleado
		Employee employee = employeeDao.getEmployeeById(3);
		System.out.println(employee);
	}
}
