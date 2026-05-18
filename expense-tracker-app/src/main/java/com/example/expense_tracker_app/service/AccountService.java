package com.example.expense_tracker_app.service;

import com.example.expense_tracker_app.dto.AccountForm;
import com.example.expense_tracker_app.entity.Account;
import com.example.expense_tracker_app.entity.AccountType;
import com.example.expense_tracker_app.repository.AccountRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class AccountService {

    private final AccountRepository accountRepository;

    @Transactional()
    public List<Account> findAll(String name) {
        return accountRepository.findAllByName(name); }

    @Transactional()
    public Account findById(Long id) {
        return accountRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Account not found: " + id));
    }

    public Account create(AccountForm accountForm) {
        Account account = new Account();
        account.setName(accountForm.getName());
        account.setType(AccountType.valueOf(accountForm.getType()));
        account.setOpeningBalance(accountForm.getOpeningBalance());
        return accountRepository.save(account);
    }

    public Account update(Long id, AccountForm accountForm) {
        Account account = findById(id);
        account.setName(accountForm.getName());
        account.setType(AccountType.valueOf(accountForm.getType()));
        account.setOpeningBalance(accountForm.getOpeningBalance());
        return accountRepository.save(account);
    }

    public void delete(Long id) {
        if (!accountRepository.existsById(id)) {
            throw new EntityNotFoundException("Account not found: " + id);
        }
        accountRepository.deleteById(id);
    }
}
