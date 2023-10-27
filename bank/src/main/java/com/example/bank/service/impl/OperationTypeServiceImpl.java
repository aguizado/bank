package com.example.bank.service.impl;

import com.example.bank.model.OperationTypeModel;
import com.example.bank.model.dto.OperationTypeDto;
import com.example.bank.model.mapper.OperationTypeMapper;
import com.example.bank.repository.OperationTypeRepository;
import com.example.bank.service.IoperationTypeService;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * . Class OperationTypeServiceImpl
 *
 * @author Andres Guizado
 * @version 0.1, 2023/10/26
 */
@Service
@RequiredArgsConstructor
public class OperationTypeServiceImpl implements IoperationTypeService {
  
  private final OperationTypeRepository operationTypeRepository;
  
  private final OperationTypeMapper operationTypeMapper;
  
  @Override
  public OperationTypeDto createOperationType(OperationTypeDto operationType) {
    OperationTypeModel operationTypeModel = operationTypeMapper.toOperationType(operationType);
    return operationTypeMapper.INSTANCE.toEntity(operationTypeRepository.save(operationTypeModel));
  }

  @Override
  public List<OperationTypeDto> getOperationTypes() {
    return operationTypeMapper.toEntityList(operationTypeRepository.findAll());
  }

  @Override
  public Optional<OperationTypeDto> getOperationType(Integer operationTypeId) {
    return Optional.of(operationTypeMapper.toEntity(operationTypeRepository
        .findById(operationTypeId).get()));
  }

  @Override
  public OperationTypeDto editOperationType(OperationTypeDto operationType) {
    return createOperationType(operationType);
  }

}
