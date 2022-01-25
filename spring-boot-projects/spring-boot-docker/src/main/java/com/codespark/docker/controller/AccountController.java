package com.codespark.docker.controller;

import com.codespark.docker.dto.AccountDTO;
import com.codespark.docker.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("accounts")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @GetMapping
    public List<AccountDTO> accounts() {
        return accountService.accounts();
    }

    @PostMapping
    public AccountDTO createNewAccount(@RequestBody AccountDTO accountDTO) {
        return accountService.createNewAccount(accountDTO);
    }

}
