package entities;

import java.math.BigDecimal;

public interface Transferable {
    void transfer(BigDecimal amount, String toAccountNumber);

    default boolean canTransfer(BigDecimal amount) {
        return false;
    }

    Comparable<BigDecimal> getBalance();
}
