package com.example.bank.util;

import java.math.BigDecimal;

/**
 * . Class Constants
 *
 * @author Andres Guizado
 * @version 0.1, 2023/10/31
 */
public class Constants {
  
  private Constants() {
    throw new AssertionError();
  }
  
  public static final String DO_ON_ERROR = "do on Error";
  public static final String DO_ON_SUCCESS = "do on Success";
  
  public static final String ERROR_UNEXPECTED = "Error Unexpected";
  public static final String ERROR_NOT_EXIST_CLIENT_TYPE = "Client type does not exist";
  public static final String ERROR_NOT_EXIST_PRODUCT_TYPE = "Product type does not exist";
  public static final String ERROR_NOT_EXIST_PRODUCT = "Product does not exist";
  public static final String ERROR_INSUFFICIENT_BALANCE = "Insufficient balance";
  public static final String ERROR_DAY_NOT_ALLOWED = "Day not allowed";
  public static final String ERROR_INSUFFICIENT_CREDIT = "Insufficient credit";
  public static final String ERROR_PAGING_MORE = "Is paying more";
  public static final String FORMAT_DATE_COMPLETE = "dd/MM/yyyy HH:mm:ss";
  public static final BigDecimal MINIMUM_MONTHLY_AMOUNT = new BigDecimal("500");

}
