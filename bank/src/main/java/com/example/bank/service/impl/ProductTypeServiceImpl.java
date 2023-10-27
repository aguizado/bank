package com.example.bank.service.impl;

import com.example.bank.model.ProductTypeModel;
import com.example.bank.model.dto.ProductTypeDto;
import com.example.bank.model.mapper.ProductTypeMapper;
import com.example.bank.repository.ProductTypeRepository;
import com.example.bank.service.IproductTypeService;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * . Class ProductTypeServiceImpl
 *
 * @author Andres Guizado
 * @version 0.1, 2023/10/27
 */
@Service
@RequiredArgsConstructor
public class ProductTypeServiceImpl implements IproductTypeService {
  
  private final ProductTypeRepository productTypeRepository;
  
  private final ProductTypeMapper productTypeMapper;
  
  @Override
  public ProductTypeDto createProductType(ProductTypeDto productType) {
    ProductTypeModel productTypeModel = productTypeMapper.toProductType(productType);
    return productTypeMapper.INSTANCE.toEntity(
        productTypeRepository.save(productTypeModel));
  }
  
  @Override
  public List<ProductTypeDto> getProductTypes() {
    return productTypeMapper.toEntityList(productTypeRepository.findAll());
  }

  @Override
  public Optional<ProductTypeDto> getProductType(Integer productTypeId) {
    return Optional.of(productTypeMapper.toEntity(productTypeRepository
        .findById(productTypeId).get()));
  }

  @Override
  public ProductTypeDto editProductType(ProductTypeDto productType) {
    return createProductType(productType);
  }

}
