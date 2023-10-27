package com.example.bank.model.mapper;

import com.example.bank.model.CustomerTypeModel;
import com.example.bank.model.dto.CustomerTypeDto;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * . Class CustomerTypeMapper
 *
 * @author Andres Guizado
 * @version 0.1, 2023/10/27
 */
@Mapper(componentModel = "spring")
public interface CustomerTypeMapper {
  
  CustomerTypeMapper INSTANCE = Mappers.getMapper(CustomerTypeMapper.class);
  
  CustomerTypeModel toCustomerType(CustomerTypeDto customerTypeDto);
  
  List<CustomerTypeModel> toCustomerTypes(List<CustomerTypeDto> customerTypeDto);
  
  CustomerTypeDto toEntity(CustomerTypeModel customerTypeModel);
  
  List<CustomerTypeDto> toEntityList(List<CustomerTypeModel> customerTypeModel);

}
