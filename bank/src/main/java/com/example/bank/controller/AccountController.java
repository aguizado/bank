package com.example.bank.controller;

import com.example.bank.model.AccountModel;
import com.example.bank.repository.AccountRepository;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**.
* Class AccountController
*
* @author Andres Guizado
* @version 0.1, 2023/10/20
*/
@RestController
@RequiredArgsConstructor
public class AccountController {
	
	private final AccountRepository accountRepository;
	
	/**.
	 * This method is to save Account
	 *
	 * @param account This is the first parameter
	 * @return a HTTP Status
	 */
	@PostMapping("/accounts")
	public ResponseEntity<AccountModel> accountsPost(@RequestBody AccountModel account) {
		try {
			AccountModel accountModel = accountRepository.save(account);
			return new ResponseEntity<>(accountModel, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}	
	}
	
	@GetMapping("/accounts/{accountId}")
	public ResponseEntity<AccountModel> accountsAccountIdGet(
			@PathVariable("accountId") Integer accountId) {
		Optional<AccountModel> opAccount = accountRepository.findById(accountId);
		if (opAccount.isPresent()) {
			return ResponseEntity.status(HttpStatus.OK).body(opAccount.get());
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}
	
	@PutMapping("/accounts/{accountId}")
	public ResponseEntity<AccountModel> accountsAccountIdPut(@PathVariable("accountId") Integer accountId, @RequestBody AccountModel account) {
		Optional<AccountModel> opAccount = accountRepository.findById(accountId);
		if (opAccount.isPresent()) {
			return new ResponseEntity<>(accountRepository.save(account), HttpStatus.OK);
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}
	
//	@DeleteMapping("/accounts/{accountId}")
//	public ResponseEntity<Void> accountsAccountIdDelete(@PathVariable("accountId") Integer accountId) {
//		try {
//			accountRepository.deleteById(accountId);
//		    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//		} catch (Exception e) {
//			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//		}
//	}

}
