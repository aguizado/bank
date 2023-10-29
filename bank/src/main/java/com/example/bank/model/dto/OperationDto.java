package com.example.bank.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;

/**
 * . Class OperationDto
 *
 * @author Andres Guizado
 * @version 0.1, 2023/10/25
 */
@Setter
@Getter
@ToString
@RequiredArgsConstructor
public class OperationDto {
  
  @Id
  @JsonProperty("id")
  private Integer id;

  @JsonProperty("type_operation")
  private OperationTypeDto typeOperation;

  @JsonProperty("customer_producto")
  private CustomerProductDto customerProducto;

  @JsonProperty("balance")
  private Integer balance;

  @JsonProperty("commission")
  private Integer commission;

  @JsonProperty("operation_date")
  private String operationDate;

}
