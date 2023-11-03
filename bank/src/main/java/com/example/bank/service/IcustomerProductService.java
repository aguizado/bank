package com.example.bank.service;

import com.example.bank.model.dto.CustomerProductDto;
import com.example.bank.model.dto.OperationDto;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;

/**
 * . Interface IcustomerProductService
 *
 * @author Andres Guizado
 * @version 0.1, 2023/10/27
 */
public interface IcustomerProductService {
  
  public Single<CustomerProductDto> createCustomerProduct(CustomerProductDto customerProductDto);
  
  public Single<CustomerProductDto> getBalance(Integer customerProducId);
  
  public Observable<CustomerProductDto> getProducts(Integer customerProducId);
  
  public Single<CustomerProductDto> validateToUpdate(OperationDto operationDto);

}
