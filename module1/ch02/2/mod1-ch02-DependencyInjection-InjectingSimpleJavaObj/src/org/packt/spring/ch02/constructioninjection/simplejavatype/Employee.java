package org.packt.spring.ch02.constructioninjection.simplejavatype;

public class Employee {
	
	private String employeeName;
	private int employeeAge;
	private boolean married;
	
	public Employee(String employeeName, int employeeAge, boolean married)
	{
		this.employeeName = employeeName;
		this.employeeAge = employeeAge;
		this.married = married;
	}
	
	@Override
	public String toString()
	{
		return "Employee Name: " + this.employeeName + " Age: " + this.employeeAge + " IsMarried: " + this.married;
	}
}
