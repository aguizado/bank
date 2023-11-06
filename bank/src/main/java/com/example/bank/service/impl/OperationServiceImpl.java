package com.example.bank.service.impl;

import com.example.bank.model.OperationModel;
import com.example.bank.model.dto.OperationDto;
import com.example.bank.model.mapper.OperationMapper;
import com.example.bank.repository.OperationRepository;
import com.example.bank.service.IoperationService;
import com.example.bank.util.Constants;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;
import java.text.SimpleDateFormat;
import java.util.Date;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.adapter.rxjava.RxJava3Adapter;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

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
  public Single<OperationDto> createOperation(OperationDto operationDto) {
    OperationModel operationModel = operationMapper.toOperation(operationDto);

    SimpleDateFormat simpleDateFormat = new SimpleDateFormat(Constants.FORMAT_DATE_COMPLETE);
    String date = simpleDateFormat.format(new Date());
    operationModel.setOperationDate(date);

    Mono<OperationModel> operationMono = operationRepository.save(operationModel);
    return RxJava3Adapter.monoToSingle(operationMono.map(operationMapper::toEntity));
  }

  @Override
  public Observable<OperationDto> getMovements(Integer customerId) {
    Flux<OperationModel> operationFlux = operationRepository
        .findByCustomerProductoCustomerId(customerId);
    return RxJava3Adapter.fluxToObservable(operationFlux.map(operationMapper::toEntity));
  }

  @Override
  public Single<OperationDto> getReportLastMovements(Integer customerId) {
    Mono<OperationModel> operationMono = operationRepository.findById(customerId);
    return RxJava3Adapter.monoToSingle(operationMono.map(operationMapper::toEntity));
  }

  @Override
  public Observable<OperationDto> getCommissions(Integer customerId) {
    Flux<OperationModel> operationFlux = operationRepository
        .findByCustomerProductoCustomerId(customerId);
    return RxJava3Adapter.fluxToObservable(operationFlux.map(operationMapper::toEntity));
  }

}
