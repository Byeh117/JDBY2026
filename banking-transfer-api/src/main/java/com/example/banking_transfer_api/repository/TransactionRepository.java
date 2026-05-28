package com.example.banking_transfer_api.repository;

import com.example.banking_transfer_api.entity.BankTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TransactionRepository extends JpaRepository<BankTransaction, Long> {

    @Query("""
        SELECT t FROM BankTransaction t
        WHERE t.fromAccount.id = :accountId OR t.toAccount.id = :accountId
        ORDER BY t.occurredAt DESC
    """)
    List<BankTransaction> findHistoryForAccount(@Param("accountId") Long accountId);
}
