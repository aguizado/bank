package com.example.bank.model.mapper;

import com.example.bank.model.ProductTypeModel;
import com.example.bank.model.dto.ProductTypeDto;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * . Class ProductTypeMapper
 *
 * @author Andres Guizado
 * @version 0.1, 2023/10/27
 */
@Mapper(componentModel = "spring")
public interface ProductTypeMapper {
  
  ProductTypeMapper INSTANCE = Mappers.getMapper(ProductTypeMapper.class);
  
  ProductTypeModel toProductType(ProductTypeDto productTypeDto);
  
  List<ProductTypeModel> toProductTypes(List<ProductTypeDto> productTypeDto);
  
  ProductTypeDto toEntity(ProductTypeModel productTypeModel);
  
  List<ProductTypeDto> toEntityList(List<ProductTypeModel> productTypeModel);

}
