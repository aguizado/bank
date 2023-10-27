package com.example.bank.service.impl;

import com.example.bank.model.CustomerModel;
import com.example.bank.model.dto.CustomerDto;
import com.example.bank.model.mapper.CustomerMapper;
import com.example.bank.repository.CustomerRepository;
import com.example.bank.service.IcustomerService;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * . Class CustomerServiceImpl
 *
 * @author Andres Guizado
 * @version 0.1, 2023/10/16
 */
@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements IcustomerService {
  
  private final CustomerRepository customerRepository;
  
  private final CustomerMapper customerMapper;
  
  @Override
  public CustomerDto createCustomer(CustomerDto customer) {
    CustomerModel customerModel = customerMapper.toCustomer(customer);
    return customerMapper.INSTANCE.toEntity(customerRepository.save(customerModel));
  }

  @Override
  public Optional<CustomerDto> getCustomer(Integer customerId) {
    return Optional.of(customerMapper.toEntity(customerRepository
        .findById(customerId).get()));
  }

  @Override
  public CustomerDto editCustomer(CustomerDto customer) {
    return createCustomer(customer);
  }

  @Override
  public void deleteCustomer(Integer customerId) {
    customerRepository.deleteById(customerId);
  }
  
}
