package com.example.employeesApp.dto;

import lombok.Data;

import java.util.List;

@Data
public class EmployeeDTO {
    private Integer id;
    private String name;
    private String address;
    private String email;
    private List<AddressDTO> employeeAddresses;
}
