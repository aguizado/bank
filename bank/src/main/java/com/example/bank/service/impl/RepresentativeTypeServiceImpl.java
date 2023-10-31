package com.example.bank.service.impl;

import com.example.bank.model.RepresentativeTypeModel;
import com.example.bank.model.dto.RepresentativeTypeDto;
import com.example.bank.model.mapper.RepresentativeTypeMapper;
import com.example.bank.repository.RepresentativeTypeRepository;
import com.example.bank.service.IrepresentativeTypeService;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.adapter.rxjava.RxJava3Adapter;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * . Class RepresentativeTypeImpl
 *
 * @author Andres Guizado
 * @version 0.1, 2023/10/27
 */
@Service
@RequiredArgsConstructor
public class RepresentativeTypeServiceImpl implements IrepresentativeTypeService {
  
  private final RepresentativeTypeRepository representativeTypeRepository;
  
  private final RepresentativeTypeMapper representativeTypeMapper;
  
  @Override
  public Single<RepresentativeTypeDto> createRepresentativeType(
      RepresentativeTypeDto representativeType) {
    RepresentativeTypeModel representativeTypeModel = representativeTypeMapper
        .toRepresentativeType(representativeType);
    Mono<RepresentativeTypeModel> representativeTypeMono = representativeTypeRepository
        .save(representativeTypeModel);
    return RxJava3Adapter.monoToSingle(representativeTypeMono
        .map(representativeTypeMapper::toEntity));
  }

  @Override
  public Observable<RepresentativeTypeDto> getRepresentativeTypes() {
    Flux<RepresentativeTypeModel> representativeTypeFlux = representativeTypeRepository.findAll();
    return RxJava3Adapter.fluxToObservable(representativeTypeFlux
        .map(representativeTypeMapper::toEntity));
  }

  @Override
  public Single<RepresentativeTypeDto> getRepresentativeType(Integer representativeTypeId) {
    Mono<RepresentativeTypeModel> representativeTypeMono = representativeTypeRepository
        .findById(representativeTypeId);
    return RxJava3Adapter.monoToSingle(representativeTypeMono
        .map(representativeTypeMapper::toEntity));
  }

  @Override
  public Single<RepresentativeTypeDto> editRepresentativeType(
      RepresentativeTypeDto representativeType) {
    return createRepresentativeType(representativeType);
  }

}
