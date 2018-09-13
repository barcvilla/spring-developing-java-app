package org.springframework.chapter1.main;

import org.springframework.chapter1.service.GreetingMessageService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MainClass 
{
	public static void main(String[] args)
	{
		//ApplicationContext carga spring bean configuration file (beans.xml) y se encarga de inicializar los objetos bean
		ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
		//recuperamos el Spring bean desde el context a traves del metodo getBean()
		//el metodo getBean() utiliza el Bean Id el Bean Class para retornar un objeto bean
		GreetingMessageService greetingMessageService = context.getBean("greetingMessageServiceImpl", GreetingMessageService.class);
		
		System.out.println(greetingMessageService.greetUser());
	}
}
