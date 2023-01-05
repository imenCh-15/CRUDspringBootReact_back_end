package com.example.employeesApp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

//point d'entrée de l'application est la classe qui contient l annotation @SpringBootApplication
@SpringBootApplication
public class EmployeesApplication {

	public static void main(String[] args) {
		ApplicationContext apc= SpringApplication.run(EmployeesApplication.class, args);
		//beans names
		/*for(String s:apc.getBeanDefinitionNames())
			System.out.println(s);*/
	}

}
