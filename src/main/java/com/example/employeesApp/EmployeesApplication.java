package com.example.employeesApp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;

//point d'entrée de l'application est la classe qui contient l annotation @SpringBootApplication
@SpringBootApplication
//tell spring ioc to scan components even out of the main package
@ComponentScan(basePackages = "com.*")
public class EmployeesApplication {
	public int add(int a, int b) {
		return a + b;
	}
	public  String throwException(int a)throws Exception{
		if(a<0){
			throw new Exception("Value should be greater than or equal to 0");
		}
		return "Value is greater than or equal to 0";
	}
public void checkTimeOut() throws InterruptedException{
	System.out.println("I am going to sleep");
	Thread.sleep(2000);
	System.out.println("Sleeping over");
}
	public Object checkNull(Object obj){

		if(obj!=null){
			return obj;

		}
		return null;
	}
	public static void main(String[] args) {
		ApplicationContext apc= SpringApplication.run(EmployeesApplication.class, args);
		//beans names
		/*for(String s:apc.getBeanDefinitionNames())
			System.out.println(s);*/
	}

}
