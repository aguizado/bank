package com.example.bank.model.mapper;

import com.example.bank.model.OperationModel;
import com.example.bank.model.dto.OperationDto;
import java.util.List;
import org.mapstruct.Mapper;

/**
 * . Class OperationMapper
 *
 * @author Andres Guizado
 * @version 0.1, 2023/10/25
 */
@Mapper(componentModel = "spring")
public interface OperationMapper {
  
  OperationModel toOperation(OperationDto operationDto);
  
  List<OperationModel> toOperations(List<OperationDto> operationDto);
  
  OperationDto toEntity(OperationModel operationModel);

}
