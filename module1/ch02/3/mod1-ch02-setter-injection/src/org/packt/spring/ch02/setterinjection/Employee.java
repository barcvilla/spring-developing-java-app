package org.packt.spring.ch02.setterinjection;

public class Employee {
	private String employeeName;
	
	public void setEmployeeName(String employeeName)
	{
		this.employeeName = employeeName;
	}
	
	@Override
	public String toString()
	{
		return "Employe Name: " + this.employeeName;
	}
}
