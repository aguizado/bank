package com.example.bank.service;

import com.example.bank.api.CustomersApiDelegate;
import com.example.bank.model.Account;
import com.example.bank.model.Account.TypeAccountEnum;
import com.example.bank.model.Customer;
import com.example.bank.model.Customer.TypeCustomerEnum;
import com.example.bank.repository.CustomerRepository;
import java.util.logging.Logger;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

/**.
* Class CustomersApiDelegateImpl

* @author Andres Guizado
* @version 0.1, 2023/10/16
*/
@Service
@RequiredArgsConstructor
public class CustomersApiDelegateImpl implements CustomersApiDelegate {
	
	static Logger logger = Logger.getLogger(CustomersApiDelegateImpl.class.getName());
	
	private final CustomerRepository customerRepository;
	private Integer one = 1;
	private Integer monthlyTransactionLimitSaving = 3;
	private Integer monthlyTransactionLimitFixedTerm = 1;
	private Integer maintenanceValue = 5;
	private Integer zero = 0;
	
	@Override
	public ResponseEntity<Customer> customersPost(Customer customer) {
	
		boolean isOk = validateCustomer(customer);

		if(!isOk) {
			logger.info("BAD_REQUEST");
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	
		customerRepository.save(customer);
		return ResponseEntity.ok(customer);
	}
	
	@Override
	public ResponseEntity<Customer> customersCustomerIdGet(Integer customerId) {		
		return customerRepository.findById(customerId).map(ResponseEntity::ok).orElse(new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR));
	}
	
	@Override
	public ResponseEntity<Customer> customersCustomerIdPut(Integer customerId, Customer customer) {
		
		boolean isOk = validateCustomer(customer);
		
		if(!isOk) {
			logger.info("BAD_REQUEST");
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		customerRepository.save(customer);
		return ResponseEntity.ok(customer);
	}
	
	@Override
	public ResponseEntity<Void> customersCustomerIdDelete(Integer customerId) {
		customerRepository.deleteById(customerId);
		return  ResponseEntity.ok().build();
	}
	
	private boolean validateCustomer(Customer customer) {
		boolean isOk = true;
		if(TypeCustomerEnum.PERSONAL.equals(customer.getTypeCustomer())) {
			isOk = validatePersonal(customer, isOk);			
		} else if (TypeCustomerEnum.BUSINESS.equals(customer.getTypeCustomer())) {
			isOk = validateBusiness(customer, isOk);
		} else {
			isOk = false;
		}
		return isOk;
	}

	private boolean validatePersonal(Customer customer, boolean isOk) {
		logger.info("PERSONAL");
		
		int nroAccountSaving = 0;
		int nroAccountCurrent = 0;
		int nroAccountFixedTerm = 0;
		
		for (Account account : customer.getAccounts()) {
			if((nroAccountSaving < 1 || nroAccountCurrent < 1 || nroAccountFixedTerm < 1) &&
					(account.getOwners().isEmpty() && account.getAuthorizedSignatories().isEmpty())) {
				if(TypeAccountEnum.SAVING.equals(account.getTypeAccount())) {
					account.setMaintenanceFee(false);
					account.setMaintenanceValue(zero);
					account.setMonthlyTransactionLimit(monthlyTransactionLimitSaving);
					nroAccountSaving++;
				}
				if(TypeAccountEnum.CURRENT.equals(account.getTypeAccount())) {
					account.setMaintenanceFee(true);
					account.setMaintenanceValue(maintenanceValue);
					account.setMonthlyTransactionLimit(null);
					nroAccountCurrent++;
				}
				if(TypeAccountEnum.FIXED_TERM.equals(account.getTypeAccount())) {
					account.setMaintenanceFee(false);
					account.setMaintenanceValue(zero);
					account.setMonthlyTransactionLimit(monthlyTransactionLimitFixedTerm);
					nroAccountFixedTerm++;
				}
				
			}else {
				logger.info("Un cliente personal solo puede tener un máximo de una cuenta de ahorro, una cuenta corriente o cuentas a plazo fijo.");
				logger.info("Un cliente personal no necesita tilutales y/o firmantes");
				isOk = false;
			}			
			saveAccount(account);
		}
		
		if(customer.getLoans().size() <= one) {
			saveLoan(customer);
		} else {
			logger.info("Un cliente puede tener un producto de crédito");
			isOk = false;
		}
		return isOk;
	}
	
	private boolean validateBusiness(Customer customer, boolean isOk) {
		logger.info("BUSINESS");
		for (Account account : customer.getAccounts()) {
			
			if((!TypeAccountEnum.CURRENT.equals(account.getTypeAccount())) &&
					(account.getOwners().size() <= 1 && account.getAuthorizedSignatories().isEmpty())) {	
				logger.info("Un cliente empresarial no puede tener una cuenta de ahorro o de plazo fijo");
				logger.info("Las cuentas bancarias empresariales deben tener uno o más titulares y cero o más firmantes autorizados");
				isOk = false;
			}
			
			account.setMaintenanceFee(true);
			account.setMaintenanceValue(maintenanceValue);
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
