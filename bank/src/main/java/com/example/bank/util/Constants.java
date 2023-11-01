package com.example.bank.util;

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

}
