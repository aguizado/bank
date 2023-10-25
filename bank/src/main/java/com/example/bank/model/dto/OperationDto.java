package com.example.bank.model.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

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
  
  private Integer id;
  private CustomerDto customer;
  
  /**
   * . Enum TypeOperationEnumDto
   *
   * @author Andres Guizado
   * @version 0.1, 2023/10/23
   */
  public enum TypeOperationEnumDto {

    DEPOSIT("deposit"), WITHDRAWAL("withdrawal"), PAYMENT("payment"), CONSUMPTION("consumption");

    private String value;

    TypeOperationEnumDto(String value) {
      this.value = value;
    }

    @JsonValue
    public String getValue() {
      return value;
    }

    @Override
    public String toString() {
      return String.valueOf(value);
    }

    /**
     * . This method is fromValue TypeOperationEnum
     *
     * @param value This is the first parameter
     * @return a new value
     */
    @JsonCreator
    public static TypeOperationEnumDto fromValue(String value) {
      for (TypeOperationEnumDto b : TypeOperationEnumDto.values()) {
        if (b.value.equals(value)) {
          return b;
        }
      }
      throw new IllegalArgumentException("Unexpected value '" + value + "'");
    }
  }
  
  private TypeOperationEnumDto typeOperation;
  private Integer balance;
  private String operationDate;

}
