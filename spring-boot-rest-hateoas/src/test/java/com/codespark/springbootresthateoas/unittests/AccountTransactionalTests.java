package com.codespark.springbootresthateoas.unittests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.math.BigDecimal;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.codespark.springbootresthateoas.transactions.Account;
import com.codespark.springbootresthateoas.transactions.AccountRepository;
import com.codespark.springbootresthateoas.transactions.AccountService;

@SpringBootTest
public class AccountTransactionalTests {

    private static final int ACCOUNT_ID = 1234;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private AccountService accountService;

    @BeforeEach
    public void setup() {
        // Give new account a balance of $200
        accountService.createAccount("John Doe", 200.00);
    }

    @Test
    public void assertWithdrawAmountsTransactional() {
        try {
            // When withdrawing $300 from account using transactional
            accountService.withdrawAmountsTransactional(ACCOUNT_ID, List.of(100.00, 200.00));
        } catch (Exception e) {
            Account account = accountRepository.findById(ACCOUNT_ID)
                    .orElseThrow(() -> new RuntimeException("Account not found."));

            // Assert that transaction was rolled back
            assertNotNull(account);
            assertEquals("John Doe", account.getName());
            assertEquals(new BigDecimal("200.00"), account.getBalance());
        }
    }

}
