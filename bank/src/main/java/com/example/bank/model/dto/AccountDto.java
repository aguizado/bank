package com.example.bank.model.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import java.util.List;
import javax.validation.Valid;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;

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
  
  @Id
  @JsonProperty("id")
  private Integer id;
  
  @JsonProperty("account_number")
  private String accountNumber;
  
  @JsonProperty("account_value")
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
  
  @JsonProperty("type_account")
  private TypeAccountEnumDto typeAccount;
  
  @JsonProperty("maintenance_fee")
  private Boolean maintenanceFee;
  
  @JsonProperty("maintenance_value")
  private Integer maintenanceValue;
  
  @JsonProperty("monthly_transaction_limit")
  private Integer monthlyTransactionLimit;
  
  @JsonProperty("owners")
  @Valid
  private List<CustomerDto> owners = null;
  
  @JsonProperty("authorized_signatories")
  @Valid
  private List<CustomerDto> authorizedSignatories = null;

}
