package entities;

import java.math.BigDecimal;

public interface Transferable {
    void transfer(BigDecimal amount, String toAccountNumber);
    BigDecimal getBalance();
    default boolean canTransfer(BigDecimal amount) {
        return getBalance().compareTo(amount) <= 0;
    }
}
