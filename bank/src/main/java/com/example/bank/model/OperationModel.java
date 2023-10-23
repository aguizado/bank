package com.example.bank.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * . Class OperationModel
 *
 * @author Andres Guizado
 * @version 0.1, 2023/10/19
 */
@Setter
@Getter
@ToString
@RequiredArgsConstructor
@Document(collection = "operation")
public class OperationModel {

	@JsonProperty("id")
	private Integer id;

	@JsonProperty("customer")
	private CustomerModel customer;

	public enum TypeOperationEnum {

		DEPOSIT("deposit"), WITHDRAWAL("withdrawal"), PAYMENT("payment"), CONSUMPTION("consumption");

		private String value;

		TypeOperationEnum(String value) {
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
		public static TypeOperationEnum fromValue(String value) {
			for (TypeOperationEnum b : TypeOperationEnum.values()) {
				if (b.value.equals(value)) {
					return b;
				}
			}
			throw new IllegalArgumentException("Unexpected value '" + value + "'");
		}
	}

	@JsonProperty("type_operation")
	private TypeOperationEnum typeOperation;

	@JsonProperty("balance")
	private Integer balance;

	@JsonProperty("operation_date")
	private String operationDate;

}
