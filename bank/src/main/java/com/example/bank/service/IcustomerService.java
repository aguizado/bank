package com.example.bank.service;

import com.example.bank.model.dto.CustomerDto;
import io.reactivex.rxjava3.core.Single;

/**
 * . Interface IcustomerService
 *
 * @author Andres Guizado
 * @version 0.1, 2023/10/16
 */
public interface IcustomerService {

  public Single<CustomerDto> createCustomer(CustomerDto customer);
  
  public Single<CustomerDto> getCustomer(Integer customerId);
  
  public Single<CustomerDto> editCustomer(CustomerDto customer);
  
  public Single<CustomerDto> deleteCustomer(Integer customerId);

}
