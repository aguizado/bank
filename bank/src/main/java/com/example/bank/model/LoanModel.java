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
 * . Class CustomerModel
 *
 * @author Andres Guizado
 * @version 0.1, 2023/10/19
 */
@Setter
@Getter
@ToString
@RequiredArgsConstructor
@Document(collection = "loan")
public class LoanModel {

	@JsonProperty("id")
	private Integer id;

	@JsonProperty("loan_number")
	private String loanNumber;

	@JsonProperty("loan_value")
	private Integer loanValue;

	public enum TypeLoanEnum {

		PERSONAL("personal"), BUSINESS("business"), CREDIT_CARD("credit_card");

		private String value;

		TypeLoanEnum(String value) {
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
		public static TypeLoanEnum fromValue(String value) {
			for (TypeLoanEnum b : TypeLoanEnum.values()) {
				if (b.value.equals(value)) {
					return b;
				}
			}
			throw new IllegalArgumentException("Unexpected value '" + value + "'");
		}

	}

	@JsonProperty("type_loan")
	private TypeLoanEnum typeLoan;

	@JsonProperty("unit_limit")
	private Integer unitLimit;

}
