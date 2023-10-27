package com.example.bank.controller;

import com.example.bank.model.dto.ProductDto;
import com.example.bank.service.IproductService;
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
 * . Class ProductController
 *
 * @author Andres Guizado
 * @version 0.1, 2023/10/27
 */
@RestController
@RequiredArgsConstructor
public class ProductController {
  
  @Autowired
  IproductService productService;
  
  /**
   * . This method is to save Product
   *
   * @param product This is the first parameter
   * @return a HTTP Status
   */
  @PostMapping("/product")
  public ResponseEntity<ProductDto> createProduct(
      @RequestBody ProductDto product) {
    try {
      ProductDto productDto = productService.createProduct(product);
      return new ResponseEntity<>(productDto, HttpStatus.CREATED);
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }
  
  /**
   * . This method is to get Product
   *
   * @return a HTTP Status
   */
  @GetMapping("/product/getAll")
  public ResponseEntity<List<ProductDto>> getProducts() { 
    try {
      List<ProductDto> productList = productService.getProducts();
      return ResponseEntity.ok(productList);
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
  }

  /**
   * . This method is to get Product
   *
   * @param productId This is the first parameter
   * @return a HTTP Status
   */
  @GetMapping("/product/{productId}")
  public ResponseEntity<ProductDto> getProduct(
      @PathVariable("productId") Integer productId) {
    Optional<ProductDto> opProduct = productService
        .getProduct(productId);
    if (opProduct.isPresent()) {
      return ResponseEntity.status(HttpStatus.OK).body(opProduct.get());
    }
    return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
  }

  /**
   * . This method is to update Product
   *
   * @param productId This is the first parameter
   * @param product   This is the second parameter
   * @return a HTTP Status
   */
  @PutMapping("/product/{productId}")
  public ResponseEntity<ProductDto> editProduct(
      @PathVariable("productId") Integer productId,
      @RequestBody ProductDto product) {
    Optional<ProductDto> opProduct = productService
        .getProduct(productId);
    if (opProduct.isPresent()) {
      return new ResponseEntity<>(productService
          .editProduct(product), HttpStatus.OK);
    }
    return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
  }

}
