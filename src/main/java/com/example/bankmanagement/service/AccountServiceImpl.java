package com.example.bankmanagement.service;

import com.example.bankmanagement.dto.AccountDto;
import com.example.bankmanagement.dto.CreateAccountRequest;
import com.example.bankmanagement.model.Account;
import com.example.bankmanagement.repository.AccountRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * This class contains the business logic for account management.
 * It implements the AccountService interface and interacts with the AccountRepository.
 */
@Service
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;

    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    /**
     * Creates a new account based on the request data, saves it to the database,
     * and returns the created account as a DTO.
     * @param request The request object containing the new account details.
     * @return An AccountDto of the newly created account.
     */
    @Override
    public AccountDto createAccount(CreateAccountRequest request) {
        // In a real application, you would add validation here (e.g., check if account number already exists)
        
        Account account = new Account();
        account.setHolderName(request.getHolderName());
        account.setAccountNumber(request.getAccountNumber());
        account.setBranchName(request.getBranchName());
        account.setCurrentBalance(request.getCurrentBalance());
        account.setAccountType(request.getAccountType());
        account.setContact(request.getContact());
        account.setPassword(request.getPassword()); // In a real app, you would hash this password
        
        Account savedAccount = accountRepository.save(account);
        return mapToDto(savedAccount);
    }

    /**
     * Retrieves a single account by its ID.
     * @param id The ID of the account to find.
     * @return An Optional containing the AccountDto if found, otherwise an empty Optional.
     */
    @Override
    public Optional<AccountDto> getAccountById(Long id) {
        return accountRepository.findById(id).map(this::mapToDto);
    }

    /**
     * Retrieves a list of all accounts from the database.
     * @return A List of AccountDto objects.
     */
    @Override
    public List<AccountDto> getAllAccounts() {
        return accountRepository.findAll().stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    /**
     * Deletes an account from the database by its ID.
     * @param id The ID of the account to delete.
     */
    @Override
    public void deleteAccount(Long id) {
        // You might want to check if the account exists before trying to delete it
        if (!accountRepository.existsById(id)) {
            // In a real app, you might throw a custom exception here
            throw new IllegalStateException("Account with ID " + id + " not found.");
        }
        accountRepository.deleteById(id);
    }

    /**
     * Private helper method to convert an Account entity object to an AccountDto.
     * This ensures that the internal database entity is not exposed directly to the web layer.
     * @param account The Account entity to convert.
     * @return The corresponding AccountDto.
     */
    private AccountDto mapToDto(Account account) {
        AccountDto dto = new AccountDto();
        dto.setId(account.getId()); // This line is crucial for the delete/view buttons to work
        dto.setHolderName(account.getHolderName());
        dto.setAccountNumber(account.getAccountNumber());
        dto.setBranchName(account.getBranchName());
        dto.setCurrentBalance(account.getCurrentBalance());
        dto.setAccountType(account.getAccountType());
        dto.setContact(account.getContact());
        return dto;
    }
}

