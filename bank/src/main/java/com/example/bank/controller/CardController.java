package com.example.bank.controller;

import com.example.bank.model.dto.CardDto;
import com.example.bank.service.IcardService;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
  public ResponseEntity<CardDto> createCard(@RequestBody CardDto card) {
    try {
      CardDto cardDto = cardService.createCard(card);
      return new ResponseEntity<>(cardDto, HttpStatus.CREATED);
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }
  
  /**
   * . This method is to get Card
   *
   * @param cardId This is the first parameter
   * @return a HTTP Status
   */
  @GetMapping("/card/{cardId}")
  public ResponseEntity<CardDto> getCard(
      @PathVariable("cardId") Integer cardId) {
    Optional<CardDto> opCard = cardService.getCard(cardId);
    if (opCard.isPresent()) {
      return ResponseEntity.status(HttpStatus.OK).body(opCard.get());
    }
    return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
  }
  
  /**
   * . This method is to update Card
   *
   * @param cardId This is the first parameter
   * @param card   This is the second parameter
   * @return a HTTP Status
   */
  @PutMapping("/card/{cardId}")
  public ResponseEntity<CardDto> editCard(@PathVariable("cardId") Integer cardId,
      @RequestBody CardDto card) {
    Optional<CardDto> opAccount = cardService.getCard(cardId);
    if (opAccount.isPresent()) {
      return new ResponseEntity<>(cardService.editCard(card), HttpStatus.OK);
    }
    return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
  }

}
