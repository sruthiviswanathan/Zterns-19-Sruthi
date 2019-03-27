package com.zilker.helloworld;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;

import com.zilker.bean.ConstructorDependency;
import com.zilker.bean.HelloWorld;

public class HelloWorldOperation {

	public static void main(String[] args) {
		 
		
		/*
		 * using ApplicationContext dependency injection using getters and setters
		 */
	ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
 
		HelloWorld service = (HelloWorld) context.getBean("helloWorld");
		String message = service.sayHello();
		System.out.println(message);
 
		
		((ClassPathXmlApplicationContext) context).close();
		
		/*
		 * Using XMLBeanFactory Dependency injection using constructor
		 */
     	 XmlBeanFactory factory = new XmlBeanFactory (new ClassPathResource("applicationContext.xml")); 
	     ConstructorDependency obj = (ConstructorDependency) factory.getBean("helloWorld1");    
	     message = obj.sayHello();
	     String message1 = obj.getHelloWorld().sayHello();
 	     System.out.println(message);
 	    System.out.println(message1);
 	     
	}
	
}
