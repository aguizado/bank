package com.example.bank.service.impl;

import com.example.bank.model.CardModel;
import com.example.bank.model.dto.CardDto;
import com.example.bank.model.mapper.CardMapper;
import com.example.bank.repository.CardRepository;
import com.example.bank.service.IcardService;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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
  public CardDto createCard(CardDto card) {
    CardModel cardModel = cardMapper.toCard(card);
    return cardMapper.INSTANCE.toEntity(cardRepository.save(cardModel));
  }

  @Override
  public Optional<CardDto> getCard(Integer cardId) {
    return Optional.of(cardMapper.toEntity(cardRepository.findById(cardId).get()));
  }

  @Override
  public CardDto editCard(CardDto card) {
    return createCard(card);
  }

}
