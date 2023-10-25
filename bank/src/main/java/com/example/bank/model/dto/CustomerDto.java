package com.example.bank.model.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import java.util.List;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * . Class CustomerDto
 *
 * @author Andres Guizado
 * @version 0.1, 2023/10/25
 */
@Setter
@Getter
@ToString
@RequiredArgsConstructor
public class CustomerDto {
  
  private Integer id;
  private String name;
  private String lastName;
  
  /**
   * . Enum TypeCustomerEnumDto
   *
   * @author Andres Guizado
   * @version 0.1, 2023/10/23
   */
  public enum TypeCustomerEnumDto {

    PERSONAL("personal"), PERSONAL_VIP("personal_vip"),
    BUSINESS("business"), BUSINESS_PYME("business_pyme");

    private String value;

    TypeCustomerEnumDto(String value) {
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
     * . This method is fromValue TypeCustomerEnum
     *
     * @param value This is the first parameter
     * @return a new value
     */
    @JsonCreator
    public static TypeCustomerEnumDto fromValue(String value) {
      for (TypeCustomerEnumDto b : TypeCustomerEnumDto.values()) {
        if (b.value.equals(value)) {
          return b;
        }
      }
      throw new IllegalArgumentException("Unexpected value '" + value + "'");
    }
  }
  
  private TypeCustomerEnumDto typeCustomer;
  private List<AccountDto> accounts = null;
  private List<LoanDto> loans = null;

}
