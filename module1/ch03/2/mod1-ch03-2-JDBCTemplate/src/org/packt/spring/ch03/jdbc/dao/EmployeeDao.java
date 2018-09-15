package org.packt.spring.ch03.jdbc.dao;

import org.packt.spring.ch03.jdbc.model.Employee;

public interface EmployeeDao {
	
	public Employee getEmployeeById(int id);
}
