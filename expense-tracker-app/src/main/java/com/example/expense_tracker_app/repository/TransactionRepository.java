package com.example.expense_tracker_app.repository;

import com.example.expense_tracker_app.dto.CategorySpendRow;
import com.example.expense_tracker_app.entity.Transaction;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    List<Transaction> findAllByOccurredOnBetweenOrderByOccurredOnDesc(LocalDate start, LocalDate end);

    List<Transaction>
    findByOccurredOnBetweenAndCategory_IdOrderByOccurredOnDesc(LocalDate start, LocalDate end, Long categoryId);

    List<Transaction> findTop10ByOrderByOccurredOnDescIdDesc();

    long countByCategory_Id(Long categoryId);

    @Query("""
        SELECT SUM(t.amount)
        FROM Transaction t
        WHERE t.category.type = 'EXPENSE'
        AND t.occurredOn BETWEEN :start AND :end
    """)
    BigDecimal sumExpensesBetween(
            @Param("start") LocalDate start,
            @Param("end") LocalDate end
    );

    @Query("""
        SELECT new com.example.expense_tracker_app.dto.CategorySpendRow(c.name, SUM(t.amount))
        FROM Transaction t
        JOIN t.category c
        WHERE c.type = 'EXPENSE'
        AND t.occurredOn BETWEEN :start AND :end
        GROUP BY c.name
        ORDER BY SUM(t.amount) DESC
    """)
    List<CategorySpendRow> topExpenseCategoriesBetween(
            @Param("start") LocalDate start,
            @Param("end") LocalDate end,
            Pageable pageable
    );

}
