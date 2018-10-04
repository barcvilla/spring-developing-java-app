package org.packt.spring.ch06.hibernate.service;

import java.util.List;

import org.packt.spring.ch06.hibernate.dao.EmployeeDao;
import org.packt.spring.ch06.hibernate.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeServiceImpl implements EmployeeService{
	
	@Autowired
	private EmployeeDao employeeDao;

	public List<Employee> getAllEmployees() {
		List<Employee> employees = employeeDao.getAllEmployees();
		return employees;
	}

	public void insertEmployee(Employee employee) {
		employeeDao.insertEmployee(employee);
	}

	public int updateEmployee(Employee employee) {
		int result = employeeDao.updateEmployee(employee);
		return result;
	}

	public int deleteEmployee(Employee employee) {
		int result = employeeDao.deleteEmployee(employee);
		return result;
	}

	public Employee getEmployee(int id) {
		return employeeDao.getEmployee(id);
	}

}
