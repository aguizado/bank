package com.example.bank.service.impl;

import com.example.bank.model.RepresentativeModel;
import com.example.bank.model.dto.RepresentativeDto;
import com.example.bank.model.mapper.RepresentativeMapper;
import com.example.bank.repository.RepresentativeRepository;
import com.example.bank.service.IrepresentativeService;
import io.reactivex.rxjava3.core.Single;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.adapter.rxjava.RxJava3Adapter;
import reactor.core.publisher.Mono;

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
  public Single<RepresentativeDto> createRepresentative(RepresentativeDto representative) {
    RepresentativeModel representativeModel = representativeMapper
        .toRepresentative(representative);
    Mono<RepresentativeModel> representativeMono = representativeRepository
        .save(representativeModel);
    return RxJava3Adapter.monoToSingle(representativeMono.map(representativeMapper::toEntity));
  }

  @Override
  public Single<RepresentativeDto> getRepresentative(Integer representativeId) {
    Mono<RepresentativeModel> representativeMono = representativeRepository
        .findById(representativeId);
    return RxJava3Adapter.monoToSingle(representativeMono.map(representativeMapper::toEntity));
  }

  @Override
  public Single<RepresentativeDto> editRepresentative(RepresentativeDto representative) {
    return createRepresentative(representative);
  }

  @Override
  public Single<RepresentativeDto> deleteRepresentative(Integer representativeId) {
    Mono<RepresentativeModel> representativeMono = representativeRepository
        .findById(representativeId)
        .flatMap(existingRepres -> representativeRepository.delete(existingRepres)
        .then(Mono.just(existingRepres)));
    return RxJava3Adapter.monoToSingle(representativeMono.map(representativeMapper::toEntity));
  }

}
