package com.example.bank.model.mapper;

import com.example.bank.model.OperationModel;
import com.example.bank.model.dto.OperationDto;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * . Class OperationMapper
 *
 * @author Andres Guizado
 * @version 0.1, 2023/10/25
 */
@Mapper(componentModel = "spring")
public interface OperationMapper {
  
  OperationMapper INSTANCE = Mappers.getMapper(OperationMapper.class);
  
  OperationModel toOperation(OperationDto operationDto);
  
  List<OperationModel> toOperations(List<OperationDto> operationDto);
  
  OperationDto toEntity(OperationModel operationModel);
  
  List<OperationDto> toEntityList(List<OperationModel> operationModel);

}
