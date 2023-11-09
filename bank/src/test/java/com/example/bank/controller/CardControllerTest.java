package com.example.bank.controller;

import static org.mockito.Mockito.when;

import com.example.bank.model.dto.CardDto;
import com.example.bank.service.impl.CardServiceImpl;
import io.reactivex.rxjava3.core.Single;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;

@ExtendWith(MockitoExtension.class)
class CardControllerTest {
  
  private WebTestClient webTestClient;
  
  @InjectMocks
  private CardController cardController;
  
  @Mock
  private CardServiceImpl cardServiceImpl;
  
  @BeforeEach
  void setUp() {
    webTestClient = WebTestClient.bindToController(cardController).build();
  }

  @Test
  @Disabled("In Progress")
  @DisplayName("Unit Test to Create Card")
  void testCreateCard() {
    
    CardDto card = new CardDto();
    card.setId(1);
    card.setNumber("1234567812345678");
    card.setIsDelete(false);
    
    when(cardServiceImpl.createCard(card)).thenReturn(Single.just(card));
    
    webTestClient.post()
        .uri("/card")
        .contentType(MediaType.APPLICATION_JSON)
        .bodyValue(card)
        .exchange()
        .expectStatus().isOk()
        .expectBody(CardDto.class)
        .isEqualTo(card);
  }

}
