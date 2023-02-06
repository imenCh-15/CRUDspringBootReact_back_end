package com.example.employeesApp.service;


import com.example.employeesApp.model.Address;
import com.example.employeesApp.model.Employee;
import com.example.employeesApp.repository.AddressRepository;
import com.example.employeesApp.repository.EmployeeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AddressServiceImplTest {
    @Mock
    private AddressRepository addressRepository;
    @Mock
    private EmployeeRepository employeeRepository;
    @InjectMocks
    private AddressServiceImpl addressService;
    private Employee employee;
    private Address address;
    @BeforeEach
    public void init(){
         employee =Employee.builder().name("imen").email("imen@yahoo.com").build();
         address = Address.builder().city("paris")
                .street("25 rue Mederic").type("optional").zip_code("75250").build();
    }

    @Test
    void shouldSaveAddress(){
        when(employeeRepository.findById(employee.getId())).thenReturn(Optional.of(employee));
        when(addressRepository.save(any(Address.class))).thenReturn(address);
        Address savedAddress=addressService.saveAddress(employee.getId(),address);
        assertThat(savedAddress).isNotNull();
    }
    @Test
    void shouldGetEmployeeAddressesList(){
        int employeeId =1;
        when(addressRepository.findByEmployeeId(employeeId)).thenReturn(Arrays.asList(address));
        List<Address> addressesList=addressService.getEmployeeAddresses(employeeId);
        assertThat(addressesList).isNotNull();
    }
    @Test
    void shouldGetAddressById(){
        when(addressRepository.findById(address.getId())).thenReturn(Optional.of(address));
        Address savedAddress=addressService.getAddressById(address.getId());
        assertThat(savedAddress).isNotNull();
        assertThat(savedAddress.getCity()).isEqualTo(address.getCity());

    }
    @Test
    void should_updateAddress(){
        int employeeId =1;
        int addressId=1;
        employee.setEmployeeAddresses(Arrays.asList(address));
        address.setEmployee(employee);
        when(employeeRepository.findById(employeeId)).thenReturn(Optional.of(employee));
        when(addressRepository.findById(addressId)).thenReturn(Optional.of(address));
        when(addressRepository.findByEmployeeId(employeeId)).thenReturn(Arrays.asList(address));

        when(addressRepository.save(address)).thenReturn(address);
        Address newAddress=Address.builder()
                                  .city("paris").street("50 avenue de versailles").type("optional").zip_code("75270").build();
        Address updatedAddress=addressService.updateAddress(employeeId, addressId,newAddress);
        assertThat(updatedAddress.getStreet()).isEqualTo("50 avenue de versailles");
        assertThrows(RuntimeException.class,() ->{
            addressService.updateAddress(anyInt(),addressId,newAddress);
        });
        assertThrows(RuntimeException.class,() ->{
            addressService.updateAddress(employeeId,anyInt(),newAddress);
        });
        assertThrows(RuntimeException.class,() ->{
            addressService.updateAddress(employeeId,addressId,address);
        });



    }
    @Test
    void should_deleteAddress(){
       address.setId(1);
        when(addressRepository.findById(address.getId())).thenReturn(Optional.of(address));
        addressService.deleteAddress(address.getId());
         assertThrows(RuntimeException.class,()->{
             addressService.deleteAddress(anyInt());
         });

    }

}