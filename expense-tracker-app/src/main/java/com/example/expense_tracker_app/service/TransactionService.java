package com.example.expense_tracker_app.service;

import com.example.expense_tracker_app.dto.TransactionForm;
import com.example.expense_tracker_app.entity.Account;
import com.example.expense_tracker_app.entity.Category;
import com.example.expense_tracker_app.entity.Transaction;
import com.example.expense_tracker_app.repository.AccountRepository;
import com.example.expense_tracker_app.repository.CategoryRepository;
import com.example.expense_tracker_app.repository.TransactionRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class TransactionService {

    private final TransactionRepository transactionRepository;
    private final AccountRepository accountRepository;
    private final CategoryRepository categoryRepository;

    public Transaction findById(long id) {
        return transactionRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Transaction not found: " + id));
    }

    public List<Transaction> findFiltered(LocalDate start, LocalDate end, Long categoryId) {
        if (start == null) start = LocalDate.now().withDayOfMonth(1);
        if (end == null) end = LocalDate.now().withDayOfMonth(LocalDate.now().lengthOfMonth());

        return categoryId != null
                ? transactionRepository.findByOccurredOnBetweenAndCategory_IdOrderByOccurredOnDesc(start, end, categoryId)
                : transactionRepository.findAllByOccurredOnBetweenOrderByOccurredOnDesc(start, end);
    }

    public Transaction create(TransactionForm form) {
        Account account = accountRepository.findById(form.getAccountId())
                .orElseThrow(() -> new EntityNotFoundException("Account not found: " + form.getAccountId()));
        Category category = categoryRepository.findById(form.getCategoryId())
                .orElseThrow(() -> new EntityNotFoundException("Category not found: " + form.getCategoryId()));

        Transaction transaction = new Transaction();
        transaction.setAmount(form.getAmount());
        transaction.setOccurredOn(form.getOccurredOn());
        transaction.setNote(form.getNote());
        transaction.setAccount(account);
        transaction.setCategory(category);
        return transactionRepository.save(transaction);
    }

    public Transaction update(Long id, TransactionForm form) {
        Transaction transaction = findById(id);

        Account account = accountRepository.findById(form.getAccountId())
                .orElseThrow(() -> new EntityNotFoundException("Account not found: " + form.getAccountId()));
        Category category = categoryRepository.findById(form.getCategoryId())
                .orElseThrow(() -> new EntityNotFoundException("Category not found: " + form.getCategoryId()));

        transaction.setAmount(form.getAmount());
        transaction.setOccurredOn(form.getOccurredOn());
        transaction.setNote(form.getNote());
        transaction.setAccount(account);
        transaction.setCategory(category);
        return transactionRepository.save(transaction);
    }

    public void delete(Long id) {
        if (!transactionRepository.existsById(id)) {
            throw new EntityNotFoundException("Transaction not found: " + id);
        }
        transactionRepository.deleteById(id);
    }
}
