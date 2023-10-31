package com.example.bank.service;

import com.example.bank.model.dto.RepresentativeDto;
import io.reactivex.rxjava3.core.Single;

/**
 * . Interface Irepresentative
 *
 * @author Andres Guizado
 * @version 0.1, 2023/10/27
 */
public interface IrepresentativeService {
  
  public Single<RepresentativeDto> createRepresentative(RepresentativeDto representative);
  
  public Single<RepresentativeDto> getRepresentative(Integer representativeId);
  
  public Single<RepresentativeDto> editRepresentative(RepresentativeDto representative);
  
  public Single<RepresentativeDto> deleteRepresentative(Integer representativeId);

}
