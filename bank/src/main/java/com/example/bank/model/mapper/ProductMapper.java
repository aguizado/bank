package com.example.bank.model.mapper;

import com.example.bank.model.ProductModel;
import com.example.bank.model.dto.ProductDto;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * . Class ProductMapper
 *
 * @author Andres Guizado
 * @version 0.1, 2023/10/27
 */
@Mapper(componentModel = "spring")
public interface ProductMapper {
  
  ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);
  
  ProductModel toProduct(ProductDto productDto);
  
  List<ProductModel> toProducts(List<ProductDto> productDto);
  
  ProductDto toEntity(ProductModel productModel);
  
  List<ProductDto> toEntityList(List<ProductModel> productModel);

}
