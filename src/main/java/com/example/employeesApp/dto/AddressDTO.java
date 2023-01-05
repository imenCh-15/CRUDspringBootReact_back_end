package com.example.employeesApp.dto;

import lombok.Data;

@Data
public class AddressDTO {
    private  Integer id;
    private String city;
    private String street;
    private String zip_code;
    private  String type;
    private EmployeeDTO employee;
}
