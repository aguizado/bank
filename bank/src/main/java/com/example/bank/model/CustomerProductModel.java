package com.example.bank.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * . Class CustomerProductModel
 *
 * @author Andres Guizado
 * @version 0.1, 2023/10/27
 */
@Setter
@Getter
@ToString
@RequiredArgsConstructor
@Document(collection = "customer_product")
public class CustomerProductModel {
  
  @Id
  @JsonProperty("id")
  private Integer id;

  @JsonProperty("customer")
  private CustomerModel customer;

  @JsonProperty("product")
  private ProductModel product;

  @JsonProperty("Representative")
  private RepresentativeModel representative;

  @JsonProperty("product_number")
  private String productNumber;

  @JsonProperty("number_card")
  private CardModel numberCard;

  @JsonProperty("amount")
  private Integer amount;

  @JsonProperty("number_transaction_limit")
  private Integer numberTransactionLimit;

  @JsonProperty("creation_date")
  private String creationDate;

}
