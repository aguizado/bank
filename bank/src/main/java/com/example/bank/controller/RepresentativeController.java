package com.example.bank.controller;

import com.example.bank.model.dto.RepresentativeDto;
import com.example.bank.service.IrepresentativeService;
import com.example.bank.util.Constants;
import io.reactivex.rxjava3.core.Single;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * . Class RepresentativeController
 *
 * @author Andres Guizado
 * @version 0.1, 2023/10/27
 */
@RestController
@Log4j2
@RequiredArgsConstructor
public class RepresentativeController {
  
  @Autowired
  IrepresentativeService representativeService;
  
  /**
   * . This method is to save Representative
   *
   * @param representative This is the first parameter
   * @return a HTTP Status
   */
  @PostMapping("/representative")
  public Single<ResponseEntity<RepresentativeDto>> createRepresentative(
      @RequestBody RepresentativeDto representative) {
    return representativeService.createRepresentative(representative)
        .map(ResponseEntity::ok)
        .doOnError(throwable -> log.error(Constants.DO_ON_ERROR, throwable))
        .doOnSuccess(response -> log.info(Constants.DO_ON_SUCCESS, response));
  }

  /**
   * . This method is to get Representative
   *
   * @param representativeId This is the first parameter
   * @return a HTTP Status
   */
  @GetMapping("/representative/{representativeId}")
  public Single<ResponseEntity<RepresentativeDto>> getRepresentative(
      @PathVariable("representativeId") Integer representativeId) {
    return representativeService.getRepresentative(representativeId)
        .map(ResponseEntity::ok)
        .doOnError(throwable -> log.error(Constants.DO_ON_ERROR, throwable))
        .doOnSuccess(response -> log.info(Constants.DO_ON_SUCCESS, response));
  }

  /**
   * . This method is to update Representative
   *
   * @param representativeId This is the first parameter
   * @param representative   This is the second parameter
   * @return a HTTP Status
   */
  @PutMapping("/representative/{representativeId}")
  public Single<ResponseEntity<RepresentativeDto>> editRepresentative(
      @PathVariable("representativeId") Integer representativeId,
      @RequestBody RepresentativeDto representative) {
    return representativeService.editRepresentative(representative)
        .map(ResponseEntity::ok)
        .doOnError(throwable -> log.error(Constants.DO_ON_ERROR, throwable))
        .doOnSuccess(response -> log.info(Constants.DO_ON_SUCCESS, response));
  }

  /**
   * . This method is to delete Representative
   *
   * @param representativeId This is the first parameter
   * @return a HTTP Status
   */
  @DeleteMapping("/representative/{representativeId}")
  public Single<ResponseEntity<RepresentativeDto>> deleteRepresentative(
      @PathVariable("representativeId") Integer representativeId) {
    return representativeService.deleteRepresentative(representativeId)
        .map(ResponseEntity::ok)
        .doOnError(throwable -> log.error(Constants.DO_ON_ERROR, throwable))
        .doOnSuccess(response -> log.info(Constants.DO_ON_SUCCESS, response));
  }

}
