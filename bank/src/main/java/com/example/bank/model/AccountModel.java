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
public class AccountModel {
	
	private Integer id;
	private String accountNumber;
	private Integer accountValue;

	public enum TypeAccountEnum {
	    SAVING("saving"),
	    
	    CURRENT("current"),
	    
	    FIXED_TERM("fixed_term");

	    private String value;

	    TypeAccountEnum(String value) {
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
	    public static TypeAccountEnum fromValue(String value) {
	    	for (TypeAccountEnum b : TypeAccountEnum.values()) {
	    		if (b.value.equals(value)) {
	    			return b;
	    		}
	    	}
	    	throw new IllegalArgumentException("Unexpected value '" + value + "'");
	    }
	}

	private TypeAccountEnum typeAccount;
	private Boolean maintenanceFee;
	private Integer maintenanceValue;
	private Integer monthlyTransactionLimit;
	private List<CustomerModel> owners = null;
	private List<CustomerModel> authorizedSignatories = null;

}
