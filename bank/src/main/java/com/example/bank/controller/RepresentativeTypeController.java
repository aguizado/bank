package com.example.bank.controller;

import com.example.bank.model.dto.RepresentativeTypeDto;
import com.example.bank.service.IrepresentativeTypeService;
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
 * . Class RepresentativeTypeController
 *
 * @author Andres Guizado
 * @version 0.1, 2023/10/27
 */
@RestController
@RequiredArgsConstructor
public class RepresentativeTypeController {
  
  @Autowired
  IrepresentativeTypeService representativeTypeService;
  
  /**
   * . This method is to save Representative Type
   *
   * @param representativeType This is the first parameter
   * @return a HTTP Status
   */
  @PostMapping("/representative_type")
  public Single<RepresentativeTypeDto> createRepresentativeType(
      @RequestBody RepresentativeTypeDto representativeType) {
    return representativeTypeService.createRepresentativeType(representativeType);
  }
  
  /**
   * . This method is to get Representative Type
   *
   * @return a HTTP Status
   */
  @GetMapping("/representative_type/getAll")
  public Observable<RepresentativeTypeDto> getRepresentativeTypes() { 
    return representativeTypeService.getRepresentativeTypes();
  }
  
  /**
   * . This method is to get Representative Type
   *
   * @param representativeTypeId This is the first parameter
   * @return a HTTP Status
   */
  @GetMapping("/representative_type/{representativeTypeId}")
  public Single<RepresentativeTypeDto> getRepresentativeType(
      @PathVariable("representativeTypeId") Integer representativeTypeId) {
    return representativeTypeService.getRepresentativeType(representativeTypeId);
  }
  
  /**
   * . This method is to update Representative Type
   *
   * @param representativeTypeId This is the first parameter
   * @param representativeType   This is the second parameter
   * @return a HTTP Status
   */
  @PutMapping("/representative_type/{representativeTypeId}")
  public Single<RepresentativeTypeDto> editRepresentativeType(
      @PathVariable("representativeTypeId") Integer representativeTypeId,
      @RequestBody RepresentativeTypeDto representativeType) {
    return representativeTypeService.editRepresentativeType(representativeType);
  }

}
