package com.example.bank.service;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.bank.api.CustomersApiDelegate;
import com.example.bank.model.Account;
import com.example.bank.model.Customer;
import com.example.bank.model.CustomerType.DescriptionEnum;
import com.example.bank.model.Loan;
import com.example.bank.repository.CustomerRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomersApiDelegateImpl implements CustomersApiDelegate{
	
	private final CustomerRepository customerRepository;
	private Integer ONE = 1;
	private Integer MONTHLY_TRANSACTION_LIMIT_SAVING = 3;
	private Integer MONTHLY_TRANSACTION_LIMIT_FIXED_TERM = 1;
	private Integer MAINTENANCE_VALUE = 5;
	private Integer ZERO = 0;
	
	@Override
	public ResponseEntity<Customer> customersPost(Customer customer){
		
		boolean isOk = validateCustomer(customer);
		
		if(!isOk) {
			System.out.println("BAD_REQUEST");
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
						
		customerRepository.save(customer);
		return ResponseEntity.ok(customer);
	}
	
	@Override
	public ResponseEntity<Customer> customersCustomerIdGet(Integer customerId){		
		return customerRepository.findById(customerId).map(ResponseEntity::ok).orElse(new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR));
	}
	
	@Override
	public ResponseEntity<Customer> customersCustomerIdPut(Integer customerId, Customer customer) {
		
		boolean isOk = validateCustomer(customer);
		
		if(!isOk) {
			System.out.println("BAD_REQUEST");
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		customerRepository.save(customer);
		return ResponseEntity.ok(customer);
	}
	
	@Override
	public ResponseEntity<Void> customersCustomerIdDelete(Integer customerId){
		customerRepository.deleteById(customerId);
		return  ResponseEntity.ok().build();
	}
	
	private boolean validateCustomer(Customer customer) {
		boolean isOk = true;
		if(DescriptionEnum.PERSONAL.equals(customer.getType().getDescription())) {
			isOk = validatePersonal(customer, isOk);			
		} else if (DescriptionEnum.BUSINESS.equals(customer.getType().getDescription())) {
			isOk = validateBusiness(customer, isOk);
		} else {
			isOk = false;
		}
		return isOk;
	}

	private boolean validatePersonal(Customer customer, boolean isOk) {
		System.out.println("PERSONAL");
		
		int nroAccountSaving = 0;
		int nroAccountCurrent = 0;
		int nroAccountFixedTerm = 0;
		
		for (Account account : customer.getAccounts()) {
			if((nroAccountSaving < 1 || nroAccountCurrent < 1 || nroAccountFixedTerm < 1) &&
					(account.getOwners().size() == 0 && account.getAuthorizedSignatories().size() == 0)) {
				if(com.example.bank.model.AccountType.DescriptionEnum.SAVING.equals(account.getType().getDescription())) {
					account.setMaintenanceFee(false);
					account.setMaintenanceValue(ZERO);
					account.setMonthlyTransactionLimit(MONTHLY_TRANSACTION_LIMIT_SAVING);
					nroAccountSaving++;
				}
				if(com.example.bank.model.AccountType.DescriptionEnum.CURRENT.equals(account.getType().getDescription())) {
					account.setMaintenanceFee(true);
					account.setMaintenanceValue(MAINTENANCE_VALUE);
					account.setMonthlyTransactionLimit(null);
					nroAccountCurrent++;
				}
				if(com.example.bank.model.AccountType.DescriptionEnum.FIXED_TERM.equals(account.getType().getDescription())) {
					account.setMaintenanceFee(false);
					account.setMaintenanceValue(ZERO);
					account.setMonthlyTransactionLimit(MONTHLY_TRANSACTION_LIMIT_FIXED_TERM);
					nroAccountFixedTerm++;
				}
				
			}else {
				System.out.println("Un cliente personal solo puede tener un máximo de una cuenta de ahorro, una cuenta corriente o cuentas a plazo fijo.");
				System.out.println("Un cliente personal no necesita tilutales y/o firmantes");
				isOk = false;
			}			
			saveAccount(account);
		}
		
		if(customer.getLoans().size() <= ONE) {
			saveLoan(customer);
		} else {
			isOk = false;
		}
		return isOk;
	}
	
	private boolean validateBusiness(Customer customer, boolean isOk) {
		System.out.println("BUSINESS");
		for (Account account : customer.getAccounts()) {
			
			if((!com.example.bank.model.AccountType.DescriptionEnum.CURRENT.equals(account.getType().getDescription())) &&
					(account.getOwners().size() <= 1 && account.getAuthorizedSignatories().size() <= 0)) {	
				System.out.println("Un cliente empresarial no puede tener una cuenta de ahorro o de plazo fijo");
				System.out.println("Las cuentas bancarias empresariales deben tener uno o más titulares y cero o más firmantes autorizados");
				isOk = false;
			}
			
			account.setMaintenanceFee(true);
			account.setMaintenanceValue(MAINTENANCE_VALUE);
			account.setMonthlyTransactionLimit(null);
			
			saveAccount(account);
		}
		saveLoan(customer);
		return isOk;
	}

	private void saveAccount(Account account) {
//		AccountApiDelegateImpl accountImpl = new AccountApiDelegateImpl(null);
//		accountImpl.accountsPost(account);
	}

	private void saveLoan(Customer customer) {
//		for(Loan loan : customer.getLoans()) {
//			LoanApiDelegateImpl loanImpl = new LoanApiDelegateImpl(null);
//			loanImpl.loansPost(loan);
//		}
	}

}
