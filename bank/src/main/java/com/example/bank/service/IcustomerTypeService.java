package com.example.bank.service;

import com.example.bank.model.dto.CustomerTypeDto;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;

/**
 * . Interface IcustomerTypeService
 *
 * @author Andres Guizado
 * @version 0.1, 2023/10/27
 */
public interface IcustomerTypeService {
  
  public Single<CustomerTypeDto> createCustomerType(CustomerTypeDto customerType);
  
  public Observable<CustomerTypeDto> getCustomerTypes();
  
  public Single<CustomerTypeDto> getCustomerType(Integer customerTypeId);
  
  public Single<CustomerTypeDto> editCustomerType(CustomerTypeDto customerType);

}
