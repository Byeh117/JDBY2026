package com.example.banking_transfer_api.service;

import com.example.banking_transfer_api.dto.account.AccountResponse;
import com.example.banking_transfer_api.dto.account.TransferRequest;
import com.example.banking_transfer_api.dto.transaction.TransactionResponse;
import com.example.banking_transfer_api.entity.Account;
import com.example.banking_transfer_api.entity.AccountStatus;
import com.example.banking_transfer_api.entity.BankTransaction;
import com.example.banking_transfer_api.entity.TransactionType;
import com.example.banking_transfer_api.exception.InsufficientFundsException;
import com.example.banking_transfer_api.exception.InvalidTransferException;
import com.example.banking_transfer_api.exception.ResourceNotFoundException;
import com.example.banking_transfer_api.repository.AccountRepository;
import com.example.banking_transfer_api.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class TransactionService {

    private final TransactionRepository transactionRepository;
    private final AccountRepository accountRepository;

    public List<TransactionResponse> findHistoryForAccount(Long accountId, String username) {
        accountRepository.findByIdAndOwner_Username(accountId, username)
                .orElseThrow(() -> new ResourceNotFoundException("Account not found"));

        return transactionRepository.findHistoryForAccount(accountId)
                .stream()
                .map(this::toResponse)
                .toList();
    }

    private TransactionResponse toResponse(BankTransaction t) {
        return TransactionResponse.builder()
                .id(t.getId())
                .type(t.getType().name())
                .amount(t.getAmount())
                .fromAccountNumber(t.getFromAccount().getAccountNumber())
                .toAccountNumber(t.getToAccount() != null ? t.getToAccount().getAccountNumber() : null)
                .description(t.getDescription())
                .occurredAt(t.getOccurredAt())
                .build();
    }
}
