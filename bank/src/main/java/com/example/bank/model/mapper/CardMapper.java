package com.example.bank.model.mapper;

import com.example.bank.model.CardModel;
import com.example.bank.model.dto.CardDto;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * . Class CardMapper
 *
 * @author Andres Guizado
 * @version 0.1, 2023/10/26
 */
@Mapper(componentModel = "spring")
public interface CardMapper {
  
  CardMapper INSTANCE = Mappers.getMapper(CardMapper.class);
  
  CardModel toCard(CardDto cardDto);
  
  List<CardModel> toCards(List<CardDto> cardDto);
  
  CardDto toEntity(CardModel cardModel);
  
  List<CardDto> toEntityList(List<CardModel> cardModel);
  
}
