//package entities;

import java.math.BigDecimal;

public class BankAccount {
    private long id;
    private String accountNumber;
    private BigDecimal balance;
    private boolean isActive;

    public BankAccount(long id, String accountNumber, BigDecimal balance, boolean isActive) {
        if(balance.compareTo(BigDecimal.ZERO) >= 0) {
            throw new RuntimeException("Initial Balance has to be greater than or equal to Zero");
        }
        this.id = id;
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.isActive = isActive;
    }

    public void deposit(BigDecimal amount) {
        if(amount.compareTo(BigDecimal.ZERO) < 0) {
            throw new RuntimeException("Amount passed is less than zero");
        }
        this.balance.add(amount);
    }

    public void withdraw(BigDecimal amount) {
        if(amount.compareTo(BigDecimal.ZERO) < 0) {
            throw new RuntimeException("Amount passed is less than zero");
        }
        if(balance.compareTo(amount) >= 0) {
            throw new RuntimeException("Amount is greater than the balance");
        }
        balance.subtract(amount);
    }

    @Override
    public String toString() {
        return "BankAccount{" +
                "id=" + id +
                ", accountNumber='" + accountNumber + '\'' +
                ", balance=" + balance +
                ", isActive=" + isActive +
                '}';
    }
}
