package com.example.bank.mapper;

import com.example.bank.model.Account;
import com.example.bank.model.AccountModel;

import java.util.List;

import org.mapstruct.Mapper;

/**.
* Class CustomerModel

* @author Andres Guizado
* @version 0.1, 2023/10/19
*/
@Mapper(componentModel = "spring")
public interface AccountMapper {
	
	Account toAccount(AccountModel accountModel);
	
	List<Account> toAccounts(List<AccountModel> accountModels);
	
	AccountModel toEntity(Account account);

}
