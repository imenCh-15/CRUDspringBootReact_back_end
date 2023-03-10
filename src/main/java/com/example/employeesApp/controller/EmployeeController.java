package com.example.employeesApp.controller;
import com.example.employeesApp.dto.EmployeeDTO;
import com.example.employeesApp.model.Employee;
import com.example.employeesApp.service.AddressService;
import com.example.employeesApp.service.EmployeeService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.modelmapper.TypeToken;



import java.util.ArrayList;
import java.util.List;
@CrossOrigin
//this annotation to make this class to serve rest endpoints
@RestController
@RequestMapping("/employee")

public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private AddressService addressService;
    @PostMapping("/addEmployee")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Object> newEmployee(@RequestBody Employee employee){

                return new ResponseEntity<>(employeeService.saveEmployee(employee), HttpStatus.CREATED);
    }
    @PutMapping("/updateEmployee/{id}")
    public ResponseEntity<Object> updateEmployee(@RequestBody Employee employee,@PathVariable Integer id){
        if (employee.getName().isEmpty()||employee.getEmail().isEmpty()) {
            return new ResponseEntity<>("A missing name or email.", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(employeeService.updateEmployee(employee,id), HttpStatus.OK);

    }

    @GetMapping("/employees")
    public List<Employee> employeesList(){
        return employeeService.getEmployees();

    }

    @GetMapping("/employeesDTO")
    public List<Employee> employeesDTOList(){
        List<Employee> employeeList= new ArrayList<Employee>();
        List<EmployeeDTO> employeeDTOList = employeeService.getListEmployees();
        if (employeeDTOList != null && !employeeDTOList.isEmpty())
        {
            java.lang.reflect.Type userRestListType = new TypeToken<List<Employee>>(){}.getType();
            ModelMapper modelMapper = new ModelMapper();
            employeeList = modelMapper.map(employeeDTOList, userRestListType);
        }

        return employeeList;


    }





    @GetMapping("/getEmployee/{id}")
    public Employee employeeById(@PathVariable Integer id){
        return employeeService.getEmployeeById(id);
    }
    @GetMapping("/employeeDto/{id}")
    public ResponseEntity<EmployeeDTO> employeeDtoById(@PathVariable Integer id){
        EmployeeDTO employeeDto= employeeService.getEmployeeDtoById(id);
        return new ResponseEntity<>(employeeDto, HttpStatus.OK);
    }


    @DeleteMapping("/delete/{id}")
    public void deleteEmployee(@PathVariable Integer id){

         employeeService.deleteEmployee(id);
    }




}

