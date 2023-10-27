package com.example.bank.controller;

import com.example.bank.model.dto.RepresentativeDto;
import com.example.bank.model.dto.RepresentativeTypeDto;
import com.example.bank.service.IrepresentativeService;
import com.example.bank.service.IrepresentativeTypeService;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
@RequiredArgsConstructor
public class RepresentativeController {
  
  @Autowired
  IrepresentativeService representativeService;
  
  @Autowired
  IrepresentativeTypeService representativeTypeService;
  
  /**
   * . This method is to save Representative
   *
   * @param representative This is the first parameter
   * @return a HTTP Status
   */
  @PostMapping("/representative")
  public ResponseEntity<RepresentativeDto> createRepresentative(
      @RequestBody RepresentativeDto representative) {
    Optional<RepresentativeTypeDto> opRepresentativeType = representativeTypeService
        .getRepresentativeType(representative.getTypeRepresentative().getId());
    if (opRepresentativeType.isPresent()) {
      RepresentativeDto representativeDto = representativeService
          .createRepresentative(representative);
      return new ResponseEntity<>(representativeDto, HttpStatus.CREATED);
    }
    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
  }

  /**
   * . This method is to get Representative
   *
   * @param representativeId This is the first parameter
   * @return a HTTP Status
   */
  @GetMapping("/representative/{representativeId}")
  public ResponseEntity<RepresentativeDto> getRepresentative(
      @PathVariable("representativeId") Integer representativeId) {
    Optional<RepresentativeDto> opRepresentative = representativeService
        .getRepresentative(representativeId);
    if (opRepresentative.isPresent()) {
      return ResponseEntity.status(HttpStatus.OK).body(opRepresentative.get());
    }
    return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
  }

  /**
   * . This method is to update Representative
   *
   * @param representativeId This is the first parameter
   * @param representative   This is the second parameter
   * @return a HTTP Status
   */
  @PutMapping("/representative/{representativeId}")
  public ResponseEntity<RepresentativeDto> editRepresentative(
      @PathVariable("representativeId") Integer representativeId,
      @RequestBody RepresentativeDto representative) {
    Optional<RepresentativeDto> opRepresentative = representativeService
        .getRepresentative(representativeId);
    if (opRepresentative.isPresent()) {
      return new ResponseEntity<>(representativeService
          .editRepresentative(representative), HttpStatus.OK);
    }
    return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
  }

  /**
   * . This method is to delete Representative
   *
   * @param representativeId This is the first parameter
   * @return a HTTP Status
   */
  @DeleteMapping("/representative/{representativeId}")
  public ResponseEntity<HttpStatus> deleteRepresentative(
      @PathVariable("representativeId") Integer representativeId) {
    try {
      representativeService.deleteRepresentative(representativeId);
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

}
