package com.example.employeesApp.model;


import java.util.List;
import java.util.Set;

public class EmployeeRest {
    private Integer id;
    private String name;
    private String address;
    private String email;
    private Set<AddressRest> employeeAddresses;
}
