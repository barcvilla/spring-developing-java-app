package org.packt.spring.ch06.hibernate.service;

import java.util.List;

import org.packt.spring.ch06.hibernate.model.Employee;

public interface EmployeeService {
	public List<Employee> getAllEmployees();
	public void insertEmployee(Employee employee);
	public Employee getEmployee(int id);
	public int updateEmployee(Employee employee);
	public int deleteEmployee(Employee employee);
}
