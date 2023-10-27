package com.example.bank.service;

import com.example.bank.model.dto.RepresentativeTypeDto;
import java.util.List;
import java.util.Optional;

/**
 * . Interface IrepresentativeTypeService
 *
 * @author Andres Guizado
 * @version 0.1, 2023/10/27
 */
public interface IrepresentativeTypeService {
  
  public RepresentativeTypeDto createRepresentativeType(RepresentativeTypeDto representativeType);
  
  public List<RepresentativeTypeDto> getRepresentativeTypes();
  
  public Optional<RepresentativeTypeDto> getRepresentativeType(Integer representativeTypeId);
  
  public RepresentativeTypeDto editRepresentativeType(RepresentativeTypeDto representativeType);

}
