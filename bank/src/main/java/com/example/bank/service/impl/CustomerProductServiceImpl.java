package com.example.bank.service.impl;

import com.example.bank.model.CustomerModel;
import com.example.bank.model.CustomerProductModel;
import com.example.bank.model.CustomerTypeModel;
import com.example.bank.model.CustomerTypeModel.DescriptionEnum;
import com.example.bank.model.OperationModel;
import com.example.bank.model.ProductModel;
import com.example.bank.model.ProductTypeModel;
import com.example.bank.model.ProfileModel;
import com.example.bank.model.dto.CustomerProductDto;
import com.example.bank.model.dto.OperationDto;
import com.example.bank.model.mapper.CustomerProductMapper;
import com.example.bank.model.mapper.OperationMapper;
import com.example.bank.repository.CustomerProductRepository;
import com.example.bank.service.IcustomerProductService;
import com.example.bank.util.Constants;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;
import java.math.BigDecimal;
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
      setDataCustomerProduct(customerProductModel, isPersonal, isBusiness);
      return customerProductRepository.save(customerProductModel);
    } else {
      for (CustomerProductModel bd : x) {
        if ((isPersonal && !(com.example.bank.model.ProductModel.DescriptionEnum.ACCOUNT
            .equals(bd.getProduct().getDescription()) && isAccount))
            || (isBusiness && (isCurrent || isCredit))) {
          setDataCustomerProduct(customerProductModel, isPersonal, isBusiness);
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
  private void setDataCustomerProduct(CustomerProductModel customerProductModel,
      boolean isPersonal, boolean isBusiness) {
    ProductModel product = customerProductModel.getProduct();
    boolean isRegular = com.example.bank.model.ProfileModel.DescriptionEnum.REGULAR.equals(
        customerProductModel.getCustomer().getTypeCustomer().getTypeProfile().getDescription());
    boolean isVip = com.example.bank.model.ProfileModel.DescriptionEnum.VIP.equals(
        customerProductModel.getCustomer().getTypeCustomer().getTypeProfile().getDescription());
    boolean isPyme = com.example.bank.model.ProfileModel.DescriptionEnum.PYME.equals(
        customerProductModel.getCustomer().getTypeCustomer().getTypeProfile().getDescription());
    boolean isCredit = com.example.bank.model.ProductModel.DescriptionEnum.CREDIT.equals(
        customerProductModel.getProduct().getDescription());
    if (isPersonal && isRegular) {
      if (com.example.bank.model.ProductTypeModel.DescriptionEnum.SAVING.equals(
            customerProductModel.getProduct().getTypeProduct().getDescription())) {
        setDataTypeProduct(new BigDecimal("0"), 30, 0, product);
      } else if (com.example.bank.model.ProductTypeModel.DescriptionEnum.CURRENT.equals(
            customerProductModel.getProduct().getTypeProduct().getDescription())) {
        setDataTypeProduct(new BigDecimal("5"), 0, 0, product);
      } else if (com.example.bank.model.ProductTypeModel.DescriptionEnum.FIXED_TERM.equals(
            customerProductModel.getProduct().getTypeProduct().getDescription())) {
        setDataTypeProduct(new BigDecimal("0"), 1, 15, product);
      }
      customerProductModel.setRepresentative(null);
      customerProductModel.setCreditLimit(null);
    } else if (isPersonal && isVip
        && !(Constants.MINIMUM_MONTHLY_AMOUNT.compareTo(customerProductModel.getAmount()) == -1
        && isCredit)) {
      setDataProfile(customerProductModel, 1, DescriptionEnum.PERSONAL);      
    } else if (isBusiness && isRegular) {
      setDataTypeProduct(new BigDecimal("5"), 0, 0, product);
      customerProductModel.setAmount(customerProductModel.getCreditLimit());
    } else if (isBusiness && isPyme
        && !(com.example.bank.model.ProductTypeModel.DescriptionEnum.CURRENT.equals(
            customerProductModel.getProduct().getTypeProduct().getDescription())
            && isCredit)) {
      setDataProfile(customerProductModel, 3, DescriptionEnum.BUSSINESS);
    }    
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat(Constants.FORMAT_DATE_COMPLETE);
    String date = simpleDateFormat.format(new Date());    
    customerProductModel.setNumberTransactionLimit(0);
    customerProductModel.setCreationDate(date);
    customerProductModel.setModificationDate(date);
    customerProductModel.setProduct(product);
  }

  /**
   * . This method is to set Data Profile
   *
   * @param customerProductModel This is the first parameter
   * @param idCustomerType This is the second parameter
   * @param description This is the third parameter
   */
  private void setDataProfile(CustomerProductModel customerProductModel,
      Integer idCustomerType, DescriptionEnum description) {
    CustomerModel customer = customerProductModel.getCustomer();
    CustomerTypeModel customerType = customer.getTypeCustomer();
    ProfileModel profile = customerType.getTypeProfile();
    profile.setId(1);
    profile.setDescription(com.example.bank.model.ProfileModel.DescriptionEnum.REGULAR);
    customerType.setId(idCustomerType);
    customerType.setDescription(description);
    customerType.setTypeProfile(profile);
    customer.setTypeCustomer(customerType);
    customerProductModel.setCustomer(customer);
  }
  
  /**
   * . This method is to set Data Type Product
   *
   * @param fee This is the first parameter
   * @param limit This is the second parameter
   * @param day This is the third parameter
   * @param product This is the fourth parameter
   */
  private void setDataTypeProduct(BigDecimal fee, int limit, int day, ProductModel product) {
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
  public Single<CustomerProductDto> validateToUpdateOperation(OperationDto operationDto) {
    
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
    BigDecimal maintenanceFee = new BigDecimal("0.0");
    
    BigDecimal amount = customerProduct.getAmount();
    BigDecimal newAmount = new BigDecimal("0");
    
    if (nroTransactionLimit != 0 && nroTransaction >= nroTransactionLimit) {
      maintenanceFee = product.getTypeProduct().getMaintenanceFee().multiply(amount);
    }    
    customerProduct.setNumberTransactionLimit(nroTransaction + 1);
    operationModel.setCommission(maintenanceFee);
    
    if (com.example.bank.model.OperationTypeModel.DescriptionEnum.DEPOSIT.equals(
        operationModel.getTypeOperation().getDescription())) {
      newAmount = amount.add(operationModel.getBalance()).add(maintenanceFee);
    } else if (com.example.bank.model.OperationTypeModel.DescriptionEnum.WITHDRAWAL.equals(
        operationModel.getTypeOperation().getDescription())) {
      if (amount.compareTo(operationModel.getBalance()) < 0) {
        return Mono.error(new Exception(Constants.ERROR_INSUFFICIENT_BALANCE));
      }
      newAmount = amount.subtract(operationModel.getBalance()).add(maintenanceFee);
    }
    log.info("A commission of " + maintenanceFee + " was applied");
    
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
    BigDecimal creditLimit = customerProduct.getCreditLimit();
    BigDecimal amount = customerProduct.getAmount();
    BigDecimal balance = operationModel.getBalance();
    BigDecimal newAmount = new BigDecimal("0");
    
    if (com.example.bank.model.OperationTypeModel.DescriptionEnum.PAYMENT.equals(
        operationModel.getTypeOperation().getDescription())) {
      if ((creditLimit.subtract(amount)).compareTo(balance) < 0) {
        return Mono.error(new Exception(Constants.ERROR_PAGING_MORE));
      }
      newAmount = amount.add(balance);
    } else if (com.example.bank.model.OperationTypeModel.DescriptionEnum.CONSUMPTION.equals(
        operationModel.getTypeOperation().getDescription())) {
      if (amount.compareTo(balance) < 0) {
        return Mono.error(new Exception(Constants.ERROR_INSUFFICIENT_CREDIT));
      }
      newAmount = amount.subtract(balance);
    }
    customerProduct.setAmount(newAmount);
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat(Constants.FORMAT_DATE_COMPLETE);
    String date = simpleDateFormat.format(new Date());
    customerProduct.setModificationDate(date);
    operationModel.setCustomerProducto(customerProduct);
    
    log.info("Updating Balance in Customer Product");
    return customerProductRepository.save(customerProduct);
  }

  @Override
  public Single<CustomerProductDto> validateToUpdateTransfer(
      CustomerProductDto customerProductDto) {
    CustomerProductModel customerProduct = customerProductMapper
        .toCustomerProduct(customerProductDto);
    Mono<CustomerProductModel> cpMono = customerProductRepository.save(customerProduct);
    return RxJava3Adapter.monoToSingle(cpMono.map(customerProductMapper::toEntity));
  }

}
