package com.example.bank.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import java.util.List;
import javax.validation.Valid;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * . Class AccountModel
 *
 * @author Andres Guizado
 * @version 0.1, 2023/10/19
 */
@Setter
@Getter
@ToString
@RequiredArgsConstructor
@Document(collection = "account")
public class AccountModel {

  @JsonProperty("id")
  private Integer id;

  @JsonProperty("account_number")
  private String accountNumber;

  @JsonProperty("account_value")
  private Integer accountValue;

  /**
   * . Enum TypeAccountEnum
   *
   * @author Andres Guizado
   * @version 0.1, 2023/10/23
   */
  public enum TypeAccountEnum {

    SAVING("saving"), CURRENT("current"), FIXED_TERM("fixed_term");

    private String value;

    TypeAccountEnum(String value) {
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
    public static TypeAccountEnum fromValue(String value) {
      for (TypeAccountEnum b : TypeAccountEnum.values()) {
        if (b.value.equals(value)) {
          return b;
        }
      }
      throw new IllegalArgumentException("Unexpected value '" + value + "'");
    }
  }

  @JsonProperty("type_account")
  private TypeAccountEnum typeAccount;

  @JsonProperty("maintenance_fee")
  private Boolean maintenanceFee;

  @JsonProperty("maintenance_value")
  private Integer maintenanceValue;

  @JsonProperty("monthly_transaction_limit")
  private Integer monthlyTransactionLimit;

  @JsonProperty("owners")
  @Valid
  private List<CustomerModel> owners = null;

  @JsonProperty("authorized_signatories")
  @Valid
  private List<CustomerModel> authorizedSignatories = null;

}
