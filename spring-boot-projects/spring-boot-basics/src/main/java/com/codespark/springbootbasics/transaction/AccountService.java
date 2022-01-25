package com.codespark.springbootbasics.transaction;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountService {

	private static final Logger logger = LoggerFactory.getLogger(AccountService.class);

	@Autowired
	private AccountRepository accountRepository;

	public Account createSavingsAccount(int id, String name) {
		logger.info("Created new savings account for " + name);
		return accountRepository.save(new Account(id, name, new BigDecimal(10)));
	}

	public Account depositToSavingsAccount(int accountId, BigDecimal amount) {
		Account account = accountRepository.findById(accountId)
				.orElseThrow(() -> new RuntimeException("Account not found"));
		account.setBalance(account.getBalance().add(amount));
		logger.info("Rs. " + amount + " deposited to the savings account for " + account.getName());
		return accountRepository.save(account);
	}

	/**
	 * This method is used to withdraw a single amount from user account and does
	 * not needs @Transactional since only a single database operation is wrapped
	 * inside the block.
	 * 
	 * @param accountId Account ID
	 * @param amount    Transaction amount
	 * @return
	 */
	public Account withdrawFromSavingsAccount(int accountId, BigDecimal amount) {
		Account account = accountRepository.findById(accountId)
				.orElseThrow(() -> new RuntimeException("Account not found"));
		account.setBalance(account.getBalance().subtract(amount));
		logger.info("Rs. " + amount + " withdrawn from the savings account for " + account.getName());
		return accountRepository.save(account);
	}

	/**
	 * This method implements the process of wrapping multiple database operations
	 * with non-intrusive transactions. This method is tagged with @Transactional,
	 * meaning that any failure causes the entire operation to roll back to its
	 * previous state and to re-throw the original exception. Which means the
	 * transaction within would be rolled back if any of the data constraints fail.
	 * In this case it is the minimum account balance of 10, is ensured to be
	 * maintained while any database operation on account within this method. a
	 * DataSourceTransactionManager is also automatically, this is the component
	 * that intercepts the method annotated with @Transactional.
	 * 
	 * @param accountId Account ID
	 * @param amounts   Transaction amounts
	 * @return
	 */
	@Transactional
	public Optional<Account> withdrawFromSavingsAccount(int accountId, List<BigDecimal> amounts) {
		for (BigDecimal amount : amounts) {
			Account account = accountRepository.findById(accountId)
					.orElseThrow(() -> new RuntimeException("Account not found"));
			account.setBalance(account.getBalance().subtract(amount));
			logger.info("Rs. " + amount + " withdrawn from the savings account for " + account.getName());
			accountRepository.save(account);
		}
		return accountRepository.findById(accountId);
	}

}
