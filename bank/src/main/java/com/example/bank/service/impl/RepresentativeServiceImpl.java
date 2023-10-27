package com.example.bank.service.impl;

import com.example.bank.model.RepresentativeModel;
import com.example.bank.model.dto.RepresentativeDto;
import com.example.bank.model.mapper.RepresentativeMapper;
import com.example.bank.repository.RepresentativeRepository;
import com.example.bank.service.IrepresentativeService;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * . Class RepresentativeServiceImpl
 *
 * @author Andres Guizado
 * @version 0.1, 2023/10/27
 */
@Service
@RequiredArgsConstructor
public class RepresentativeServiceImpl implements IrepresentativeService {
  
  private final RepresentativeRepository representativeRepository;
  
  private final RepresentativeMapper representativeMapper;
  
  @Override
  public RepresentativeDto createRepresentative(RepresentativeDto representative) {
    RepresentativeModel representativeModel = representativeMapper.toRepresentative(representative);
    return representativeMapper.INSTANCE.toEntity(
        representativeRepository.save(representativeModel));
  }

  @Override
  public Optional<RepresentativeDto> getRepresentative(Integer representativeId) {
    return Optional.of(representativeMapper.toEntity(representativeRepository
        .findById(representativeId).get()));
  }

  @Override
  public RepresentativeDto editRepresentative(RepresentativeDto representative) {
    return createRepresentative(representative);
  }

  @Override
  public void deleteRepresentative(Integer representativeId) {
    representativeRepository.deleteById(representativeId);
  }

}
