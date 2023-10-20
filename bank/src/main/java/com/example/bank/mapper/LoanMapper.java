package com.example.bank.mapper;

import com.example.bank.model.Loan;
import com.example.bank.model.LoanModel;

import java.util.List;

import org.mapstruct.Mapper;

/**.
* Class CustomerModel

* @author Andres Guizado
* @version 0.1, 2023/10/19
*/
@Mapper(componentModel = "spring")
public interface LoanMapper {
	
	Loan toLoan(LoanModel loanModel);
	
	List<Loan> toLoans (List<LoanModel> loanModels);
	
	LoanModel toEntity(Loan loan);

}
