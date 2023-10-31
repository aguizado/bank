package com.example.bank.controller;

import com.example.bank.model.dto.ProductTypeDto;
import com.example.bank.service.IproductTypeService;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
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
  public Single<ProductTypeDto> createProductType(
      @RequestBody ProductTypeDto productType) {
    return productTypeService.createProductType(productType);
  }
  
  /**
   * . This method is to get Product Type
   *
   * @return a HTTP Status
   */
  @GetMapping("/product_type/getAll")
  public Observable<ProductTypeDto> getProductTypes() { 
    return productTypeService.getProductTypes();
  }

  /**
   * . This method is to get ProductType
   *
   * @param productTypeId This is the first parameter
   * @return a HTTP Status
   */
  @GetMapping("/product_type/{productTypeId}")
  public Single<ProductTypeDto> getProductType(
      @PathVariable("productTypeId") Integer productTypeId) {
    return productTypeService.getProductType(productTypeId);
  }

  /**
   * . This method is to update ProductType
   *
   * @param productTypeId This is the first parameter
   * @param productType   This is the second parameter
   * @return a HTTP Status
   */
  @PutMapping("/product_type/{productTypeId}")
  public Single<ProductTypeDto> editProductType(
      @PathVariable("productTypeId") Integer productTypeId,
      @RequestBody ProductTypeDto productType) {
    return productTypeService.editProductType(productType);
  }

}
