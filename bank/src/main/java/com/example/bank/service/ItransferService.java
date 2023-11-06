package com.example.bank.service;

import com.example.bank.model.dto.TransferDto;
import io.reactivex.rxjava3.core.Single;

/**
 * . Interface ItransferService
 *
 * @author Andres Guizado
 * @version 0.1, 2023/11/05
 */
public interface ItransferService {
  
  public Single<TransferDto> createTransfer(TransferDto transfer);

}
