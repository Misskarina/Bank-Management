package com.example.bankmanagement.repository;

import com.example.bankmanagement.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
    Optional<Account> findByAccountNumberAndPassword(String accountNumber, String password);
    Optional<Account> findByAccountNumber(String accountNumber);
}

