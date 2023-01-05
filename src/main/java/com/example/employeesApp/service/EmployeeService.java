package com.example.employeesApp.service;

import com.example.employeesApp.dto.EmployeeDTO;
import com.example.employeesApp.model.Employee;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface EmployeeService {
    public Employee saveEmployee(Employee employee);
    public List<Employee> getEmployees();
    public List<EmployeeDTO> getListEmployees();

    public Employee getEmployeeById(@PathVariable Integer id );
    public EmployeeDTO getEmployeeDtoById(@PathVariable Integer id);

    public Employee updateEmployee(Employee employee, @PathVariable Integer id);

    public String deleteEmployee(@PathVariable Integer id);
}
