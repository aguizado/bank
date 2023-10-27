package com.example.bank.controller;

import com.example.bank.model.dto.ProductTypeDto;
import com.example.bank.service.IproductTypeService;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
  public ResponseEntity<ProductTypeDto> createProductType(
      @RequestBody ProductTypeDto productType) {
    try {
      ProductTypeDto productTypeDto = productTypeService.createProductType(productType);
      return new ResponseEntity<>(productTypeDto, HttpStatus.CREATED);
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }
  
  /**
   * . This method is to get Product Type
   *
   * @return a HTTP Status
   */
  @GetMapping("/product_type/getAll")
  public ResponseEntity<List<ProductTypeDto>> getProductTypes() { 
    try {
      List<ProductTypeDto> productTypeList = productTypeService.getProductTypes();
      return ResponseEntity.ok(productTypeList);
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
  }

  /**
   * . This method is to get ProductType
   *
   * @param productTypeId This is the first parameter
   * @return a HTTP Status
   */
  @GetMapping("/product_type/{productTypeId}")
  public ResponseEntity<ProductTypeDto> getProductType(
      @PathVariable("productTypeId") Integer productTypeId) {
    Optional<ProductTypeDto> opProductType = productTypeService
        .getProductType(productTypeId);
    if (opProductType.isPresent()) {
      return ResponseEntity.status(HttpStatus.OK).body(opProductType.get());
    }
    return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
  }

  /**
   * . This method is to update ProductType
   *
   * @param productTypeId This is the first parameter
   * @param productType   This is the second parameter
   * @return a HTTP Status
   */
  @PutMapping("/product_type/{productTypeId}")
  public ResponseEntity<ProductTypeDto> editProductType(
      @PathVariable("productTypeId") Integer productTypeId,
      @RequestBody ProductTypeDto productType) {
    Optional<ProductTypeDto> opProductType = productTypeService
        .getProductType(productTypeId);
    if (opProductType.isPresent()) {
      return new ResponseEntity<>(productTypeService
          .editProductType(productType), HttpStatus.OK);
    }
    return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
  }

}
