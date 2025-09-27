package com.example.bankmanagement.dto;

/**
 * Data Transfer Object for Account information.
 * This is the object that is exposed to the web layer (Thymeleaf).
 * I have added the 'id' field to this DTO.
 */
public class AccountDto {

    private Long id; // <<< THIS FIELD WAS ADDED
    private String holderName;
    private String accountNumber;
    private String branchName;
    private double currentBalance;
    private String accountType;
    private String contact;

    // --- Getters and Setters ---

    public Long getId() { // <<< THIS GETTER WAS ADDED
        return id;
    }

    public void setId(Long id) { // <<< THIS SETTER WAS ADDED
        this.id = id;
    }

    public String getHolderName() {
        return holderName;
    }

    public void setHolderName(String holderName) {
        this.holderName = holderName;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    public double getCurrentBalance() {
        return currentBalance;
    }

    public void setCurrentBalance(double currentBalance) {
        this.currentBalance = currentBalance;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }
}

