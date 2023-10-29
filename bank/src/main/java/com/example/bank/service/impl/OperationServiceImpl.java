package com.example.bank.service.impl;

import com.example.bank.model.OperationModel;
import com.example.bank.model.dto.OperationDto;
import com.example.bank.model.mapper.OperationMapper;
import com.example.bank.repository.OperationRepository;
import com.example.bank.service.IoperationService;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * . Class OperationServiceImpl
 *
 * @author Andres Guizado
 * @version 0.1, 2023/10/16
 */
@Service
@RequiredArgsConstructor
public class OperationServiceImpl implements IoperationService {
  
  private final OperationRepository operationRepository;
  
  private final OperationMapper operationMapper;
  
  @Override
  public OperationDto createOperation(OperationDto operationDto) {
    OperationModel operationModel = operationMapper
        .toOperation(operationDto);
    return operationMapper.INSTANCE.toEntity(
        operationRepository.save(operationModel));
  }

  @Override
  public Optional<OperationDto> getMovements(Integer customerId) {
    return Optional.of(operationMapper.toEntity(operationRepository
        .findById(customerId).get()));
  }

  @Override
  public Optional<OperationDto> getReportLastMovements(Integer customerId) {
    return Optional.of(operationMapper.toEntity(operationRepository
        .findById(customerId).get()));
  }

}
