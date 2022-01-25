package com.codespark.springbootbasics.transaction;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;

//@Component
public class TransactionBootstrap implements CommandLineRunner {

	private static final Logger logger = LoggerFactory.getLogger(TransactionBootstrap.class);
	private static final int ACCOUNT_ID = 123;

	@Autowired
	private AccountService accountService;

	@Override
	public void run(String... args) throws Exception {
		// Add a new account for user with initial amount of 10
		accountService.createSavingsAccount(ACCOUNT_ID, "Subho Das");

		// Deposit 10 to the user account, now total is 20
		accountService.depositToSavingsAccount(ACCOUNT_ID, new BigDecimal(10));

		// Create a complex transaction with multiple amounts
		List<BigDecimal> transaction = new ArrayList<>();
		transaction.add(new BigDecimal(5));
		transaction.add(new BigDecimal(10));

		try {
			// Withdraw using above transaction amounts as a single transaction
			accountService.withdrawFromSavingsAccount(ACCOUNT_ID, transaction);

			/*
			 * The above transaction will fail and roll back due to minimum account balance
			 * constraint, hence the previous balance of 20 is unchanged as 20 - 15 will
			 * violate the constraint. This can also be confirmed for the DB record and the
			 * log record below.
			 */
		} catch (Exception e) {
			logger.info("Transaction for amounts " + transaction + " rolled back due to min. balance constraint");
		}
	}

}
