package com.example.banking_transfer_api.controller;

import com.example.banking_transfer_api.dto.account.*;
import com.example.banking_transfer_api.dto.transaction.TransactionResponse;
import com.example.banking_transfer_api.service.AccountService;
import com.example.banking_transfer_api.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/account")
@RequiredArgsConstructor
public class AccountController {
    private final AccountService accountService;
    private final TransactionService transactionService;

    @GetMapping
    public List<AccountResponse> list(
            @AuthenticationPrincipal UserDetails user) {
        return accountService.findMyAccounts(user.getUsername());
    }

    @GetMapping("/{id}")
    public AccountResponse get(@PathVariable Long id,
                               @AuthenticationPrincipal UserDetails user) {
        return accountService.findMyAccount(id, user.getUsername());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AccountResponse openAccount(@RequestBody OpenAccountRequest request,
                                       @AuthenticationPrincipal UserDetails user) {
        return accountService.openAccount(user.getUsername());
    }

    @PostMapping("/{id}/deposits")
    public AccountResponse deposit(@PathVariable Long id,
                                   @RequestBody DepositRequest request,
                                   @AuthenticationPrincipal UserDetails user) {
        return accountService.deposit(id, request, user.getUsername());
    }

    @PostMapping("/{id}/withdrawals")
    public AccountResponse withdraw(@PathVariable Long id,
                                    @RequestBody WithdrawRequest request,
                                    @AuthenticationPrincipal UserDetails user) {
        return accountService.withdraw(id, request, user.getUsername());
    }

    @PostMapping("/{id}/transfers")
    public AccountResponse transfer(@PathVariable Long id,
                                    @RequestBody TransferRequest request,
                                    @AuthenticationPrincipal UserDetails user) {
        return accountService.transfer(id, request, user.getUsername());
    }

    @GetMapping("/{id}/transactions")
    public List<TransactionResponse> list(
            @PathVariable Long id,
            @AuthenticationPrincipal UserDetails user) {
        return transactionService.findHistoryForAccount(id, user.getUsername());
    }
}
