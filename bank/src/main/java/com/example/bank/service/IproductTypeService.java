package com.example.bank.service;

import com.example.bank.model.dto.ProductTypeDto;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;

/**
 * . Interface IproductTypeService
 *
 * @author Andres Guizado
 * @version 0.1, 2023/10/27
 */
public interface IproductTypeService {
  
  public Single<ProductTypeDto> createProductType(ProductTypeDto productType);
  
  public Observable<ProductTypeDto> getProductTypes();
  
  public Single<ProductTypeDto> getProductType(Integer productTypeId);
  
  public Single<ProductTypeDto> editProductType(ProductTypeDto productType);

}
