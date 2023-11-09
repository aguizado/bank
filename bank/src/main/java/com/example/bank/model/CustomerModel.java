package com.example.bank.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
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

  @Id
  @JsonProperty("id")
  private Integer id;

  @JsonProperty("name")
  private String name;

  @JsonProperty("last_name")
  private String lastName;

  @JsonProperty("type_customer")
  private CustomerTypeModel typeCustomer;
  
  /**
   * . Enum DescriptionEnum
   *
   * @author Andres Guizado
   * @version 0.1, 2023/10/27
   */
  public enum TypeDocumentEnum {
    
    DNI("DNI"), CEX("CEX"), PASSPORT("passport");

    private String value;

    TypeDocumentEnum(String value) {
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
     * . This method is fromValue DescriptionEnum
     *
     * @param value This is the first parameter
     * @return a new value
     */
    @JsonCreator
    public static TypeDocumentEnum fromValue(String value) {
      for (TypeDocumentEnum b : TypeDocumentEnum.values()) {
        if (b.value.equals(value)) {
          return b;
        }
      }
      throw new IllegalArgumentException("Unexpected value '" + value + "'");
    }
  }

  @JsonProperty("type_document")
  private TypeDocumentEnum typeDocument = TypeDocumentEnum.DNI;

  @JsonProperty("document_numer")
  private String documentNumer;

  @JsonProperty("phone")
  private String phone;

  @JsonProperty("imei")
  private String imei;

  @JsonProperty("email")
  private String email;

}
