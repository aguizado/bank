package com.example.bank.service.impl;

import com.example.bank.model.CustomerProductModel;
import com.example.bank.model.dto.CustomerProductDto;
import com.example.bank.model.mapper.CustomerProductMapper;
import com.example.bank.repository.CustomerProductRepository;
import com.example.bank.service.IcustomerProductService;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * . Class ProductServiceImpl
 *
 * @author Andres Guizado
 * @version 0.1, 2023/10/27
 */
@Service
@RequiredArgsConstructor
public class CustomerProductServiceImpl implements IcustomerProductService {
  
  private final CustomerProductRepository customerProductRepository;
  
  private final CustomerProductMapper customerProductMapper;

  @Override
  public CustomerProductDto createCustomerProduct(CustomerProductDto customerProductDto) {
    CustomerProductModel customerProductModel = customerProductMapper
        .toCustomerProduct(customerProductDto);
    return customerProductMapper.INSTANCE.toEntity(
        customerProductRepository.save(customerProductModel));
  }

  @Override
  public Optional<CustomerProductDto> getBalance(Integer customerId) {
    return Optional.of(customerProductMapper.toEntity(customerProductRepository
        .findById(customerId).get()));
  }

  @Override
  public Optional<CustomerProductDto> getProducts(Integer customerId) {
    return Optional.of(customerProductMapper.toEntity(customerProductRepository
        .findById(customerId).get()));
  }

}
