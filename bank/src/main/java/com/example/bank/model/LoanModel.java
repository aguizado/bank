package com.example.bank.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import lombok.Data;

/**.
* Class CustomerModel

* @author Andres Guizado
* @version 0.1, 2023/10/19
*/
@Data
public class LoanModel {
	
	private Integer id;
	private String loanNumber;
	private Integer loanValue;

	public enum TypeLoanEnum {

		PERSONAL("personal"),	    
	    BUSINESS("business"),	    
	    CREDIT_CARD("credit_card");

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

	private TypeLoanEnum typeLoan;
	private Integer unitLimit;

}
