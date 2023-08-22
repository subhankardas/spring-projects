package com.codespark.springbootresthateoas.transactions;

import java.math.BigDecimal;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AccountService {

    private static final Logger log = LoggerFactory.getLogger(AccountService.class);
    private static final int ACCOUNT_ID = 1234;

    @Autowired
    private AccountRepository accountRepository;

    public Account createAccount(String name, Double balance) {
        log.info("Creating account {} with balance {}", name, balance);

        Account newAccount = new Account();
        newAccount.setId(ACCOUNT_ID);
        newAccount.setName(name);
        newAccount.setBalance(new BigDecimal(balance));

        return accountRepository.save(newAccount);
    }

    public void withdrawAmountsNonTransactional(int accountId, List<Double> amounts) {
        log.info("Withdrawing amounts {} from account {}", amounts, accountId);

        Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> new RuntimeException("Account not found."));

        for (Double amount : amounts) {
            account.setBalance(account.getBalance().subtract(new BigDecimal(amount)));
            accountRepository.save(account);
        }
    }

    /**
     * Using '@Transactional' annotation, this method will be executed as a single
     * atomic transaction. If any exception is thrown, the transaction will be
     * rolled back to make sure that the balance is not distorted by the exception.
     * 
     * Hence we can wrap multiple database operations in a single transaction to
     * ensure that they either succeed complete or failed with rollback in case of
     * any exception.
     */
    @Transactional
    public void withdrawAmountsTransactional(int accountId, List<Double> amounts) {
        log.info("Withdrawing amounts {} from account {}", amounts, accountId);

        Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> new RuntimeException("Account not found."));

        for (Double amount : amounts) {
            account.setBalance(account.getBalance().subtract(new BigDecimal(amount)));
            accountRepository.save(account);
        }
    }

}
