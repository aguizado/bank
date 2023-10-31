package com.example.bank.service.impl;

import com.example.bank.model.ProductTypeModel;
import com.example.bank.model.dto.ProductTypeDto;
import com.example.bank.model.mapper.ProductTypeMapper;
import com.example.bank.repository.ProductTypeRepository;
import com.example.bank.service.IproductTypeService;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.adapter.rxjava.RxJava3Adapter;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * . Class ProductTypeServiceImpl
 *
 * @author Andres Guizado
 * @version 0.1, 2023/10/27
 */
@Service
@RequiredArgsConstructor
public class ProductTypeServiceImpl implements IproductTypeService {
  
  private final ProductTypeRepository productTypeRepository;
  
  private final ProductTypeMapper productTypeMapper;
  
  @Override
  public Single<ProductTypeDto> createProductType(ProductTypeDto productType) {
    ProductTypeModel productTypeModel = productTypeMapper.toProductType(productType);
    Mono<ProductTypeModel> productTypeMono = productTypeRepository.save(productTypeModel);
    return RxJava3Adapter.monoToSingle(productTypeMono.map(productTypeMapper::toEntity));
  }
  
  @Override
  public Observable<ProductTypeDto> getProductTypes() {
    Flux<ProductTypeModel> productTypeFlux = productTypeRepository.findAll();
    return RxJava3Adapter.fluxToObservable(productTypeFlux.map(productTypeMapper::toEntity));
  }

  @Override
  public Single<ProductTypeDto> getProductType(Integer productTypeId) {
    Mono<ProductTypeModel> productTypeMono = productTypeRepository.findById(productTypeId);
    return RxJava3Adapter.monoToSingle(productTypeMono.map(productTypeMapper::toEntity));
  }

  @Override
  public Single<ProductTypeDto> editProductType(ProductTypeDto productType) {
    return createProductType(productType);
  }

}
