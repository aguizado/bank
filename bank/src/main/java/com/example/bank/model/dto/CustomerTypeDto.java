package com.example.bank.model.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * . Class CustomerTypeDto
 *
 * @author Andres Guizado
 * @version 0.1, 2023/10/27
 */
@Setter
@Getter
@ToString
@RequiredArgsConstructor
public class CustomerTypeDto {
  
  @JsonProperty("id")
  private Integer id;

  /**
   * . Enum DescriptionEnum
   *
   * @author Andres Guizado
   * @version 0.1, 2023/10/27
   */
  public enum DescriptionEnum {
    
    PERSONAL("personal"), BUSSINESS("business");

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
  private DescriptionEnum description;

  @JsonProperty("type_profile")
  private ProfileDto typeProfile;

  @JsonProperty("is_delete")
  private Boolean isDelete = false;

}
