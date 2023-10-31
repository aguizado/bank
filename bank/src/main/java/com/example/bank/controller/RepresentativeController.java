package com.example.bank.controller;

import com.example.bank.model.dto.RepresentativeDto;
import com.example.bank.service.IrepresentativeService;
import io.reactivex.rxjava3.core.Single;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
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
  
  /**
   * . This method is to save Representative
   *
   * @param representative This is the first parameter
   * @return a HTTP Status
   */
  @PostMapping("/representative")
  public Single<RepresentativeDto> createRepresentative(
      @RequestBody RepresentativeDto representative) {
    return representativeService.createRepresentative(representative);
  }

  /**
   * . This method is to get Representative
   *
   * @param representativeId This is the first parameter
   * @return a HTTP Status
   */
  @GetMapping("/representative/{representativeId}")
  public Single<RepresentativeDto> getRepresentative(
      @PathVariable("representativeId") Integer representativeId) {
    return representativeService.getRepresentative(representativeId);
  }

  /**
   * . This method is to update Representative
   *
   * @param representativeId This is the first parameter
   * @param representative   This is the second parameter
   * @return a HTTP Status
   */
  @PutMapping("/representative/{representativeId}")
  public Single<RepresentativeDto> editRepresentative(
      @PathVariable("representativeId") Integer representativeId,
      @RequestBody RepresentativeDto representative) {
    return representativeService.editRepresentative(representative);
  }

  /**
   * . This method is to delete Representative
   *
   * @param representativeId This is the first parameter
   * @return a HTTP Status
   */
  @DeleteMapping("/representative/{representativeId}")
  public Single<RepresentativeDto> deleteRepresentative(
      @PathVariable("representativeId") Integer representativeId) {
    return representativeService.deleteRepresentative(representativeId);
  }

}
