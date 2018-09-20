package org.packt.spring.ch03.jdbc.dao;

import java.util.List;

import org.packt.spring.ch03.jdbc.model.Employee;

public interface EmployeeDao {
	
	public int getEmployeeCount();
	public int insertEmployee(Employee employee);
	public int deleteEmployee(int empId);
	public Employee getEmployeeById(int empId);
	public void EmployeeBatchUpdate(final List<Employee> employees);
}
