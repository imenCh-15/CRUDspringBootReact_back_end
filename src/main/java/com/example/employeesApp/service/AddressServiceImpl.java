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
          address.setEmployee(employee);
           return addressRepository.save(address);
        }).orElseThrow(()->new EmployeeNotFoundException(id));
    }

    @Override
    public void addEmployeeAddress(Integer employeeId, Address address) {
        Employee employee = employeeRepository.findById(employeeId).orElseThrow(()->new EmployeeNotFoundException(employeeId));
        if (employee != null) {
            List<Address> addresses = employee.getEmployeeAddresses();
            if (addresses == null) {
                addresses = new ArrayList<>();
            }
            address.setEmployee(employee);
            Address newAddress=addressRepository.save(address);
            addresses.add(newAddress);
            employee.setEmployeeAddresses(addresses);
            employeeRepository.save(employee);

        }
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


    @Override
    public Address updateAddress(Integer employeeId ,Integer addressId, Address address){
        Employee employee =employeeRepository.findById(employeeId).orElseThrow(
                () ->new EmployeeNotFoundException(employeeId));
        Address employeeAddress =addressRepository.findById(addressId).orElseThrow(() ->
                new AddressException("Could not found the employee address with id : "+addressId));
        if(employeeAddress.getEmployee().getId()!=employee.getId()){
            throw new AddressException("This address does not belong to the employee");
        }
        List<Address>employeeAddresses= getEmployeeAddresses(employeeId);
        if(findMach(employeeAddresses,address)) {
            throw new AddressException("Address already exists!");
        }

        employeeAddress.setCity(address.getCity());
        employeeAddress.setStreet(address.getStreet());
        employeeAddress.setZip_code(address.getZip_code());
            return addressRepository.save(employeeAddress);


    }


    @Override
    public void deleteAddress(@PathVariable Integer addressId){

      addressRepository.findById(addressId).orElseThrow(
              () -> new AddressException("Could not found the employee address with id : "+addressId));
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
