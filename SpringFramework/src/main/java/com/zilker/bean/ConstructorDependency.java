package com.zilker.bean;

import org.springframework.stereotype.Service;

@Service("helloWorld1")
public class ConstructorDependency {

	private String name;
	private int age;
	HelloWorld helloWorld;
	
	public ConstructorDependency(int age,String name,HelloWorld helloWorld) {
		this.age=age;
		this.name=name;
		this.helloWorld = helloWorld;
	}
	public ConstructorDependency(HelloWorld helloWorld) {
		
		this.helloWorld = helloWorld;
	}
	
	
	public HelloWorld getHelloWorld() {
		return helloWorld;
	}

	public void setHelloWorld(HelloWorld helloWorld) {
		this.helloWorld = helloWorld;
	}


	public String sayHello() {
		return  name + " is "+ age+ " yrs old" ;
	}
	public void initIt() {
		  System.out.println("Init method after properties are set : " + name);
	}
	
    public void cleanUp() {
		  System.out.println("Spring Container is destroyed!");
	}
	
}
