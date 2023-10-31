package com.example.bank.controller;

import com.example.bank.model.dto.CardDto;
import com.example.bank.service.IcardService;
import com.example.bank.util.Constants;
import io.reactivex.rxjava3.core.Single;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * . Class CardController
 *
 * @author Andres Guizado
 * @version 0.1, 2023/10/26
 */
@RestController
@Log4j2
@RequiredArgsConstructor
public class CardController {
  
  @Autowired
  IcardService cardService;
  
  /**
   * . This method is to save Card
   *
   * @param card This is the first parameter
   * @return a HTTP Status
   */
  @PostMapping("/card")
  public Single<ResponseEntity<CardDto>> createCard(@RequestBody CardDto card) {
    return cardService.createCard(card)
        .map(ResponseEntity::ok)
        .doOnError(throwable -> log.error(Constants.DO_ON_ERROR, throwable))
        .doOnSuccess(response -> log.info(Constants.DO_ON_SUCCESS, response));
  }
  
  /**
   * . This method is to get Card
   *
   * @param cardId This is the first parameter
   * @return a HTTP Status
   */
  @GetMapping("/card/{cardId}")
  public Single<ResponseEntity<CardDto>> getCard(@PathVariable("cardId") Integer cardId) {
    return cardService.getCard(cardId)
        .map(ResponseEntity::ok)
        .doOnError(throwable -> log.error(Constants.DO_ON_ERROR, throwable))
        .doOnSuccess(response -> log.info(Constants.DO_ON_SUCCESS, response));
  }
  
  /**
   * . This method is to update Card
   *
   * @param cardId This is the first parameter
   * @param card   This is the second parameter
   * @return a HTTP Status
   */  
  @PutMapping("/card/{cardId}")
  public Single<ResponseEntity<CardDto>> editCard(@PathVariable("cardId") Integer cardId,
      @RequestBody CardDto card) {
    return cardService.editCard(card)
        .map(ResponseEntity::ok)
        .doOnError(throwable -> log.error(Constants.DO_ON_ERROR, throwable))
        .doOnSuccess(response -> log.info(Constants.DO_ON_SUCCESS, response));
  }
  
}
