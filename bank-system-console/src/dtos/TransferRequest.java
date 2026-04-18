package dtos;

import enums.Priority;

import java.math.BigDecimal;

public class TransferRequest {
    private final String fromAccount;
    private final String toAccount;
    private final BigDecimal amount;

    private String note;
    private String reference;
    private Priority priority;

    private TransferRequest(Builder builder) {
        this.fromAccount = builder.fromAccount;
        this.toAccount = builder.toAccount;
        this.amount = builder.amount;
        this.note = builder.note;
        this.reference = builder.reference;
        this.priority = builder.priority;
    }

    public String getFromAccount() {
        return fromAccount;
    }

    public String getToAccount() {
        return toAccount;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    public static class Builder {
        private final String fromAccount;
        private final String toAccount;
        private final BigDecimal amount;

        private String note;
        private String reference;
        private Priority priority;

        public Builder(String fromAccount, String toAccount, BigDecimal amount) {
            this.fromAccount = fromAccount;
            this.toAccount = toAccount;
            this.amount = amount;
        }

        public Builder note(String note) {
            this.note = note;
            return this;
        }

        public Builder reference(String reference) {
            this.reference = reference;
            return this;
        }

        public Builder priority(Priority priority) {
            this.priority = priority;
            return this;
        }

        public TransferRequest build() {
            return new TransferRequest(this);
        }
    }
}