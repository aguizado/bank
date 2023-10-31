package com.example.bank.service.impl;

import com.example.bank.model.ProductModel;
import com.example.bank.model.dto.ProductDto;
import com.example.bank.model.mapper.ProductMapper;
import com.example.bank.repository.ProductRepository;
import com.example.bank.service.IproductService;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.adapter.rxjava.RxJava3Adapter;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * . Class ProductServiceImpl
 *
 * @author Andres Guizado
 * @version 0.1, 2023/10/27
 */
@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements IproductService {
  
  private final ProductRepository productRepository;
  
  private final ProductMapper productMapper;

  @Override
  public Single<ProductDto> createProduct(ProductDto product) {
    ProductModel productModel = productMapper.toProduct(product);
    Mono<ProductModel> productMono = productRepository.save(productModel);
    return RxJava3Adapter.monoToSingle(productMono.map(productMapper::toEntity));
  }

  @Override
  public Observable<ProductDto> getProducts() {
    Flux<ProductModel> productFlux = productRepository.findAll();
    return RxJava3Adapter.fluxToObservable(productFlux.map(productMapper::toEntity));
  }

  @Override
  public Single<ProductDto> getProduct(Integer productId) {
    Mono<ProductModel> productMono = productRepository.findById(productId);
    return RxJava3Adapter.monoToSingle(productMono.map(productMapper::toEntity));
  }

  @Override
  public Single<ProductDto> editProduct(ProductDto product) {
    return createProduct(product);
  }

}
