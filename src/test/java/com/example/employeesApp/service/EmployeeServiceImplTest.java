package com.example.employeesApp.service;

import com.example.employeesApp.model.Employee;
import com.example.employeesApp.repository.EmployeeRepository;
import static org.assertj.core.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import static org.mockito.Mockito.*;

import java.util.Optional;


class EmployeeServiceImplTest {

    @Mock private EmployeeRepository employeeRepository;
    @InjectMocks
    private EmployeeServiceImpl underTest;
    private AutoCloseable autoCloseable;
    private Employee employee;
    @BeforeEach
    void setUp() {
        autoCloseable= MockitoAnnotations.openMocks(this);
        underTest=new EmployeeServiceImpl(employeeRepository);
        employee =Employee.builder().name("imen").email("imen@yahoo.com").build();
    }

    @AfterEach
    void tearDown() throws Exception{
        autoCloseable.close();
    }

    @Test
    void canSaveEmployee() {
        underTest.saveEmployee(employee);

        //check if employeeRepository was involved with the same employee that we post
        ArgumentCaptor<Employee> employeeArgumentCaptor=ArgumentCaptor.forClass(Employee.class);

       //we verify that the employee repo was invoked with the save method
        //at the same time we capture the value
        verify(employeeRepository).save(employeeArgumentCaptor.capture());
        Employee capturedEmployee=employeeArgumentCaptor.getValue();
        //capturedEmployee is what the saveEmployee service receive
        // and the employee is what the underTest receive
        //check if the employee that was passed to the service is the same one that the employee repo has been given

        assertThat(capturedEmployee).isEqualTo(employee);



    }

    @Test
    void canGetEmployees() {
        //WHEN
    underTest.getEmployees();
        //THEN
    verify(employeeRepository).findAll();

    }
    @Test
    void canGetListEmployees() {
        //WHEN
        underTest.getListEmployees();
        //THEN
        verify(employeeRepository).findAll();

    }


    @Test
    void canGetEmployeeById() {
        employee.setId(98);
        when(employeeRepository.findById(employee.getId())).thenReturn(Optional.of(employee));
        underTest.getEmployeeById(employee.getId());
        verify(employeeRepository).findById(employee.getId());

    }
    @Test
    public void canGetEmployeeById_should_throw_exception_when_employee_doesnt_exist()  {
        employee.setId(89);
        assertThrows(RuntimeException.class,() ->{
            underTest.getEmployeeById(anyInt());
        });

    }


    @Test
    void canUpdateEmployee() {
        employee.setId(5);
        Employee newEmployee=new Employee("ben","ben@yahoo.com");

        when(employeeRepository.findById(employee.getId())).thenReturn(Optional.of(employee));
        underTest.updateEmployee(newEmployee,employee.getId());
        verify(employeeRepository).findById(employee.getId());
        verify(employeeRepository).save(newEmployee);




    }
    @Test
    void canUpdateEmployee_when_employee_doesnt_exist() {
        employee.setId(5);
        Employee newEmployee=new Employee("ben","ben@yahoo.com");
        assertThrows(RuntimeException.class,() ->{
            underTest.updateEmployee(newEmployee,anyInt());
        });



    }


    @Test
    public void whenGivenId_shouldDeleteEmployee_ifFound() {
        employee.setId(98);
        when(employeeRepository.findById(employee.getId())).thenReturn(Optional.of(employee));
        underTest.deleteEmployee(employee.getId());
        verify(employeeRepository).deleteById(employee.getId());

    }
    @Test
    public void should_throw_exception_when_employee_doesnt_exist()  {
        employee.setId(89);
       assertThrows(RuntimeException.class,() ->{
           underTest.deleteEmployee(anyInt());
       });

    }
}