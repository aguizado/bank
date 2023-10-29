package com.example.bank.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * . Class OperationModel
 *
 * @author Andres Guizado
 * @version 0.1, 2023/10/19
 */
@Setter
@Getter
@ToString
@RequiredArgsConstructor
@Document(collection = "operation")
public class OperationModel {

  @Id
  @JsonProperty("id")
  private Integer id;

  @JsonProperty("type_operation")
  private OperationTypeModel typeOperation;

  @JsonProperty("customer_producto")
  private CustomerProductModel customerProducto;

  @JsonProperty("balance")
  private Integer balance;

  @JsonProperty("commission")
  private Integer commission;

  @JsonProperty("operation_date")
  private String operationDate;

}
