package com.example.bank.service;

import com.example.bank.model.dto.RepresentativeDto;
import java.util.Optional;

/**
 * . Interface Irepresentative
 *
 * @author Andres Guizado
 * @version 0.1, 2023/10/27
 */
public interface IrepresentativeService {
  
  public RepresentativeDto createRepresentative(RepresentativeDto representative);
  
  public Optional<RepresentativeDto> getRepresentative(Integer representativeId);
  
  public RepresentativeDto editRepresentative(RepresentativeDto representative);
  
  void deleteRepresentative(Integer representativeId);

}
