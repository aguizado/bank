package com.example.bank.model.mapper;

import com.example.bank.model.CustomerProductModel;
import com.example.bank.model.dto.CustomerProductDto;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * . Class CustomerProductMapper
 *
 * @author Andres Guizado
 * @version 0.1, 2023/10/27
 */
@Mapper(componentModel = "spring")
public interface CustomerProductMapper {
  
  CustomerProductMapper INSTANCE = Mappers.getMapper(CustomerProductMapper.class);
  
  CustomerProductModel toCustomerProduct(CustomerProductDto customerProductDto);
  
  List<CustomerProductModel> toCustomerProducts(List<CustomerProductDto> customerProductDto);
  
  CustomerProductDto toEntity(CustomerProductModel customerProductModel);
  
  List<CustomerProductDto> toEntityList(List<CustomerProductModel> customerProductModel);

}
