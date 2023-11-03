package com.example.bank.service.impl;

import com.example.bank.model.CustomerProductModel;
import com.example.bank.model.CustomerTypeModel.DescriptionEnum;
import com.example.bank.model.OperationModel;
import com.example.bank.model.ProductModel;
import com.example.bank.model.ProductTypeModel;
import com.example.bank.model.dto.CustomerProductDto;
import com.example.bank.model.dto.OperationDto;
import com.example.bank.model.mapper.CustomerProductMapper;
import com.example.bank.model.mapper.OperationMapper;
import com.example.bank.repository.CustomerProductRepository;
import com.example.bank.service.IcustomerProductService;
import com.example.bank.util.Constants;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
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
  private final OperationMapper operationMapper;

  @Override
  public Single<CustomerProductDto> createCustomerProduct(CustomerProductDto customerProductDto) {
    
    CustomerProductModel customerProductModel = customerProductMapper
        .toCustomerProduct(customerProductDto);
    
    Mono<CustomerProductModel> cpMono = customerProductRepository.findByCustomerId(
        customerProductDto.getCustomer().getId())
        .collectList()
        .flatMap(x -> validateToSave(customerProductModel, x)); 
    
    return RxJava3Adapter.monoToSingle(cpMono.map(customerProductMapper::toEntity));
  }

  /**
   * . This method is to set Data Customer Product
   *
   * @param customerProductModel This is the first parameter
   * @param x This is the second parameter
   * @return Mono value
   */
  private Mono<? extends CustomerProductModel> validateToSave(
      CustomerProductModel customerProductModel, List<CustomerProductModel> x) {
    boolean isPersonal = DescriptionEnum.PERSONAL.equals(
        customerProductModel.getCustomer().getTypeCustomer().getDescription());
    boolean isBusiness = DescriptionEnum.BUSSINESS.equals(
        customerProductModel.getCustomer().getTypeCustomer().getDescription());
    boolean isCurrent = com.example.bank.model.ProductTypeModel.DescriptionEnum.CURRENT
        .equals(customerProductModel.getProduct().getTypeProduct().getDescription());
    boolean isAccount = com.example.bank.model.ProductModel.DescriptionEnum.ACCOUNT.equals(
        customerProductModel.getProduct().getDescription());
    boolean isCredit = com.example.bank.model.ProductModel.DescriptionEnum.CREDIT.equals(
        customerProductModel.getProduct().getDescription());
    if (x.isEmpty() && (isPersonal || (isBusiness && (isCurrent || isCredit)))) {
      setDataCustomerProduct(customerProductModel);
      return customerProductRepository.save(customerProductModel);
    } else {
      for (CustomerProductModel bd : x) {
        if ((isPersonal && !(com.example.bank.model.ProductModel.DescriptionEnum.ACCOUNT
            .equals(bd.getProduct().getDescription()) && isAccount))
            || (isBusiness && (isCurrent || isCredit))) {
          setDataCustomerProduct(customerProductModel);
          return customerProductRepository.save(customerProductModel);
        }
      }
    }
    return Mono.error(new Exception(Constants.ERROR_UNEXPECTED));
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
      customerProductModel.setRepresentative(null);
      customerProductModel.setCreditLimit(null);
    } else if (DescriptionEnum.BUSSINESS.equals(customerProductModel.getCustomer()
        .getTypeCustomer().getDescription())) {
      setDataTypeProduct(5, 0, 0, product);
      customerProductModel.setAmount(customerProductModel.getCreditLimit());
    }
    
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat(Constants.FORMAT_DATE_COMPLETE);
    String date = simpleDateFormat.format(new Date());
    
    customerProductModel.setNumberTransactionLimit(0);
    customerProductModel.setCreationDate(date);
    customerProductModel.setModificationDate(date);
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
  public Observable<CustomerProductDto> getBalance(Integer customerId) {
    Flux<CustomerProductModel> cpFlux = customerProductRepository.findByCustomerId(customerId);
    return RxJava3Adapter.fluxToObservable(cpFlux.map(customerProductMapper::toEntity));
  }

  @Override
  public Observable<CustomerProductDto> getProducts(Integer customerId) {
    Flux<CustomerProductModel> cpFlux = customerProductRepository.findByCustomerId(customerId);
    return RxJava3Adapter.fluxToObservable(cpFlux.map(customerProductMapper::toEntity));
  }

  @Override
  public Single<CustomerProductDto> validateToUpdate(OperationDto operationDto) {
    
    OperationModel operationModel = operationMapper.toOperation(operationDto);
    Mono<CustomerProductModel> cpMono = Mono.empty();
     
    if (com.example.bank.model.ProductModel.DescriptionEnum.ACCOUNT.equals(
        operationModel.getCustomerProducto().getProduct().getDescription())) {
      cpMono = validateAccount(operationModel);
    } else if (com.example.bank.model.ProductModel.DescriptionEnum.CREDIT.equals(
        operationModel.getCustomerProducto().getProduct().getDescription())) {
      cpMono = validateCredit(operationModel);
    }
    
    return RxJava3Adapter.monoToSingle(cpMono.map(customerProductMapper::toEntity));
  }
  
  /**
   * . This method is to validate Account to save Customer Product
   *
   * @param operationModel This is the first parameter
   * @return Mono value
   */
  private Mono<CustomerProductModel> validateAccount(OperationModel operationModel) {
    CustomerProductModel customerProduct = operationModel.getCustomerProducto();
    ProductModel product = customerProduct.getProduct();

    if (com.example.bank.model.ProductTypeModel.DescriptionEnum.FIXED_TERM.equals(
        product.getTypeProduct().getDescription())) {
      SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd");
      String strToday = simpleDateFormat.format(new Date());
      Integer today = Integer.parseInt(strToday);
      Integer transactionDay = product.getTypeProduct().getTransactionDay();
      if (!today.equals(transactionDay)) {
        return Mono.error(new Exception(Constants.ERROR_DAY_NOT_ALLOWED));
      }
    }
    
    Integer nroTransaction = customerProduct.getNumberTransactionLimit();
    Integer nroTransactionLimit = product.getTypeProduct().getMonthlyTransactionLimit();
    
    if (nroTransactionLimit != 0 && nroTransaction >= nroTransactionLimit) {
      return Mono.error(new Exception(Constants.ERROR_LIMIT_TRANSACTION));
    }
    
    customerProduct.setNumberTransactionLimit(nroTransaction + 1);
    Integer amount = customerProduct.getAmount();
    Integer newAmount = 0;
    
    if (com.example.bank.model.OperationTypeModel.DescriptionEnum.DEPOSIT.equals(
        operationModel.getTypeOperation().getDescription())) {
      newAmount = amount + operationModel.getBalance();
    } else if (com.example.bank.model.OperationTypeModel.DescriptionEnum.WITHDRAWAL.equals(
        operationModel.getTypeOperation().getDescription())) {
      if (amount < operationModel.getBalance()) {
        return Mono.error(new Exception(Constants.ERROR_INSUFFICIENT_BALANCE));
      }
      newAmount = amount - operationModel.getBalance();
    }
    customerProduct.setAmount(newAmount);
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat(Constants.FORMAT_DATE_COMPLETE);
    String date = simpleDateFormat.format(new Date());
    customerProduct.setModificationDate(date);
    operationModel.setCustomerProducto(customerProduct);
    
    log.info("Updating Balance in Customer Product");
    return customerProductRepository.save(customerProduct);
  }
  
  /**
   * . This method is to validate Credit to save Customer Product
   *
   * @param operationModel This is the first parameter
   * @return Mono value
   */
  private Mono<CustomerProductModel> validateCredit(OperationModel operationModel) {    
    CustomerProductModel customerProduct = operationModel.getCustomerProducto();
    Integer creditLimit = customerProduct.getCreditLimit();
    Integer amount = customerProduct.getAmount();
    Integer balance = operationModel.getBalance();
    Integer newAmount = 0;
    
    if (com.example.bank.model.OperationTypeModel.DescriptionEnum.PAYMENT.equals(
        operationModel.getTypeOperation().getDescription())) {
      if ((creditLimit - amount) < balance) {
        return Mono.error(new Exception(Constants.ERROR_PAGING_MORE));
      }
      newAmount = amount + balance;
    } else if (com.example.bank.model.OperationTypeModel.DescriptionEnum.CONSUMPTION.equals(
        operationModel.getTypeOperation().getDescription())) {
      if (amount <= balance) {
        return Mono.error(new Exception(Constants.ERROR_INSUFFICIENT_CREDIT));
      }
      newAmount = amount - balance;
    }
    customerProduct.setAmount(newAmount);
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat(Constants.FORMAT_DATE_COMPLETE);
    String date = simpleDateFormat.format(new Date());
    customerProduct.setModificationDate(date);
    operationModel.setCustomerProducto(customerProduct);
    
    log.info("Updating Balance in Customer Product");
    return customerProductRepository.save(customerProduct);
  }

}
