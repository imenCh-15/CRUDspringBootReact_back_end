package com.example.employeesApp.repository;

import com.example.employeesApp.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AddressRepository extends JpaRepository<Address,Integer> {

    List<Address> findByEmployeeId(Integer id);
}
