package org.packt.spring.ch06.hibernate.test;

import org.packt.spring.ch06.hibernate.model.Employee;
import org.packt.spring.ch06.hibernate.service.EmployeeService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringHibernateMain {
	
	static ApplicationContext context = new ClassPathXmlApplicationContext("/META-INF/spring/app-context.xml");
	static EmployeeService employeeService = context.getBean("employeeServiceImpl", EmployeeService.class);
	
	public static void main(String[] args)
	{	
		
		//addEmployee();
		//updateEmployee();
		deleteEmployee();
		getAllEmployees();
	}
	
	public static void addEmployee()
	{
		Employee employee = new Employee();
		employee.setFirstName("Carlos Eduardo");
		employee.setLastName("Villanueva Altuna");
		employee.setJobTitle("Software Engineer");
		employee.setDepartment("Information Technology");
		employee.setSalary(15000);
		
		employeeService.insertEmployee(employee);
	}
	
	public static void getAllEmployees()
	{
		for(Employee emp : employeeService.getAllEmployees())
		{
			System.out.println(emp.toString());
		}
	}
	
	public static void deleteEmployee()
	{
		Employee employee =  employeeService.getEmployee(5);
		int result = employeeService.deleteEmployee(employee);
		System.out.println("Se ha eliminado " + result + " Empleado");
	}
	
	public static void updateEmployee()
	{
		Employee employee = employeeService.getEmployee(1);
		employee.setFirstName("Antonio");
		employee.setLastName("Mundhuluru");
		employee.setJobTitle("IT Manager");
		employee.setDepartment("Information Technology");
		employee.setSalary(60000);
		
		int result = employeeService.updateEmployee(employee);
		System.out.println("Se ha modificado " + result + " empleado");
	}
}
