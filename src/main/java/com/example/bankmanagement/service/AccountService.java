package com.example.bankmanagement.service;

import com.example.bankmanagement.dto.AccountDto;
import com.example.bankmanagement.dto.CreateAccountRequest;

import java.util.List;
import java.util.Optional;

public interface AccountService {
    AccountDto createAccount(CreateAccountRequest createAccountRequest);
    Optional<AccountDto> getAccountById(Long id);
    List<AccountDto> getAllAccounts();
    void deleteAccount(Long id);
}

