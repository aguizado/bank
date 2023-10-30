package com.example.bank.service.impl;

import com.example.bank.model.CardModel;
import com.example.bank.model.dto.CardDto;
import com.example.bank.model.mapper.CardMapper;
import com.example.bank.repository.CardRepository;
import com.example.bank.service.IcardService;
import io.reactivex.rxjava3.core.Single;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.adapter.rxjava.RxJava3Adapter;
import reactor.core.publisher.Mono;

/**
 * . Class CardServiceImpl
 *
 * @author Andres Guizado
 * @version 0.1, 2023/10/26
 */
@Service
@RequiredArgsConstructor
public class CardServiceImpl implements IcardService {
  
  private final CardRepository cardRepository;
  
  private final CardMapper cardMapper;

  @Override
  public Single<CardDto> createCard(CardDto card) {
    CardModel cardModel = cardMapper.toCard(card);
    Mono<CardModel> cardMono = cardRepository.save(cardModel);
    return RxJava3Adapter.monoToSingle(cardMono.map(cardMapper::toEntity));
    
  }

  @Override
  public Single<CardDto> getCard(Integer cardId) {
    Mono<CardModel> cardMono = cardRepository.findById(cardId);
    return RxJava3Adapter.monoToSingle(cardMono.map(cardMapper::toEntity));
  }
  
  @Override
  public Single<CardDto> editCard(CardDto card) {
    return createCard(card);
  }

}
