package com.example.banking_transfer_api.repository;

import com.example.banking_transfer_api.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Long> {
    List<Account> findByOwner_UsernameOrderByCreatedAtDesc(String username);

    Optional<Account> findByIdAndOwner_Username(Long id, String username);

    Optional<Account> findByAccountNumber(String accountNumber);

    boolean existsByAccountNumber(String accountNumber);
}
