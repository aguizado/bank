package com.example.bank.service.impl;

import com.example.bank.model.CustomerModel;
import com.example.bank.model.CustomerProductModel;
import com.example.bank.model.ProfileModel.DescriptionEnum;
import com.example.bank.model.dto.CustomerDto;
import com.example.bank.model.mapper.CustomerMapper;
import com.example.bank.model.mapper.CustomerProductMapper;
import com.example.bank.repository.CustomerProductRepository;
import com.example.bank.repository.CustomerRepository;
import com.example.bank.service.IcustomerService;
import io.reactivex.rxjava3.core.Single;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.adapter.rxjava.RxJava3Adapter;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

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
  public Single<CustomerDto> createCustomer(CustomerDto customer) {
    CustomerModel customerModel = customerMapper.toCustomer(customer);
    Mono<CustomerModel> customerMono = customerRepository.save(customerModel);
    return RxJava3Adapter.monoToSingle(customerMono.map(customerMapper::toEntity));
  }

  @Override
  public Single<CustomerDto> getCustomer(Integer customerId) {
    Mono<CustomerModel> customerMono = customerRepository.findById(customerId);
    return RxJava3Adapter.monoToSingle(customerMono.map(customerMapper::toEntity));
  }

  @Override
  public Single<CustomerDto> editCustomer(CustomerDto customer) {
    CustomerModel customerModel = customerMapper.toCustomer(customer);
    
    Mono<CustomerModel> customerMono = customerRepository.findById(customerModel.getId())
        .flatMap(existingUser -> customerRepository.save(existingUser)
            .then(Mono.just(existingUser)));
    
    return RxJava3Adapter.monoToSingle(customerMono.map(customerMapper::toEntity));
  }

  @Override
  public Single<CustomerDto> deleteCustomer(Integer customerId) {
    Mono<CustomerModel> customerMono = customerRepository.findById(customerId)
        .flatMap(existingUser -> customerRepository.delete(existingUser)
        .then(Mono.just(existingUser)));
    return RxJava3Adapter.monoToSingle(customerMono.map(customerMapper::toEntity));
  }
  
}
