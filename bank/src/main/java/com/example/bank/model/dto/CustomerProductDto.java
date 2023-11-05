package com.example.bank.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.math.BigDecimal;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;

/**
 * . Class CustomerProductDto
 *
 * @author Andres Guizado
 * @version 0.1, 2023/10/27
 */
@Setter
@Getter
@ToString
@RequiredArgsConstructor
public class CustomerProductDto {
  
  @Id
  @JsonProperty("id")
  private Integer id;

  @JsonProperty("customer")
  private CustomerDto customer;

  @JsonProperty("product")
  private ProductDto product;

  @JsonProperty("Representative")
  private RepresentativeDto representative;

  @JsonProperty("product_number")
  private String productNumber;

  @JsonProperty("number_card")
  private CardDto numberCard;

  @JsonProperty("amount")
  private BigDecimal amount;
  
  @JsonProperty("credit_limit")
  private BigDecimal creditLimit;

  @JsonProperty("number_transaction_limit")
  private Integer numberTransactionLimit;

  @JsonProperty("creation_date")
  private String creationDate;
  
  @JsonProperty("modification_date")
  private String modificationDate;

}
