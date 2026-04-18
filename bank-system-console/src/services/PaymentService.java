package services;

import utils.AuditLogger;
import utils.TransactionValidator;

public class PaymentService {
    private final TransactionValidator transactionValidator;
    private final AuditLogger logger;

    public PaymentService(TransactionValidator transactionValidator, AuditLogger logger) {
        this.transactionValidator = transactionValidator;
        this.logger = logger;
    }
}