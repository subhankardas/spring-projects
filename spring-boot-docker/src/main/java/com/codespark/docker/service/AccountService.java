package com.codespark.docker.service;

import com.codespark.docker.constant.AccountType;
import com.codespark.docker.dto.AccountDTO;
import com.codespark.docker.entity.Account;
import com.codespark.docker.repository.AccountRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.List;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private ModelMapper mapper;

    public List<AccountDTO> accounts() {
        List<Account> accounts = accountRepository.findAll();

        Type accountsListType = new TypeToken<List<AccountDTO>>() {
        }.getType();

        return mapper.map(accounts, accountsListType);
    }

    public AccountDTO createNewAccount(AccountDTO accountDTO) {
        Account account = mapper.map(accountDTO, Account.class);
        account.setType(AccountType.SAVINGS);

        account = accountRepository.save(account);
        accountDTO = mapper.map(account, AccountDTO.class);

        return accountDTO;
    }
}
