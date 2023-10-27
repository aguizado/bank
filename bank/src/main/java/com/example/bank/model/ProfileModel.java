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
 * . Class ProfileModel
 *
 * @author Andres Guizado
 * @version 0.1, 2023/10/26
 */
@Setter
@Getter
@ToString
@RequiredArgsConstructor
@Document(collection = "profile")
public class ProfileModel {
  
  @Id
  @JsonProperty("id")
  private Integer id;

  /**
   * . Enum DescriptionEnum
   *
   * @author Andres Guizado
   * @version 0.1, 2023/10/26
   */
  public enum DescriptionEnum {
    
    REGULAR("regular"), VIP("vip"), PYME("pyme");

    private String value;

    DescriptionEnum(String value) {
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
    public static DescriptionEnum fromValue(String value) {
      for (DescriptionEnum b : DescriptionEnum.values()) {
        if (b.value.equals(value)) {
          return b;
        }
      }
      throw new IllegalArgumentException("Unexpected value '" + value + "'");
    }
  }

  @JsonProperty("description")
  private DescriptionEnum description = DescriptionEnum.REGULAR;

  @JsonProperty("is_delete")
  private Boolean isDelete = false;

}
