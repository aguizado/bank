package com.example.bank.controller;

import com.example.bank.model.dto.TransferDto;
import com.example.bank.service.IcustomerProductService;
import com.example.bank.service.ItransferService;
import com.example.bank.util.Constants;
import io.reactivex.rxjava3.core.Single;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * . Class TransferController
 *
 * @author Andres Guizado
 * @version 0.1, 2023/11/05
 */
@RestController
@Log4j2
@RequiredArgsConstructor
public class TransferController {
  
  @Autowired
  ItransferService transferService;
  
  @Autowired
  IcustomerProductService customerProductService;
  
  /**
   * . This method is to save Transfer
   *
   * @param transfer This is the first parameter
   * @return a HTTP Status
   */
  @PostMapping("/transfer")
  public Single<ResponseEntity<TransferDto>> createTransfer(@RequestBody TransferDto transfer) {
    
    customerProductService
        .getProducts(transfer.getCustomerDestination())
        .forEach(x -> {
          if (x.getProductNumber().equals(transfer.getProductNumber())) {
            if (transfer.getCustomerOrigin() == transfer.getCustomerDestination()) {
              log.info("Es una Transferencia Propia");
            } else {
              log.info("Es una Transferencia a Terceros");
            }
            BigDecimal newAmount = new BigDecimal(0);
            newAmount = x.getAmount().add(transfer.getAmount());
            x.setAmount(newAmount);            
            customerProductService.validateToUpdateTransfer(x);
          } else {
            log.info("No existe cuenta");
          }
        });
    
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat(Constants.FORMAT_DATE_COMPLETE);
    String date = simpleDateFormat.format(new Date());
    transfer.setTransferDate(date);
    
    return transferService.createTransfer(transfer)
        .map(ResponseEntity::ok)
        .doOnError(throwable -> log.error(Constants.DO_ON_ERROR, throwable))
        .doOnSuccess(response -> log.info(Constants.DO_ON_SUCCESS, response));
  }

}
