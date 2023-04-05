package com.codespark.springbootresthateoas.transactions;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class TransactionBootstrap implements CommandLineRunner {

    private static final Logger log = LoggerFactory.getLogger(TransactionBootstrap.class);

    @Autowired
    private AccountService accountService;

    @Override
    public void run(String... args) throws Exception {
        accountService.createAccount("John Doe", 200.00);

        // withdrawAmountsNonTransactional(); // DOES NOT USES TRANSACTIONAL METHOD
        // withdrawAmountsTransactional(); // USES TRANSACTIONAL METHOD
    }

    public void withdrawAmountsNonTransactional() {
        try {
            // After account created with $200, we try to withdraw $100 and $200 this will
            // result in an exception. By using non-transactional approach, first we
            // withdraw $100 and then failed for $200 because current balance will be $100
            // and we can go below minimum balance.
            //
            // Since there is no rollback, balance will be $100 i.e. $200-$100 which is
            // wrong.
            accountService.withdrawAmountsNonTransactional(1234, List.of(100.00, 200.00));
        } catch (Exception e) {
            log.error("Error while withdrawing amounts with non-transactional approach", e);
        }
    }

    public void withdrawAmountsTransactional() {
        try {
            // After account created with $200, we try to withdraw $100 and $200 this will
            // result in an exception. But since we are using transactional approach, the
            // first deduction is rolled back after the second deduction of $200 fails due
            // to minimum balance criteria.
            //
            // Since there is a rolled back, balance will be $200 i.e. this transaction will
            // be performed atomically i.e. either it will be successful or it will be
            // failed completely.
            accountService.withdrawAmountsTransactional(1234, List.of(100.00, 200.00));
        } catch (Exception e) {
            log.error("Error while withdrawing amounts with transactional approach", e);
        }
    }

}
