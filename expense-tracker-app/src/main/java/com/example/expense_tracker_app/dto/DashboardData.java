package com.example.expense_tracker_app.dto;

import com.example.expense_tracker_app.entity.Transaction;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public record DashboardData(
        LocalDate start,
        LocalDate end,
        BigDecimal totalExpenses,
        List<CategorySpendRow> topCategories,
        List<Transaction> recentTransactions
) {
}
