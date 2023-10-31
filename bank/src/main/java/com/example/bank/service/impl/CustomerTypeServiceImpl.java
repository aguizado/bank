package com.example.bank.service.impl;

import com.example.bank.model.CustomerTypeModel;
import com.example.bank.model.dto.CustomerTypeDto;
import com.example.bank.model.mapper.CustomerTypeMapper;
import com.example.bank.repository.CustomerTypeRepository;
import com.example.bank.service.IcustomerTypeService;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.adapter.rxjava.RxJava3Adapter;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

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
  public Single<CustomerTypeDto> createCustomerType(CustomerTypeDto customerType) {
    CustomerTypeModel customerTypeModel = customerTypeMapper.toCustomerType(customerType);
    Mono<CustomerTypeModel> customerTypeMono = customerTypeRepository.save(customerTypeModel);
    return RxJava3Adapter.monoToSingle(customerTypeMono.map(customerTypeMapper::toEntity));
  }

  @Override
  public Observable<CustomerTypeDto> getCustomerTypes() {
    Flux<CustomerTypeModel> customerTypeFlux = customerTypeRepository.findAll();
    return RxJava3Adapter.fluxToObservable(customerTypeFlux.map(customerTypeMapper::toEntity));
  }

  @Override
  public Single<CustomerTypeDto> getCustomerType(Integer customerTypeId) {
    Mono<CustomerTypeModel> customerTypeMono = customerTypeRepository.findById(customerTypeId);
    return RxJava3Adapter.monoToSingle(customerTypeMono.map(customerTypeMapper::toEntity));
  }

  @Override
  public Single<CustomerTypeDto> editCustomerType(CustomerTypeDto customerType) {
    return createCustomerType(customerType);
  }

}
