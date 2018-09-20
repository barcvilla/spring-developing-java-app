package org.packt.spring.ch03.jdbc.main;

import java.util.ArrayList;
import java.util.List;

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
	
	public static void eliminarEmployee(EmployeeDao employeeDao, int empId) {
		int reg = employeeDao.deleteEmployee(empId);
		System.out.println("Se elimino '"+ reg +"' persona");
	}
	
	public static void ObtenerEmployeeById(EmployeeDao employeeDao, int empId)
	{
		Employee emp = employeeDao.getEmployeeById(empId);
		System.out.println("Se recupero el empleado\n" + emp.toString());
	}
	
	public static void employeeBatchUpdate(EmployeeDao employeeDao)
	{
		List<Employee> employeeList = new ArrayList<Employee>();
		Employee emp1 = new Employee("Fernando","Martinez","Velez","f.martines@gmail.com","789654123");
		Employee emp2 = new Employee("Pedro","Gutierrez","Alex","p.guitierrez@gmail.com","789654123");
		Employee emp3 = new Employee("Maria","Puig","Tour","m.puig@gmail.com","789654123");
		Employee emp4 = new Employee("Ana","Fernandez","Luccotti","a.luccotti@gmail.com","789654123");
		
		employeeList.add(emp1);
		employeeList.add(emp2);
		employeeList.add(emp3);
		employeeList.add(emp4);
		
		employeeDao.EmployeeBatchUpdate(employeeList);
		System.out.println("Se han insertado '" + employeeList.size() + "' personas");
	}
	
	public static void main(String[] args)
	{
		@SuppressWarnings("resources")
		ApplicationContext context = new ClassPathXmlApplicationContext("Spring.xml");
		EmployeeDao employeeDao = (EmployeeDao)context.getBean("employeeDaoImpl", EmployeeDao.class);
		
		//insertEmployee(employeeDao);
		//eliminarEmployee(employeeDao, 12);
		//ObtenerEmployeeById(employeeDao, 3);
		System.out.println("Existen '" + employeeDao.getEmployeeCount() + "' personas en la BD");
		employeeBatchUpdate(employeeDao);
		System.out.println("Existen '" + employeeDao.getEmployeeCount() + "' personas en la BD");
	}
}
