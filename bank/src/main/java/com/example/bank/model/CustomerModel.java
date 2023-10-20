package com.example.bank.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.List;

import lombok.Data;

/**.
* Class CustomerModel

* @author Andres Guizado
* @version 0.1, 2023/10/19
*/

@Data
public class CustomerModel {
	
	private Integer id;
	private String name;
	private String lastName;
	  
	public enum TypeCustomerEnum {
	    PERSONAL("personal"),
	    
	    BUSINESS("business");

	    private String value;

	    TypeCustomerEnum(String value) {
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
	    public static TypeCustomerEnum fromValue(String value) {
	    	for (TypeCustomerEnum b : TypeCustomerEnum.values()) {
	    		if (b.value.equals(value)) {
	    			return b;
	    		}
	    	}
	    	throw new IllegalArgumentException("Unexpected value '" + value + "'");
	    }
	}

	private TypeCustomerEnum typeCustomer;
	private List<AccountModel> accounts = null;
	private List<LoanModel> loans = null;

}
