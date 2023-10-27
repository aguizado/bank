package com.example.bank.service;

import com.example.bank.model.dto.CustomerDto;
import java.util.Optional;

/**
 * . Interface CustomerApiDelegate
 *
 * @author Andres Guizado
 * @version 0.1, 2023/10/16
 */
public interface IcustomerService {

  public CustomerDto createCustomer(CustomerDto customer);
  
  public Optional<CustomerDto> getCustomer(Integer customerId);
  
  public CustomerDto editCustomer(CustomerDto customer);
  
  void deleteCustomer(Integer customerId);

}
