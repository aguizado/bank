package com.example.bank.service;

import com.example.bank.model.dto.RepresentativeTypeDto;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;

/**
 * . Interface IrepresentativeTypeService
 *
 * @author Andres Guizado
 * @version 0.1, 2023/10/27
 */
public interface IrepresentativeTypeService {
  
  public Single<RepresentativeTypeDto> createRepresentativeType(
      RepresentativeTypeDto representativeType);
  
  public Observable<RepresentativeTypeDto> getRepresentativeTypes();
  
  public Single<RepresentativeTypeDto> getRepresentativeType(Integer representativeTypeId);
  
  public Single<RepresentativeTypeDto> editRepresentativeType(
      RepresentativeTypeDto representativeType);

}
