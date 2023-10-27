package com.example.bank.service.impl;

import com.example.bank.model.ProductModel;
import com.example.bank.model.dto.ProductDto;
import com.example.bank.model.mapper.ProductMapper;
import com.example.bank.repository.ProductRepository;
import com.example.bank.service.IproductService;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * . Class ProductServiceImpl
 *
 * @author Andres Guizado
 * @version 0.1, 2023/10/27
 */
@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements IproductService {
  
  private final ProductRepository productRepository;
  
  private final ProductMapper productMapper;

  @Override
  public ProductDto createProduct(ProductDto product) {
    ProductModel productModel = productMapper.toProduct(product);
    return productMapper.INSTANCE.toEntity(
        productRepository.save(productModel));
  }

  @Override
  public List<ProductDto> getProducts() {
    return productMapper.toEntityList(productRepository.findAll());
  }

  @Override
  public Optional<ProductDto> getProduct(Integer productId) {
    return Optional.of(productMapper.toEntity(productRepository
        .findById(productId).get()));
  }

  @Override
  public ProductDto editProduct(ProductDto product) {
    return createProduct(product);
  }

}
