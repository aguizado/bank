package com.example.bank.service;

import com.example.bank.model.dto.ProductTypeDto;
import java.util.List;
import java.util.Optional;

/**
 * . Interface IproductTypeService
 *
 * @author Andres Guizado
 * @version 0.1, 2023/10/27
 */
public interface IproductTypeService {
  
  public ProductTypeDto createProductType(ProductTypeDto productType);
  
  public List<ProductTypeDto> getProductTypes();
  
  public Optional<ProductTypeDto> getProductType(Integer productTypeId);
  
  public ProductTypeDto editProductType(ProductTypeDto productType);

}
