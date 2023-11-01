package com.example.bank.service;

import com.example.bank.model.dto.CardDto;
import io.reactivex.rxjava3.core.Single;

/**
 * . Interface IcardService
 *
 * @author Andres Guizado
 * @version 0.1, 2023/10/26
 */
public interface IcardService {
  
  public Single<CardDto> createCard(CardDto card);
  
  public Single<CardDto> getCard(Integer cardId);
  
  public Single<CardDto> editCard(Integer cardId, CardDto card);

}
