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
 * . Class CustomerModel
 *
 * @author Andres Guizado
 * @version 0.1, 2023/10/19
 */
@Setter
@Getter
@ToString
@RequiredArgsConstructor
@Document(collection = "customer")
public class CustomerModel {

  @JsonProperty("id")
  private Integer id;

  @JsonProperty("name")
  private String name;

  @JsonProperty("last_name")
  private String lastName;

  public enum TypeCustomerEnum {

    PERSONAL("personal"), PERSONAL_VIP("personal_vip"), BUSINESS("business"), BUSINESS_PYME("business_pyme");

    private String value;

    TypeCustomerEnum(String value) {
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

    @JsonCreator
    public static TypeCustomerEnum fromValue(String value) {
      for (TypeCustomerEnum b : TypeCustomerEnum.values()) {
        if (b.value.equals(value)) {
          return b;
        }
      }
      throw new IllegalArgumentException("Unexpected value '" + value + "'");
    }
  }

  @JsonProperty("type_customer")
  private TypeCustomerEnum typeCustomer;

  @JsonProperty("accounts")
  @Valid
  private List<AccountModel> accounts = null;

  @JsonProperty("loans")
  @Valid
  private List<LoanModel> loans = null;

}
