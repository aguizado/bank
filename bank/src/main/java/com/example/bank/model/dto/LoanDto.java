package com.example.bank.model.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * . Class LoanDto
 *
 * @author Andres Guizado
 * @version 0.1, 2023/10/25
 */
@Setter
@Getter
@ToString
@RequiredArgsConstructor
public class LoanDto {
  
  private Integer id;
  private String loanNumber;
  private Integer loanValue;
  
  /**
   * . Enum TypeLoanEnumDTO
   *
   * @author Andres Guizado
   * @version 0.1, 2023/10/23
   */
  public enum TypeLoanEnumDto {

    PERSONAL("personal"), BUSINESS("business"), CREDIT_CARD("credit_card");

    private String value;

    TypeLoanEnumDto(String value) {
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
     * . This method is fromValue TypeLoanEnum
     *
     * @param value This is the first parameter
     * @return a new value
     */
    @JsonCreator
    public static TypeLoanEnumDto fromValue(String value) {
      for (TypeLoanEnumDto b : TypeLoanEnumDto.values()) {
        if (b.value.equals(value)) {
          return b;
        }
      }
      throw new IllegalArgumentException("Unexpected value '" + value + "'");
    }

  }
  
  private TypeLoanEnumDto typeLoan;
  private Integer unitLimit;

}
