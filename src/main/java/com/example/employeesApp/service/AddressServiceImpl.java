package com.example.employeesApp.service;

import com.example.employeesApp.exception.AddressException;
import com.example.employeesApp.exception.EmployeeNotFoundException;
import com.example.employeesApp.model.Address;
import com.example.employeesApp.model.Employee;
import com.example.employeesApp.repository.AddressRepository;
import com.example.employeesApp.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;

@Service
public class AddressServiceImpl implements AddressService {
    @Autowired
    private AddressRepository addressRepository;

   @Autowired
    private EmployeeRepository employeeRepository;
    @Override
    public Address saveAddress(Integer id, Address address){
    List <Address> employeeAddresses= getEmployeeAddresses(id);

       if(findMach(employeeAddresses,address))throw new AddressException("Address already exists!");

       return employeeRepository.findById(id).map(employee -> {
           // employee.addAddress(address);
          address.setEmployee(employee);
           return addressRepository.save(address);
        }).orElseThrow(()->new EmployeeNotFoundException(id));
    }
    @Override
    public Address getAddressById(Integer id){
        return addressRepository.findById(id).orElseThrow(()->new AddressException("Could not found the employee address with id : "+id));
    }
    @Override
    public List<Address> getEmployeeAddresses(Integer id) {
        List<Address> addressRestList = new ArrayList<>();

        List<Address> addressesList= addressRepository.findByEmployeeId(id);
        if(addressesList!=null&&!addressesList.isEmpty()) {
            addressRestList = addressesList;
        }


        return addressRestList;
    }

    public Address update(Address address,Integer id) {
        Employee  employee = employeeRepository.findById(address.getEmployee().getId()).orElseThrow(()->new EmployeeNotFoundException(address.getEmployee().getId()));
        Address add=addressRepository.findById(id).orElseThrow(()->new EmployeeNotFoundException(id));

        address.setEmployee(employee);
        employee.setId(add.getId());
         return addressRepository.save(address);

    }



    @Override
    public Address updateAddress(Integer id ,Integer addressId, Address address){
        List<Address>employeeAddresses= getEmployeeAddresses(id);
        if(findMach(employeeAddresses,address))throw new AddressException("Address already exists!");

        return employeeRepository.findById(id).map(employee -> {
            //address.setEmployee(employee);
            return addressRepository.findById(addressId).map(add->{
                add.setCity(address.getCity());
                add.setStreet(address.getStreet());
                add.setZip_code(address.getZip_code());
                return addressRepository.save(add);

            }).orElseThrow(()->new EmployeeNotFoundException(id));
        }).orElseThrow(()->new EmployeeNotFoundException(id));
    }

    @Override
    public void deleteAddress(@PathVariable Integer addressId){

        if(!addressRepository.existsById(addressId)) {
            System.out.println(addressId);
            throw new AddressException("Could not found the employee address with id : "+addressId);
        }
        addressRepository.deleteById(addressId);
    }
    public boolean findMach(final List<Address> list, final Address address){
        if(list==null||list.isEmpty())
            return false;

        for( Address o:list){
            if(o.hashCode()==address.hashCode())
            if(o.equals(address))
            return true;
        }
       return false;
    }
}
