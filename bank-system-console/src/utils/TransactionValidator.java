package utils;

import entities.BankAccount;

import java.math.BigDecimal;

public class TransactionValidator {
    public boolean isValid(BigDecimal amount, BankAccount account) {
        return amount.compareTo(BigDecimal.ZERO) > 0
                && amount.compareTo(account.getBalance()) <= 0;
    }
}