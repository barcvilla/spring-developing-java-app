package org.packt.spring.ch06.hibernate.dao;

import java.util.List;

import org.packt.spring.ch06.hibernate.model.Employee;


public interface EmployeeDao {
	public List<Employee> getAllEmployees();
	public void insertEmployee(Employee employee);
	public Employee getEmployee(int id);
	public int updateEmployee(Employee employee);
	public int deleteEmployee(Employee employee);
}
