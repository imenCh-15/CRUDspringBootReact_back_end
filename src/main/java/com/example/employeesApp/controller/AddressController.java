package com.example.employeesApp.controller;

import com.example.employeesApp.model.Address;
import com.example.employeesApp.repository.AddressRepository;
import com.example.employeesApp.repository.EmployeeRepository;
import com.example.employeesApp.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/employee")
public class AddressController {

    @Value("${mail.host}")
    private String host;
    @Value("${mail.from}")
    private String from;
    @Value("${mail.port}")
    private String port;
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private AddressRepository addressRepository;
    @Autowired

    private AddressService addressService;


    @GetMapping("/address/{id}")
    public  Address getAddress(@PathVariable Integer id){
        return addressService.getAddressById(id);
    }
   @PostMapping("/{employeeId}/addresses/add")

    public Address addAddress(@PathVariable Integer employeeId, @RequestBody Address employeeAddress) {
       //System.out.println("mail props load using @value"+host+""+port+""+from);
       return addressService.saveAddress(employeeId,employeeAddress);
    }





    @GetMapping("/{employeeId}/addresses")
    public List<Address> addressesList(@PathVariable Integer employeeId){
        return addressService.getEmployeeAddresses(employeeId);
    }
    @PutMapping("/{employeeId}/addresses/update/{addressId}")
    public Address updateEmployeeAddress(@PathVariable Integer employeeId,@PathVariable Integer addressId,@RequestBody Address address){
        return addressService.updateAddress(employeeId,addressId,address);
    }
    @DeleteMapping("/addresses/delete/{addressId}")
    public void deleteAddress(@PathVariable Integer addressId){
       addressService.deleteAddress(addressId);
    }



}
