package com.example.bank.model.mapper;

import com.example.bank.model.CustomerModel;
import com.example.bank.model.dto.CustomerDto;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * . Class CustomerMapper
 *
 * @author Andres Guizado
 * @version 0.1, 2023/10/25
 */
@Mapper(componentModel = "spring")
public interface CustomerMapper {
  
  CustomerMapper INSTANCE = Mappers.getMapper(CustomerMapper.class);
  
  CustomerModel toCustomer(CustomerDto customerDto);
  
  List<CustomerModel> toCustomers(List<CustomerDto> customerDto);
  
  CustomerDto toEntity(CustomerModel customerModel);
  
  List<CustomerDto> toEntityList(List<CustomerModel> customerModel);

}
