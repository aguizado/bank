package com.example.bank.service.impl;

import com.example.bank.model.RepresentativeTypeModel;
import com.example.bank.model.dto.RepresentativeTypeDto;
import com.example.bank.model.mapper.RepresentativeTypeMapper;
import com.example.bank.repository.RepresentativeTypeRepository;
import com.example.bank.service.IrepresentativeTypeService;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * . Class RepresentativeTypeImpl
 *
 * @author Andres Guizado
 * @version 0.1, 2023/10/27
 */
@Service
@RequiredArgsConstructor
public class RepresentativeTypeServiceImpl implements IrepresentativeTypeService {
  
  private final RepresentativeTypeRepository representativeTypeRepository;
  
  private final RepresentativeTypeMapper representativeTypeMapper;
  
  @Override
  public RepresentativeTypeDto createRepresentativeType(RepresentativeTypeDto representativeType) {
    RepresentativeTypeModel representativeTypeModel = representativeTypeMapper
        .toRepresentativeType(representativeType);
    return representativeTypeMapper.INSTANCE
        .toEntity(representativeTypeRepository.save(representativeTypeModel));
  }

  @Override
  public List<RepresentativeTypeDto> getRepresentativeTypes() {
    return representativeTypeMapper.toEntityList(representativeTypeRepository.findAll());
  }

  @Override
  public Optional<RepresentativeTypeDto> getRepresentativeType(Integer representativeTypeId) {
    return Optional.of(representativeTypeMapper.toEntity(representativeTypeRepository
        .findById(representativeTypeId).get()));
  }

  @Override
  public RepresentativeTypeDto editRepresentativeType(RepresentativeTypeDto representativeType) {
    return createRepresentativeType(representativeType);
  }

}
