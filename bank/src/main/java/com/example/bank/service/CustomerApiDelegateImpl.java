package com.example.bank.service;

import com.example.bank.model.AccountModel;
import com.example.bank.model.AccountModel.TypeAccountEnum;
import com.example.bank.model.CustomerModel;
import com.example.bank.model.CustomerModel.TypeCustomerEnum;
import com.example.bank.model.LoanModel;
import com.example.bank.repository.AccountRepository;
import com.example.bank.repository.CustomerRepository;
import com.example.bank.repository.LoanRepository;
import java.util.Optional;
import java.util.logging.Logger;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * . Class CustomerApiDelegateImpl
 *
 * @author Andres Guizado
 * @version 0.1, 2023/10/16
 */
@Service
@RequiredArgsConstructor
public class CustomerApiDelegateImpl implements CustomerApiDelegate {

  private final CustomerRepository customerRepository;
  private final AccountRepository accountRepository;
  private final LoanRepository loanRepository;

  static Logger logger = Logger.getLogger(CustomerApiDelegateImpl.class.getName());

  @Override
  public CustomerModel createCustomer(CustomerModel customer) {
    validateCustomer(customer);
    return customerRepository.save(customer);
  }

  /**
   * . This method validate Customer
   *
   * @param customer This is the first parameter
   */
  public void validateCustomer(CustomerModel customer) {
    if (TypeCustomerEnum.PERSONAL.equals(customer.getTypeCustomer())) {
      validatePersonal(customer);
    } else if (TypeCustomerEnum.BUSINESS.equals(customer.getTypeCustomer())) {
      validateBusiness(customer);
    } else if (TypeCustomerEnum.PERSONAL_VIP.equals(customer.getTypeCustomer())) {
      validatePersonalVip(customer);
    } else if (TypeCustomerEnum.BUSINESS_PYME.equals(customer.getTypeCustomer())) {
      validateBusinessPyme(customer);
    }
  }

  private void validateBusinessPyme(CustomerModel customer) {
    // Como requisito debe de tener una cuenta corriente.
    // Como requisito, el cliente debe tener una tarjeta de crédito con el banco al
    // momento de la creación de la cuenta.
  }

  private void validatePersonalVip(CustomerModel customer) {
    logger.info("PERSONAL_VIP");
    validateAccountVip(customer);
    if (customer.getLoans().size() <= 1) {
      saveLoan(customer);
    } else {
      logger.info("Un cliente puede tener un producto de crédito");
    }
  }

  private void validateAccountVip(CustomerModel customer) {
  }

  /**
   * . This method validate Personal Customer
   *
   * @param customer This is the first parameter
   */
  public void validatePersonal(CustomerModel customer) {
    logger.info("PERSONAL");
    validateAccount(customer);
    if (customer.getLoans().size() <= 1) {
      saveLoan(customer);
    } else {
      logger.info("Un cliente puede tener un producto de crédito");
    }
  }

  private void validateAccount(CustomerModel customer) {
    int nroAccountSaving = 0;
    int nroAccountCurrent = 0;
    int nroAccountFixedTerm = 0;
    for (AccountModel account : customer.getAccounts()) {
      if ((nroAccountSaving < 1 || nroAccountCurrent < 1 || nroAccountFixedTerm < 1)
          && account != null
          && (account.getOwners().isEmpty() && account.getAuthorizedSignatories().isEmpty())) {
        if (TypeAccountEnum.SAVING.equals(account.getTypeAccount())) {
          setDataCustomer(account, false, 0, 3);
          nroAccountSaving++;
        }
        if (TypeAccountEnum.CURRENT.equals(account.getTypeAccount())) {
          setDataCustomer(account, true, 5, null);
          nroAccountCurrent++;
        }
        if (TypeAccountEnum.FIXED_TERM.equals(account.getTypeAccount())) {
          setDataCustomer(account, false, 0, 1);
          nroAccountFixedTerm++;
        }
      } else {
        logger.info(
            "Un cliente personal solo puede tener un máximo de una cuenta de ahorro,"
            + " una cuenta corriente o cuentas a plazo fijo.");
        logger.info("Un cliente personal no necesita tilutales y/o firmantes");
      }
      accountRepository.save(account);
    }
  }

  /**
   * . This method set data Customer
   *
   * @param account This is the first parameter
   * @param mainFee This is the second parameter
   * @param mainValue This is the third parameter
   * @param transLimit This is the fourth parameter
   */
  public void setDataCustomer(AccountModel account, boolean mainFee,
      Integer mainValue, Integer transLimit) {
    account.setMaintenanceFee(mainFee);
    account.setMaintenanceValue(mainValue);
    account.setMonthlyTransactionLimit(transLimit);
  }

  /**
   * . This method validate Business Customer
   *
   * @param customer This is the first parameter
   */
  public void validateBusiness(CustomerModel customer) {
    logger.info("BUSINESS");
    for (AccountModel account : customer.getAccounts()) {

      if ((!TypeAccountEnum.CURRENT.equals(account.getTypeAccount()))
          && (account.getOwners().size() <= 1 && account.getAuthorizedSignatories().isEmpty())) {
        logger.info("Un cliente empresarial no puede tener una cuenta de ahorro o de plazo fijo");
        logger.info(
            "Las cuentas bancarias empresariales deben tener uno o más titulares y"
            + " cero o más firmantes autorizados");
      }

      setDataCustomer(account, true, 5, null);
      accountRepository.save(account);
    }
    saveLoan(customer);
  }

  private void saveLoan(CustomerModel customer) {
    for (LoanModel loan : customer.getLoans()) {
      loanRepository.save(loan);
    }
  }

  @Override
  public Optional<CustomerModel> getCustomer(Integer customerId) {
    return customerRepository.findById(customerId);
  }

  @Override
  public CustomerModel editCustomer(CustomerModel customer) {
    validateCustomer(customer);
    return customerRepository.save(customer);
  }

  @Override
  public void deleteCustomer(Integer customerId) {
    customerRepository.deleteById(customerId);
    //borrar cuenta y/o loan
  }

}
