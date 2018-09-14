package org.packt.spring.ch02.beanscope;

public class EmployeeServiceImpl implements EmployeeService {

	private String message;
	@Override
	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String getMessage() {
		return message;
	}

}
