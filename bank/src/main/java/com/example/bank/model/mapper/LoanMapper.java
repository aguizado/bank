package com.example.bank.model.mapper;

import com.example.bank.model.LoanModel;
import com.example.bank.model.dto.LoanDto;
import java.util.List;
import org.mapstruct.Mapper;

/**
 * . Class LoanMapper
 *
 * @author Andres Guizado
 * @version 0.1, 2023/10/25
 */
@Mapper(componentModel = "spring")
public interface LoanMapper {
  
  LoanModel toLoan(LoanDto loanDto);
  
  List<LoanModel> toLoans(List<LoanDto> loanDto);
  
  LoanDto toEntity(LoanModel loanModel);

}
