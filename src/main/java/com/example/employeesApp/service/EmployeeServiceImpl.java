package com.example.employeesApp.service;
import com.example.employeesApp.dto.EmployeeDTO;
import com.example.employeesApp.exception.EmployeeNotFoundException;
import com.example.employeesApp.model.Employee;
import com.example.employeesApp.repository.EmployeeRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private ModelMapper mapper;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository=employeeRepository;
    }

    @Override
    public Employee saveEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }
   @Override
   public List<Employee> getEmployees(){
       return employeeRepository.findAll();
    }
    @Override
    public  List<EmployeeDTO> getListEmployees(){
        List<EmployeeDTO> userDTOList = new ArrayList<EmployeeDTO>();
        List<Employee>employees=employeeRepository.findAll();
        for (Employee employee : employees)
        {

            ModelMapper modelMapper = new ModelMapper();
            EmployeeDTO employeeDTO = modelMapper.map(employee, EmployeeDTO.class);
            userDTOList.add(employeeDTO);
        }

        return userDTOList;

    }

    public Employee getEmployeeById(Integer id){
        return employeeRepository.findById(id).orElseThrow(
                ()->new EmployeeNotFoundException(id));
    }
    public EmployeeDTO getEmployeeDtoById(Integer id) {
        Employee employee = employeeRepository.findById(id).orElseThrow(() -> new EmployeeNotFoundException( id));
        return mapToDTO(employee);
    }

    public Employee updateEmployee(Employee newEmployee,Integer id){
        employeeRepository.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException(id));
        newEmployee.setId(id);
        return employeeRepository.save(newEmployee);

    }
    public void deleteEmployee (Integer id){
        employeeRepository.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException(id));
        employeeRepository.deleteById(id);
    }


    private EmployeeDTO mapToDTO(Employee employee){
        EmployeeDTO employeeDto = mapper.map(employee, EmployeeDTO.class);
        return employeeDto;
    }

    // convert DTO to entity
    private Employee mapToEntity(EmployeeDTO employeeDto){
        Employee employee = mapper.map(employeeDto, Employee.class);
        return employee;
    }


}
