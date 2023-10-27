package com.example.bank.model.mapper;

import com.example.bank.model.OperationTypeModel;
import com.example.bank.model.dto.OperationTypeDto;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * . Class OperationTypeMapper
 *
 * @author Andres Guizado
 * @version 0.1, 2023/10/26
 */
@Mapper(componentModel = "spring")
public interface OperationTypeMapper {
  
  OperationTypeMapper INSTANCE = Mappers.getMapper(OperationTypeMapper.class);
  
  OperationTypeModel toOperationType(OperationTypeDto operationTypeDto);
  
  List<OperationTypeModel> toOperationTypes(List<OperationTypeDto> operationTypeDto);
  
  OperationTypeDto toEntity(OperationTypeModel operationTypeModel);
  
  List<OperationTypeDto> toEntityList(List<OperationTypeModel> operationTypeModel);

}
