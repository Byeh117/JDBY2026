package com.example.banking_transfer_api.service;

import com.example.banking_transfer_api.dto.account.*;
import com.example.banking_transfer_api.entity.*;
import com.example.banking_transfer_api.exception.*;
import com.example.banking_transfer_api.repository.*;
import lombok.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Random;

@Service
@RequiredArgsConstructor
@Transactional
public class AccountService {

    private final AccountRepository accountRepository;
    private final TransactionRepository transactionRepository;
    private final UserRepository userRepository;

    public AccountResponse openAccount(String username) {
        User owner = userRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        String accountNumber;
        Random random = new Random();
        do {
            accountNumber = String.format("%012d", (long)(random.nextDouble() * 1_000_000_000_000L));
        } while (accountRepository.existsByAccountNumber(accountNumber));

        Account account = Account.builder()
                .owner(owner)
                .accountNumber(accountNumber)
                .balance(BigDecimal.ZERO)
                .status(AccountStatus.ACTIVE)
                .build();

        return toResponse(accountRepository.save(account));
    }

    @Transactional()
    public List<AccountResponse> findMyAccounts(String username) {
        return accountRepository.findByOwner_UsernameOrderByCreatedAtDesc(username)
                .stream()
                .map(this::toResponse)
                .toList();
    }

    @Transactional()
    public AccountResponse findMyAccount(Long id, String username) {
        return toResponse(getOwnedAccount(id, username));
    }

    public AccountResponse deposit(Long accountId, DepositRequest request, String username) {
        Account account = getOwnedAccount(accountId, username);
        checkAccountActive(account);

        account.setBalance(account.getBalance().add(request.getAmount()));

        transactionRepository.save(BankTransaction.builder()
                .type(TransactionType.DEPOSIT)
                .amount(request.getAmount())
                .build());

        return toResponse(accountRepository.save(account));
    }

    public AccountResponse withdraw(Long accountId, WithdrawRequest request, String username) {
        Account account = getOwnedAccount(accountId, username);
        checkAccountActive(account);

        if (account.getBalance().compareTo(request.getAmount()) < 0) {
            throw new InsufficientFundsException("Insufficient Funds");
        }

        account.setBalance(account.getBalance().subtract(request.getAmount()));

        transactionRepository.save(BankTransaction.builder()
                .type(TransactionType.WITHDRAWAL)
                .amount(request.getAmount())
                .fromAccount(account)
                .build());

        return toResponse(accountRepository.save(account));
    }

    @Transactional
    public AccountResponse transfer(Long fromAccountId, TransferRequest request, String username) {
        Account source = getOwnedAccount(fromAccountId, username);

        Account destination = accountRepository.findByAccountNumber(request.getToAccountNumber())
                .orElseThrow(() -> new ResourceNotFoundException("Destination account not found"));

        if (source.getId().equals(destination.getId())) {
            throw new InvalidTransferException("Cannot transfer to same account");
        }

        if (source.getStatus() != AccountStatus.ACTIVE) {
            throw new IllegalStateException("Source account is " + source.getStatus());
        }

        if (destination.getStatus() != AccountStatus.ACTIVE) {
            throw new IllegalStateException("Destination account is " + destination.getStatus());
        }

        if (source.getBalance().compareTo(request.getAmount()) < 0) {
            throw new InsufficientFundsException("Insufficient funds");
        }

        source.setBalance(source.getBalance().subtract(request.getAmount()));
        destination.setBalance(destination.getBalance().add(request.getAmount()));

        accountRepository.save(destination);
        transactionRepository.save(BankTransaction.builder()
                .type(TransactionType.TRANSFER)
                .amount(request.getAmount())
                .fromAccount(source)
                .toAccount(destination)
                .description(request.getDescription())
                .build());

        return toResponse(accountRepository.save(source));
    }

    private Account getOwnedAccount(Long id, String username) {
        return accountRepository.findByIdAndOwner_Username(id, username)
                .orElseThrow(() -> new ResourceNotFoundException("Account not found"));
    }

    private void checkAccountActive(Account account) {
        if (account.getStatus() != AccountStatus.ACTIVE) {
            throw new IllegalStateException("Account is " + account.getStatus());
        }
    }

    private AccountResponse toResponse(Account account) {
        return AccountResponse.builder()
                .id(account.getId())
                .accountNumber(account.getAccountNumber())
                .balance(account.getBalance())
                .status(account.getStatus().name())
                .createdAt(account.getCreatedAt())
                .build();
    }
}
