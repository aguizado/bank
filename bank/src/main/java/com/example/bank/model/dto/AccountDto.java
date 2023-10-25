package com.example.bank.model.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import java.util.List;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * . Class AccountDto
 *
 * @author Andres Guizado
 * @version 0.1, 2023/10/25
 */
@Setter
@Getter
@ToString
@RequiredArgsConstructor
public class AccountDto {
  
  private Integer id;
  private String accountNumber;
  private Integer accountValue;
  
  /**
   * . Enum TypeAccountEnumDto
   *
   * @author Andres Guizado
   * @version 0.1, 2023/10/23
   */
  public enum TypeAccountEnumDto {

    SAVING("saving"), CURRENT("current"), FIXED_TERM("fixed_term");

    private String value;

    TypeAccountEnumDto(String value) {
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
     * . This method is fromValue TypeAccountEnum
     *
     * @param value This is the first parameter
     * @return a new value
     */
    @JsonCreator
    public static TypeAccountEnumDto fromValue(String value) {
      for (TypeAccountEnumDto b : TypeAccountEnumDto.values()) {
        if (b.value.equals(value)) {
          return b;
        }
      }
      throw new IllegalArgumentException("Unexpected value '" + value + "'");
    }
  }
  
  private TypeAccountEnumDto typeAccount;
  private Boolean maintenanceFee;
  private Integer maintenanceValue;
  private Integer monthlyTransactionLimit;
  private List<CustomerDto> owners = null;
  private List<CustomerDto> authorizedSignatories = null;

}
