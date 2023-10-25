package com.example.bank.service;

import com.example.bank.model.CustomerModel;
import java.util.Optional;

/**
 * . Interface CustomerApiDelegate
 *
 * @author Andres Guizado
 * @version 0.1, 2023/10/16
 */
public interface IcustomerService {

  public CustomerModel createCustomer(CustomerModel customer);
  
  public Optional<CustomerModel> getCustomer(Integer customerId);
  
  public CustomerModel editCustomer(CustomerModel customer);
  
  void deleteCustomer(Integer customerId);

}
