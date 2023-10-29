package com.example.bank.service;

import com.example.bank.model.dto.CustomerProductDto;
import java.util.Optional;

/**
 * . Interface IcustomerProductService
 *
 * @author Andres Guizado
 * @version 0.1, 2023/10/27
 */
public interface IcustomerProductService {
  
  public CustomerProductDto createCustomerProduct(CustomerProductDto customerProductDto);
  
  public Optional<CustomerProductDto> getBalance(Integer customerProducId);
  
  public Optional<CustomerProductDto> getProducts(Integer customerProducId);

}
