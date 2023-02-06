package com.example.employeesApp.repository;

import com.example.employeesApp.model.Employee;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2 )
class EmployeeRepositoryTest {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Test
    void should_SaveEmployee_Return_Employee() {
        //Arrange
        Employee employee = Employee.builder().name("imen").email("imen@yahoo.com").build();

        //Act
        Employee savedEmployee=employeeRepository.save(employee);

        //Assert
        Assertions.assertThat(savedEmployee).isNotNull();
        Assertions.assertThat(savedEmployee.getId()).isGreaterThan(0);
    }
    @Test
    void should_GetAllEmployees_Return_MoreThenOneEmployee() {
        //Arrange
        Employee employee1 = Employee.builder().name("imen").email("imen@yahoo.com").build();
        Employee employee2 = Employee.builder().name("imen2").email("imen2@yahoo.com").build();
        employeeRepository.save(employee1);
        employeeRepository.save(employee2);
        //Act

        List<Employee> employeeList=employeeRepository.findAll();

        //Assert
        Assertions.assertThat(employeeList).isNotNull();
        Assertions.assertThat(employeeList.size()).isEqualTo(2);
    }
    @Test
    void should_GetEmployeeById_ReturnEmployee(){
        //Arrange
        Employee employee = Employee.builder().name("imen").email("imen@yahoo.com").build();
        Employee savedEmployee =employeeRepository.save(employee);

        //Act

        Employee resultEmployee=employeeRepository.findById(savedEmployee.getId()).get();
        System.out.println(employeeRepository.findById(savedEmployee.getId()));

        //Assert
        Assertions.assertThat(resultEmployee).isNotNull();
    }
}