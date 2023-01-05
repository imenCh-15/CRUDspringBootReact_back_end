package com.example.employeesApp.exception;

public class EmployeeNotFoundException extends RuntimeException{
    public EmployeeNotFoundException(Integer id){
        super("Could not found the employee with id : "+id);
    }
}
