package com.example.employeesApp.service;

import com.example.employeesApp.model.Address;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface AddressService {
    public Address getAddressById(@PathVariable Integer id);
    public Address saveAddress(@PathVariable Integer id, Address address);
    public List <Address> getEmployeeAddresses(@PathVariable Integer id);
public  void addEmployeeAddress(@PathVariable Integer employeeId, Address address);
    public Address updateAddress(@PathVariable Integer id,@PathVariable Integer addressId, Address address);
    public void deleteAddress(@PathVariable Integer addressId);
}
