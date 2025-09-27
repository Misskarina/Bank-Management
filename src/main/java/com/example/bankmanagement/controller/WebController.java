package com.example.bankmanagement.controller;

import com.example.bankmanagement.dto.AccountDto;
import com.example.bankmanagement.dto.CreateAccountRequest;
import com.example.bankmanagement.service.AccountService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

/**
 * This controller handles requests for the web interface (HTML pages).
 */
@Controller
public class WebController {

    private final AccountService accountService;

    public WebController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping("/")
    public String viewLoginPage() {
        return "login";
    }

    @PostMapping("/login")
    public String handleLogin(@RequestParam String accountNumber, @RequestParam String password) {
        // In a real application, you would validate the account number and password here.
        System.out.println("Attempting login with Account No: " + accountNumber);
        return "redirect:/accounts";
    }

    @GetMapping("/accounts")
    public String viewAccountsPage(Model model) {
        List<AccountDto> accounts = accountService.getAllAccounts();
        model.addAttribute("accounts", accounts);
        return "accounts";
    }

    @GetMapping("/add-account")
    public String showAddAccountForm() {
        return "add-account";
    }

    /**
     * Processes the submission of the "Add New Account" form.
     * Creates the account and redirects back to the main accounts list
     * with a success message.
     * @param createAccountRequest An object that captures all the form data.
     * @param redirectAttributes Used to pass attributes to a redirect target.
     * @return A redirect to the main accounts page.
     */
    @PostMapping("/add-account")
    public String saveAccount(@ModelAttribute CreateAccountRequest createAccountRequest, RedirectAttributes redirectAttributes) {
        accountService.createAccount(createAccountRequest);
        // Add a "flash attribute" that will be available after the redirect.
        redirectAttributes.addFlashAttribute("successMessage", "Account created successfully!");
        return "redirect:/accounts";
    }

    @GetMapping("/accounts/view/{id}")
    public String viewSingleAccount(@PathVariable Long id, Model model) {
        Optional<AccountDto> accountOptional = accountService.getAccountById(id);
        if (accountOptional.isPresent()) {
            model.addAttribute("account", accountOptional.get());
            return "view-account";
        } else {
            return "redirect:/accounts";
        }
    }
    
    @PostMapping("/accounts/delete/{id}")
    public String deleteAccount(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        accountService.deleteAccount(id);
        redirectAttributes.addFlashAttribute("successMessage", "Account deleted successfully!");
        return "redirect:/accounts";
    }
}

