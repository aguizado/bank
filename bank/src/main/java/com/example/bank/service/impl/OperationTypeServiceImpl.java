package com.example.bank.service.impl;

import com.example.bank.model.OperationTypeModel;
import com.example.bank.model.dto.OperationTypeDto;
import com.example.bank.model.mapper.OperationTypeMapper;
import com.example.bank.repository.OperationTypeRepository;
import com.example.bank.service.IoperationTypeService;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.adapter.rxjava.RxJava3Adapter;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * . Class OperationTypeServiceImpl
 *
 * @author Andres Guizado
 * @version 0.1, 2023/10/26
 */
@Service
@RequiredArgsConstructor
public class OperationTypeServiceImpl implements IoperationTypeService {
  
  private final OperationTypeRepository operationTypeRepository;
  
  private final OperationTypeMapper operationTypeMapper;
  
  @Override
  public Single<OperationTypeDto> createOperationType(OperationTypeDto operationType) {
    OperationTypeModel operationTypeModel = operationTypeMapper.toOperationType(operationType);
    Mono<OperationTypeModel> operationTypeMono = operationTypeRepository.save(operationTypeModel);
    return RxJava3Adapter.monoToSingle(operationTypeMono.map(operationTypeMapper::toEntity));
  }

  @Override
  public Observable<OperationTypeDto> getOperationTypes() {
    Flux<OperationTypeModel> operationTypeFlux = operationTypeRepository.findAll();
    return RxJava3Adapter.fluxToObservable(operationTypeFlux.map(operationTypeMapper::toEntity));
  }

  @Override
  public Single<OperationTypeDto> getOperationType(Integer operationTypeId) {
    Mono<OperationTypeModel> operationTypeMono = operationTypeRepository.findById(operationTypeId);
    return RxJava3Adapter.monoToSingle(operationTypeMono.map(operationTypeMapper::toEntity));
  }

  @Override
  public Single<OperationTypeDto> editOperationType(OperationTypeDto operationType) {
    return createOperationType(operationType);
  }

}
