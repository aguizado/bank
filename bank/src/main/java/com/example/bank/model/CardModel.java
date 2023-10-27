package com.example.bank.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * . Class CardModel
 *
 * @author Andres Guizado
 * @version 0.1, 2023/10/26
 */
@Setter
@Getter
@ToString
@RequiredArgsConstructor
@Document(collection = "card")
public class CardModel {
  
  @Id
  @JsonProperty("id")
  private Integer id;

  @JsonProperty("number")
  private String number;

  @JsonProperty("is_delete")
  private Boolean isDelete = false;

}
