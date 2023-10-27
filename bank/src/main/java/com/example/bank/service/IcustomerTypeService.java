package com.example.bank.service;

import com.example.bank.model.dto.CustomerTypeDto;
import java.util.List;
import java.util.Optional;

/**
 * . Interface IcustomerTypeService
 *
 * @author Andres Guizado
 * @version 0.1, 2023/10/27
 */
public interface IcustomerTypeService {
  
  public CustomerTypeDto createCustomerType(CustomerTypeDto customerType);
  
  public List<CustomerTypeDto> getCustomerTypes();
  
  public Optional<CustomerTypeDto> getCustomerType(Integer customerTypeId);
  
  public CustomerTypeDto editCustomerType(CustomerTypeDto customerType);

}
