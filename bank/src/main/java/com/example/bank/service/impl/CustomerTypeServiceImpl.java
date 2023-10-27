package com.example.bank.service.impl;

import com.example.bank.model.CustomerTypeModel;
import com.example.bank.model.dto.CustomerTypeDto;
import com.example.bank.model.mapper.CustomerTypeMapper;
import com.example.bank.repository.CustomerTypeRepository;
import com.example.bank.service.IcustomerTypeService;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * . Class CustomerTypeServiceImpl
 *
 * @author Andres Guizado
 * @version 0.1, 2023/10/27
 */
@Service
@RequiredArgsConstructor
public class CustomerTypeServiceImpl implements IcustomerTypeService {
  
  private final CustomerTypeRepository customerTypeRepository;
  
  private final CustomerTypeMapper customerTypeMapper;

  @Override
  public CustomerTypeDto createCustomerType(CustomerTypeDto customerType) {
    CustomerTypeModel customerTypeModel = customerTypeMapper.toCustomerType(customerType);
    return customerTypeMapper.INSTANCE.toEntity(customerTypeRepository.save(customerTypeModel));
  }

  @Override
  public List<CustomerTypeDto> getCustomerTypes() {
    return customerTypeMapper.toEntityList(customerTypeRepository.findAll());
  }

  @Override
  public Optional<CustomerTypeDto> getCustomerType(Integer customerTypeId) {
    return Optional.of(customerTypeMapper.toEntity(customerTypeRepository
        .findById(customerTypeId).get()));
  }

  @Override
  public CustomerTypeDto editCustomerType(CustomerTypeDto customerType) {
    return createCustomerType(customerType);
  }

}
