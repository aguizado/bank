package com.example.bank.service.impl;

import com.example.bank.model.TransferModel;
import com.example.bank.model.dto.TransferDto;
import com.example.bank.model.mapper.TransferMapper;
import com.example.bank.repository.TransferRepository;
import com.example.bank.service.ItransferService;
import io.reactivex.rxjava3.core.Single;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.adapter.rxjava.RxJava3Adapter;
import reactor.core.publisher.Mono;

/**
 * . Class TransferServiceImpl
 *
 * @author Andres Guizado
 * @version 0.1, 2023/11/05
 */
@Service
@RequiredArgsConstructor
public class TransferServiceImpl implements ItransferService {
  
  private final TransferRepository transferRepository;
  
  private final TransferMapper transferMapper;

  @Override
  public Single<TransferDto> createTransfer(TransferDto transfer) {
    TransferModel transferModel = transferMapper.toTransfer(transfer);
    Mono<TransferModel> transferMono = transferRepository.save(transferModel);
    return RxJava3Adapter.monoToSingle(transferMono.map(transferMapper::toEntity));
  }

}
