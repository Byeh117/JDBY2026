package com.example.expense_tracker_app.service;

import com.example.expense_tracker_app.dto.CategorySpendRow;
import com.example.expense_tracker_app.dto.DashboardData;
import com.example.expense_tracker_app.entity.Transaction;
import com.example.expense_tracker_app.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class DashboardService {

    private final TransactionRepository transactionRepository;

    public DashboardData currentMonth() {
        LocalDate today = LocalDate.now();
        LocalDate start = today.withDayOfMonth(1);
        LocalDate end = today.withDayOfMonth(today.lengthOfMonth());

        BigDecimal total = transactionRepository.sumExpensesBetween(start, end);
        if (total == null) total = BigDecimal.ZERO;

        List<CategorySpendRow> topCategories =
                transactionRepository.topExpenseCategoriesBetween(start, end, PageRequest.of(0, 5));
        List<Transaction> recent =
                transactionRepository.findTop10ByOrderByOccurredOnDescIdDesc();

        return new DashboardData(start, end, total, topCategories, recent);
    }
}
