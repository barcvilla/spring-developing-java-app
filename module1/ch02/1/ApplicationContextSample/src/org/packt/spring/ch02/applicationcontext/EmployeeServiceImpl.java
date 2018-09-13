package org.packt.spring.ch02.applicationcontext;

public class EmployeeServiceImpl implements EmployeeService {

	@Override
	public Long generateEmployeeId() {
		return System.currentTimeMillis();
	}

}
