package com.example.bank.service.impl;

import static org.mockito.Mockito.when;

import com.example.bank.model.dto.CardDto;
import com.example.bank.repository.CardRepository;
import io.reactivex.rxjava3.core.Single;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class CardServiceImplTest {
  
  @InjectMocks
  private CardServiceImpl cardServiceImpl;
  
  @Mock
  private CardRepository cardRepository;

  @Test
  @Disabled("In Progress")
  @DisplayName("Unit Test to Create Card")
  void testCreateCard() {
    CardDto card = new CardDto();
    card.setId(1);
    card.setNumber("1234567812345678");
    card.setIsDelete(false);
    
    when(cardServiceImpl.createCard(card)).thenReturn(Single.just(card));
    
  }

}
