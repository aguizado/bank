package com.example.bank.service;

import com.example.bank.model.dto.ProductDto;
import java.util.List;
import java.util.Optional;

/**
 * . Interface IproductService
 *
 * @author Andres Guizado
 * @version 0.1, 2023/10/27
 */
public interface IproductService {
  
  public ProductDto createProduct(ProductDto product);
  
  public List<ProductDto> getProducts();
  
  public Optional<ProductDto> getProduct(Integer productId);
  
  public ProductDto editProduct(ProductDto product);

}
