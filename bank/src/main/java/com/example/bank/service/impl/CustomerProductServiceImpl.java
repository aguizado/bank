package com.example.bank.service.impl;

import com.example.bank.model.CustomerProductModel;
import com.example.bank.model.dto.CustomerProductDto;
import com.example.bank.model.mapper.CustomerProductMapper;
import com.example.bank.repository.CustomerProductRepository;
import com.example.bank.service.IcustomerProductService;
import io.reactivex.rxjava3.core.Single;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.adapter.rxjava.RxJava3Adapter;
import reactor.core.publisher.Mono;

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
  public Single<CustomerProductDto> createCustomerProduct(CustomerProductDto customerProductDto) {
    CustomerProductModel customerProductModel = customerProductMapper
        .toCustomerProduct(customerProductDto);
    Mono<CustomerProductModel> cpMono = customerProductRepository.save(customerProductModel);
    return RxJava3Adapter.monoToSingle(cpMono.map(customerProductMapper::toEntity));
  }

  @Override
  public Single<CustomerProductDto> getBalance(Integer customerId) {
    Mono<CustomerProductModel> cpMono = customerProductRepository.findById(customerId);
    return RxJava3Adapter.monoToSingle(cpMono.map(customerProductMapper::toEntity));
  }

  @Override
  public Single<CustomerProductDto> getProducts(Integer customerId) {
    Mono<CustomerProductModel> cpMono = customerProductRepository.findById(customerId);
    return RxJava3Adapter.monoToSingle(cpMono.map(customerProductMapper::toEntity));
  }

}
