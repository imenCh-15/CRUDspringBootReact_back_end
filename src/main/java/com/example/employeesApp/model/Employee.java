package com.example.employeesApp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
//@Entity annotation indicates that the class is a persistent Java class
@Entity
@Builder
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;

    private String email;

    @OneToMany(mappedBy = "employee",cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Address> employeeAddresses ;

    public Employee(String name, String email) {
        this.name=name;
        this.email=email;
    }


    //Synchronize both sides of a bidirectional association
    public void addAddress(Address address) {
        employeeAddresses.add(address);
        address.setEmployee(this);
    }

    public void removeAddress(Address address) {
        employeeAddresses.remove(address);
        address.setEmployee(null);
    }




}

