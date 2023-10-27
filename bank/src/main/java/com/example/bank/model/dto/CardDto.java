package com.example.bank.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;

/**
 * . Class CardDto
 *
 * @author Andres Guizado
 * @version 0.1, 2023/10/26
 */
@Setter
@Getter
@ToString
@RequiredArgsConstructor
public class CardDto {
  
  @Id
  @JsonProperty("id")
  private Integer id;

  @JsonProperty("number")
  private String number;

  @JsonProperty("is_delete")
  private Boolean isDelete = false;

}
