package entities;

import java.math.BigDecimal;
import java.util.Objects;

public class CheckingsAccount extends BankAccount {
    private BigDecimal monthlyFee;

    public CheckingsAccount(String accountNumber, BigDecimal balance, BigDecimal monthlyFee) {
        super(accountNumber, balance);
        this.monthlyFee = monthlyFee;
    }

    public void applyMonthlyFee() {
        super.getBalance().subtract(monthlyFee);
    }

    public BigDecimal getMonthlyFee() {
        return monthlyFee;
    }

    public void setMonthlyFee(BigDecimal monthlyFee) {
        this.monthlyFee = monthlyFee;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof CheckingsAccount)) return false;
        CheckingsAccount that = (CheckingsAccount) o;
        return Objects.equals(getMonthlyFee(), that.getMonthlyFee());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getMonthlyFee());
    }

    @Override
    public String toString() {
        return "CheckingAccount{" +
                "monthlyFee=" + monthlyFee +
                '}';
    }
}