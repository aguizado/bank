package com.example.bank.model.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import java.math.BigDecimal;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;

/**
 * . Class TransferDto
 *
 * @author Andres Guizado
 * @version 0.1, 2023/11/05
 */
@Setter
@Getter
@ToString
@RequiredArgsConstructor
public class TransferDto {
  
  @Id
  @JsonProperty("id")
  private Integer id;
  
  /**
   * . Enum TypeTransferEnum
   *
   * @author Andres Guizado
   * @version 0.1, 2023/11/05
   */
  public enum TypeTransferEnum {
    
    OWN("own"), THIRD("third");

    private String value;

    TypeTransferEnum(String value) {
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
    public static TypeTransferEnum fromValue(String value) {
      for (TypeTransferEnum b : TypeTransferEnum.values()) {
        if (b.value.equals(value)) {
          return b;
        }
      }
      throw new IllegalArgumentException("Unexpected value '" + value + "'");
    }
  }
  
  @JsonProperty("type_transfer")
  private TypeTransferEnum typeTransfer = TypeTransferEnum.OWN;

  @JsonProperty("customer_origin")
  private Integer customerOrigin;
  
  @JsonProperty("customer_destination")
  private Integer customerDestination;

  @JsonProperty("product_number")
  private String productNumber;

  @JsonProperty("amount")
  private BigDecimal amount;

  @JsonProperty("transfer_date")
  private String transferDate;

}
