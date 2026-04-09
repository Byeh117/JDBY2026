package entities;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;

public class SavingsAccount extends BankAccount implements Transferable {
    private BigDecimal interestRate;

    public SavingsAccount(String accountNumber, BigDecimal balance, BigDecimal interestRate) {
        super(accountNumber, balance);
        this.interestRate = interestRate;
    }

    @Override
    public void applyMonthlyFee() {
        applyInterest();
    }

    @Override
    public void transfer(BigDecimal amount, String toAccountNumber) {
        withdraw(amount);
    }

    private void applyInterest() {
        BigDecimal result = (super.getBalance().multiply(interestRate))
                .divide(new BigDecimal("100"), RoundingMode.HALF_EVEN);
        super.getBalance().add(result);
    }

    public BigDecimal getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(BigDecimal interestRate) {
        this.interestRate = interestRate;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof SavingsAccount)) return false;
        SavingsAccount that = (SavingsAccount) o;
        return Objects.equals(getInterestRate(), that.getInterestRate());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getInterestRate());
    }

    @Override
    public String toString() {
        return "SavingsAccount{" +
                "interestRate=" + interestRate +
                '}';
    }
}