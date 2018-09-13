package org.springframework.chapter1.service;

import org.springframework.stereotype.Service;

/**
 * Marcamos la clase como @Service
 * @author PC
 *
 */
@Service
public class GreetingMessageServiceImpl implements GreetingMessageService {

	@Override
	public String greetUser() 
	{
		return "Welcome to Chapter-1 of Book Learning Spring Application Development";
	}

}
