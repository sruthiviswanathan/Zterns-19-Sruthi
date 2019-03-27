package com.zilker.bean;
import org.springframework.stereotype.Service;


@Service("helloWorld")
public class HelloWorld {

	private String name;
	private int age;
	
	
	
	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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
