package com.codespark.docker.dto;

import com.codespark.docker.constant.AccountType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountDTO {

    private Long id;

    private String firstname;
    private String lastname;

    private AccountType type;

}
