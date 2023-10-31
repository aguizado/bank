package com.example.bank.service;

import com.example.bank.model.dto.ProductDto;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;

/**
 * . Interface IproductService
 *
 * @author Andres Guizado
 * @version 0.1, 2023/10/27
 */
public interface IproductService {
  
  public Single<ProductDto> createProduct(ProductDto product);
  
  public Observable<ProductDto> getProducts();
  
  public Single<ProductDto> getProduct(Integer productId);
  
  public Single<ProductDto> editProduct(ProductDto product);

}
