package com.example.bank.model.dto;

import com.example.bank.model.RepresentativeTypeModel;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;

/**
 * . Class RepresentativeDto
 *
 * @author Andres Guizado
 * @version 0.1, 2023/10/27
 */
@Setter
@Getter
@ToString
@RequiredArgsConstructor
public class RepresentativeDto {
  
  @Id
  @JsonProperty("id")
  private Integer id;

  @JsonProperty("type_representative")
  private RepresentativeTypeModel typeRepresentative;

  @JsonProperty("name")
  private String name;

  @JsonProperty("last_name")
  private String lastName;

}
