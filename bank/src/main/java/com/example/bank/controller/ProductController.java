package com.example.bank.controller;

import com.example.bank.model.dto.ProductDto;
import com.example.bank.service.IproductService;
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
 * . Class ProductController
 *
 * @author Andres Guizado
 * @version 0.1, 2023/10/27
 */
@RestController
@Log4j2
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
  public Single<ResponseEntity<ProductDto>> createProduct(
      @RequestBody ProductDto product) {
    return productService.createProduct(product)
        .map(ResponseEntity::ok)
        .doOnError(throwable -> log.error(Constants.DO_ON_ERROR, throwable))
        .doOnSuccess(response -> log.info(Constants.DO_ON_SUCCESS, response));
  }
  
  /**
   * . This method is to get Product
   *
   * @return a HTTP Status
   */
  @GetMapping("/product/getAll")
  public Observable<ResponseEntity<ProductDto>> getProducts() { 
    return productService.getProducts()
        .map(ResponseEntity::ok)
        .doOnError(throwable -> log.error(Constants.DO_ON_ERROR, throwable));
  }

  /**
   * . This method is to get Product
   *
   * @param productId This is the first parameter
   * @return a HTTP Status
   */
  @GetMapping("/product/{productId}")
  public Single<ResponseEntity<ProductDto>> getProduct(
      @PathVariable("productId") Integer productId) {
    return productService.getProduct(productId)
        .map(ResponseEntity::ok)
        .doOnError(throwable -> log.error(Constants.DO_ON_ERROR, throwable))
        .doOnSuccess(response -> log.info(Constants.DO_ON_SUCCESS, response));
  }

  /**
   * . This method is to update Product
   *
   * @param productId This is the first parameter
   * @param product   This is the second parameter
   * @return a HTTP Status
   */
  @PutMapping("/product/{productId}")
  public Single<ResponseEntity<ProductDto>> editProduct(
      @PathVariable("productId") Integer productId,
      @RequestBody ProductDto product) {
    return productService.editProduct(product)
        .map(ResponseEntity::ok)
        .doOnError(throwable -> log.error(Constants.DO_ON_ERROR, throwable))
        .doOnSuccess(response -> log.info(Constants.DO_ON_SUCCESS, response));
  }

}
