package com.example.bank.controller;

import com.example.bank.model.dto.ProductTypeDto;
import com.example.bank.service.IproductTypeService;
import com.example.bank.util.Constants;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * . Class ProductTypeController
 *
 * @author Andres Guizado
 * @version 0.1, 2023/10/27
 */
@RestController
@Log4j2
@RequiredArgsConstructor
public class ProductTypeController {
  
  @Autowired
  IproductTypeService productTypeService;
  
  /**
   * . This method is to save ProductType
   *
   * @param productType This is the first parameter
   * @return a HTTP Status
   */
  @PostMapping("/product_type")
  public Single<ResponseEntity<ProductTypeDto>> createProductType(
      @RequestBody ProductTypeDto productType) {
    return productTypeService.createProductType(productType)
        .map(ResponseEntity::ok)
        .doOnError(throwable -> log.error(Constants.DO_ON_ERROR, throwable))
        .doOnSuccess(response -> log.info(Constants.DO_ON_SUCCESS, response));
  }
  
  /**
   * . This method is to get Product Type
   *
   * @return a HTTP Status
   */
  @GetMapping("/product_type/getAll")
  public Observable<ResponseEntity<ProductTypeDto>> getProductTypes() { 
    return productTypeService.getProductTypes()
        .map(ResponseEntity::ok)
        .doOnError(throwable -> log.error(Constants.DO_ON_ERROR, throwable));
  }

  /**
   * . This method is to get ProductType
   *
   * @param productTypeId This is the first parameter
   * @return a HTTP Status
   */
  @GetMapping("/product_type/{productTypeId}")
  public Single<ResponseEntity<ProductTypeDto>> getProductType(
      @PathVariable("productTypeId") Integer productTypeId) {
    return productTypeService.getProductType(productTypeId)
        .map(ResponseEntity::ok)
        .doOnError(throwable -> log.error(Constants.DO_ON_ERROR, throwable))
        .doOnSuccess(response -> log.info(Constants.DO_ON_SUCCESS, response));
  }

  /**
   * . This method is to update ProductType
   *
   * @param productTypeId This is the first parameter
   * @param productType   This is the second parameter
   * @return a HTTP Status
   */
  @PutMapping("/product_type/{productTypeId}")
  public Single<ResponseEntity<ProductTypeDto>> editProductType(
      @PathVariable("productTypeId") Integer productTypeId,
      @RequestBody ProductTypeDto productType) {
    return productTypeService.editProductType(productType)
        .map(ResponseEntity::ok)
        .doOnError(throwable -> log.error(Constants.DO_ON_ERROR, throwable))
        .doOnSuccess(response -> log.info(Constants.DO_ON_SUCCESS, response));
  }

}
