package com.example.employeesApp.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "address")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Integer id;
    private String city;
    private String street;
    private String zip_code;
    private  String type;
   // @ManyToOne(cascade = CascadeType.ALL)
   // @JoinColumn(name = "employee_id")
   // private Employee employee;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "employee_id")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Employee employee;

    @Override
    public boolean equals(Object obj){
        System.out.println("overrided equals method invoked");

        //if same object return true
        if(this==obj)
        return true;
       if(obj==null||obj.getClass()!=this.getClass()){
        return false;}
        Address address=(Address)obj;
        return this.getCity().trim().equalsIgnoreCase(address.getCity().trim())&&
            this.getStreet().trim().equalsIgnoreCase(address.getStreet().trim()) &&
            this.getZip_code().trim().equalsIgnoreCase(address.getZip_code().trim());

}
@Override
    public int hashCode(){
    System.out.println("overrided hashcode method invoked");
        return this.street.length()%10;
}


}
