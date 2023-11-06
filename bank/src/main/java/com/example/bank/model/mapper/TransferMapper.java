package com.example.bank.model.mapper;

import com.example.bank.model.TransferModel;
import com.example.bank.model.dto.TransferDto;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * . Class TransferMapper
 *
 * @author Andres Guizado
 * @version 0.1, 2023/11/05
 */
@Mapper(componentModel = "spring")
public interface TransferMapper {
  
  TransferMapper INSTANCE = Mappers.getMapper(TransferMapper.class);
  
  TransferModel toTransfer(TransferDto transferDto);
  
  List<TransferModel> toTransfers(List<TransferDto> transferDto);
  
  TransferDto toEntity(TransferModel transferModel);
  
  List<TransferDto> toEntityList(List<TransferModel> transferModel);

}
