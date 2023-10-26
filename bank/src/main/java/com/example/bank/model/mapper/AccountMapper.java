package com.example.bank.model.mapper;

import com.example.bank.model.AccountModel;
import com.example.bank.model.dto.AccountDto;
import java.util.List;
import org.mapstruct.Mapper;

/**
 * . Class AccountMapper
 *
 * @author Andres Guizado
 * @version 0.1, 2023/10/25
 */
@Mapper(componentModel = "spring")
public interface AccountMapper {
  
  AccountModel toAccount(AccountDto accountDto);
  
  List<AccountModel> toAccounts(List<AccountDto> accountDto);
  
  AccountDto toEntity(AccountModel accountModel);

}
