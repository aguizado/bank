package com.example.bank.controller;

import com.example.bank.model.dto.RepresentativeTypeDto;
import com.example.bank.service.IrepresentativeTypeService;
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
  public ResponseEntity<RepresentativeTypeDto> createRepresentativeType(
      @RequestBody RepresentativeTypeDto representativeType) {
    try {
      RepresentativeTypeDto representativeTypeDto = representativeTypeService
          .createRepresentativeType(representativeType);
      return new ResponseEntity<>(representativeTypeDto, HttpStatus.CREATED);
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }
  
  /**
   * . This method is to get Representative Type
   *
   * @return a HTTP Status
   */
  @GetMapping("/representative_type/getAll")
  public ResponseEntity<List<RepresentativeTypeDto>> getRepresentativeTypes() { 
    try {
      List<RepresentativeTypeDto> representativeTypeList = representativeTypeService
          .getRepresentativeTypes();
      return ResponseEntity.ok(representativeTypeList);
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
  }
  
  /**
   * . This method is to get Representative Type
   *
   * @param representativeTypeId This is the first parameter
   * @return a HTTP Status
   */
  @GetMapping("/representative_type/{representativeTypeId}")
  public ResponseEntity<RepresentativeTypeDto> getRepresentativeType(
      @PathVariable("representativeTypeId") Integer representativeTypeId) {
    Optional<RepresentativeTypeDto> opRepresentativeType = representativeTypeService
        .getRepresentativeType(representativeTypeId);
    if (opRepresentativeType.isPresent()) {
      return ResponseEntity.status(HttpStatus.OK).body(opRepresentativeType.get());
    }
    return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
  }
  
  /**
   * . This method is to update Representative Type
   *
   * @param representativeTypeId This is the first parameter
   * @param representativeType   This is the second parameter
   * @return a HTTP Status
   */
  @PutMapping("/representative_type/{representativeTypeId}")
  public ResponseEntity<RepresentativeTypeDto> editRepresentativeType(
      @PathVariable("representativeTypeId") Integer representativeTypeId,
      @RequestBody RepresentativeTypeDto representativeType) {
    Optional<RepresentativeTypeDto> opRepresentativeType = representativeTypeService
        .getRepresentativeType(representativeTypeId);
    if (opRepresentativeType.isPresent()) {
      return new ResponseEntity<>(representativeTypeService
          .editRepresentativeType(representativeType), HttpStatus.OK);
    }
    return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
  }

}
