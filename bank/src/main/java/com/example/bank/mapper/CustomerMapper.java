package com.example.bank.mapper;

import com.example.bank.model.Customer;
import com.example.bank.model.CustomerModel;

import java.util.List;

import org.mapstruct.Mapper;

/**.
* Class CustomerModel

* @author Andres Guizado
* @version 0.1, 2023/10/19
*/
@Mapper(componentModel = "spring")
public interface CustomerMapper {
	
	Customer toCustomer(CustomerModel customerModel);
	
	List<Customer> toCustomers(List<CustomerModel> customerModels);
	
	CustomerModel toEntity(Customer customer);

}
