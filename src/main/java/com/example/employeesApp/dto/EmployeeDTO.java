package com.example.employeesApp.dto;

import lombok.Data;

import java.util.List;

@Data
public class EmployeeDTO {
    private Integer employee_id;
    private String name;
    private String email;
    private List<AddressDTO> employeeAddresses;
}
