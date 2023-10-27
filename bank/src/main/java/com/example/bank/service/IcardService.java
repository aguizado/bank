package com.example.bank.service;

import com.example.bank.model.dto.CardDto;
import java.util.Optional;

/**
 * . Interface IcardService
 *
 * @author Andres Guizado
 * @version 0.1, 2023/10/26
 */
public interface IcardService {
  
  public CardDto createCard(CardDto card);
  
  public Optional<CardDto> getCard(Integer cardId);
  
  public CardDto editCard(CardDto card);

}
