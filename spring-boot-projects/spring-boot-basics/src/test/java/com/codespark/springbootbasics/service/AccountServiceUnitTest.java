package com.codespark.springbootbasics.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doReturn;

import java.math.BigDecimal;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.codespark.springbootbasics.transaction.Account;
import com.codespark.springbootbasics.transaction.AccountRepository;
import com.codespark.springbootbasics.transaction.AccountService;

@SpringBootTest
public class AccountServiceUnitTest {

	@Autowired
	private AccountService accountService;

	@Mock
	private AccountRepository accountRepository;

	private static int MOCK_ACCOUNT_ID = 123;

	private static String MOCK_USERNAME = "Sumit Kumar";

	@Test
	public void testCreateSavingsAccount() {
		// Setup our mock repository
		Account mockAccount = new Account(MOCK_ACCOUNT_ID, MOCK_USERNAME, new BigDecimal(10));
		doReturn(mockAccount).when(accountRepository).save(mockAccount);

		// Execute the service call
		Account account = accountService.createSavingsAccount(MOCK_ACCOUNT_ID, MOCK_USERNAME);

		// Assert that new account is created
		assertThat(account).isEqualTo(mockAccount);
	}

	@Test
	public void testDepositToSavingsAccount() {
		// Setup our mock repository
		Account mockAccount = new Account(MOCK_ACCOUNT_ID, MOCK_USERNAME, new BigDecimal(10));
		doReturn(mockAccount).when(accountRepository).save(mockAccount);
		doReturn(Optional.of(mockAccount)).when(accountRepository).findById(MOCK_ACCOUNT_ID);

		// Execute the service calls
		Account account = accountService.createSavingsAccount(MOCK_ACCOUNT_ID, MOCK_USERNAME);
		account = accountService.depositToSavingsAccount(MOCK_ACCOUNT_ID, new BigDecimal(5.25));

		// Assert that account deposit is done
		assertThat(account.getBalance().doubleValue()).isEqualTo(new BigDecimal(15.25).doubleValue());
	}

	@Test
	public void testWithdrawFromSavingsAccount() {
		// Setup our mock repository
		Account mockAccount = new Account(MOCK_ACCOUNT_ID, MOCK_USERNAME, new BigDecimal(10));
		doReturn(mockAccount).when(accountRepository).save(mockAccount);
		doReturn(Optional.of(mockAccount)).when(accountRepository).findById(MOCK_ACCOUNT_ID);

		// Execute the service calls
		Account account = accountService.createSavingsAccount(MOCK_ACCOUNT_ID, MOCK_USERNAME);
		account = accountService.depositToSavingsAccount(MOCK_ACCOUNT_ID, new BigDecimal(5));
		account = accountService.withdrawFromSavingsAccount(MOCK_ACCOUNT_ID, new BigDecimal(2.30));

		// Assert that account deposit is done
		assertThat(account.getBalance().doubleValue()).isEqualTo(new BigDecimal(12.7).doubleValue());
	}

}
