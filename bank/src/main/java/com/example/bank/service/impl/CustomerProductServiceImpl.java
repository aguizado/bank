package com.example.bank.service.impl;

import com.example.bank.model.CustomerProductModel;
import com.example.bank.model.CustomerTypeModel.DescriptionEnum;
import com.example.bank.model.ProductModel;
import com.example.bank.model.ProductTypeModel;
import com.example.bank.model.dto.CustomerProductDto;
import com.example.bank.model.mapper.CustomerProductMapper;
import com.example.bank.repository.CustomerProductRepository;
import com.example.bank.service.IcustomerProductService;
import com.example.bank.util.Constants;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;
import java.text.SimpleDateFormat;
import java.util.Date;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import reactor.adapter.rxjava.RxJava3Adapter;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * . Class ProductServiceImpl
 *
 * @author Andres Guizado
 * @version 0.1, 2023/10/27
 */
@Service
@Log4j2
@RequiredArgsConstructor
public class CustomerProductServiceImpl implements IcustomerProductService {
  
  private final CustomerProductRepository customerProductRepository;
  
  private final CustomerProductMapper customerProductMapper;

  @Override
  public Single<CustomerProductDto> createCustomerProduct(CustomerProductDto customerProductDto) {
    
    CustomerProductModel customerProductModel = customerProductMapper
        .toCustomerProduct(customerProductDto);
    
    Mono<CustomerProductModel> cpMono = customerProductRepository.findByCustomerId(
        customerProductDto.getCustomer().getId())
        .collectList()
        .flatMap(x -> {
          if (x.isEmpty()) {
            setDataCustomerProduct(customerProductModel);
            return customerProductRepository.save(customerProductModel);
          } else {
            for (CustomerProductModel bd : x) {
              if (!(com.example.bank.model.ProductModel.DescriptionEnum.ACCOUNT.equals(
                  customerProductModel.getProduct().getDescription())
                  && com.example.bank.model.ProductModel.DescriptionEnum.ACCOUNT.equals(
                      bd.getProduct().getDescription()))) {
                setDataCustomerProduct(customerProductModel);
                return customerProductRepository.save(customerProductModel);
              }
            }
          }
          return Mono.error(new Exception(Constants.ERROR_UNEXPECTED));
        }); 
    
    return RxJava3Adapter.monoToSingle(cpMono.map(customerProductMapper::toEntity));
  }
  
  /**
   * . This method is to set Data Customer Product
   *
   * @param customerProductModel This is the first parameter
   */
  private void setDataCustomerProduct(CustomerProductModel customerProductModel) {
    ProductModel product = customerProductModel.getProduct();
    if (DescriptionEnum.PERSONAL.equals(customerProductModel.getCustomer()
        .getTypeCustomer().getDescription())) {
      
      if (com.example.bank.model.ProductModel.DescriptionEnum.ACCOUNT.equals(
          customerProductModel.getProduct().getDescription())) {        
        if (com.example.bank.model.ProductTypeModel.DescriptionEnum.SAVING.equals(
            customerProductModel.getProduct().getTypeProduct().getDescription())) {
          setDataTypeProduct(0, 30, 0, product);          
        } else if (com.example.bank.model.ProductTypeModel.DescriptionEnum.CURRENT.equals(
            customerProductModel.getProduct().getTypeProduct().getDescription())) {
          setDataTypeProduct(5, 0, 0, product);          
        } else if (com.example.bank.model.ProductTypeModel.DescriptionEnum.FIXED_TERM.equals(
            customerProductModel.getProduct().getTypeProduct().getDescription())) {
          setDataTypeProduct(0, 1, 15, product);          
        }      
      } 
      
    } else if (DescriptionEnum.EMPRESARIAL.equals(customerProductModel.getCustomer()
        .getTypeCustomer().getDescription())) {
      log.error("Muy Pronto");
    } else {
      log.error(Constants.ERROR_NOT_EXIST_CLIENT_TYPE);
    }
    
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
    String date = simpleDateFormat.format(new Date());
    
    customerProductModel.setCreationDate(date);
    customerProductModel.setProduct(product);
  }
  
  /**
   * . This method is to set Data Type Product
   *
   * @param fee This is the first parameter
   * @param limit This is the second parameter
   * @param day This is the third parameter
   * @param product This is the fourth parameter
   */
  private void setDataTypeProduct(int fee, int limit, int day, ProductModel product) {
    ProductTypeModel productType = product.getTypeProduct();
    productType.setMaintenanceFee(fee);
    productType.setMonthlyTransactionLimit(limit);
    productType.setTransactionDay(day);
    product.setTypeProduct(productType);
  }  

  @Override
  public Single<CustomerProductDto> getBalance(Integer customerId) {
    Mono<CustomerProductModel> cpMono = customerProductRepository.findById(customerId);
    return RxJava3Adapter.monoToSingle(cpMono.map(customerProductMapper::toEntity));
  }

  @Override
  public Observable<CustomerProductDto> getProducts(Integer customerId) {
    Flux<CustomerProductModel> cpMono = customerProductRepository.findByCustomerId(customerId);
    return RxJava3Adapter.fluxToObservable(cpMono.map(customerProductMapper::toEntity));
  }

}
