package com.example.employeesApp.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
//@Entity annotation indicates that the class is a persistent Java class
@Entity
@Table(name = "employee")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String email;
    @OneToMany(mappedBy = "employee",cascade = CascadeType.ALL)

    private List<Address> employeeAddresses ;


    //Synchronize both sides of a bidirectional association
   /* public void addAddress(Address address) {
        employeeAddresses.add(address);
        address.setEmployee(this);
    }*/

 /*   public void removeAddress(Address address) {
        employeeAddresses.remove(address);
        address.setEmployee(null);
    }

  */


}

