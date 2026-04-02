-- ============================================================
-- JD Bank System — Full Schema + Dummy Data
-- Database:  jd_bank_system
-- Engine:    MySQL 8.x
-- ============================================================

DROP DATABASE IF EXISTS jd_bank_system;
CREATE DATABASE jd_bank_system;
USE jd_bank_system;

-- ============================================================
-- 1. CUSTOMERS
-- ============================================================
CREATE TABLE customers (
                           id          BIGINT AUTO_INCREMENT PRIMARY KEY,
                           email       VARCHAR(255) NOT NULL UNIQUE,
                           first_name  VARCHAR(100) NOT NULL,
                           last_name   VARCHAR(100) NOT NULL,
                           created_at  TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- ============================================================
-- 2. ACCOUNTS
-- ============================================================
CREATE TABLE accounts (
                          id              BIGINT AUTO_INCREMENT PRIMARY KEY,
                          customer_id     BIGINT NOT NULL,
                          account_number  VARCHAR(20) NOT NULL UNIQUE,
                          type            ENUM('CHECKING', 'SAVINGS', 'BUSINESS') NOT NULL,
                          balance         DECIMAL(15,2) NOT NULL DEFAULT 0.00,
                          status          ENUM('ACTIVE', 'FROZEN', 'CLOSED') NOT NULL DEFAULT 'ACTIVE',

                          CONSTRAINT fk_accounts_customer
                              FOREIGN KEY (customer_id) REFERENCES customers(id)
                                  ON DELETE CASCADE
);

-- ============================================================
-- 3. TRANSACTIONS
-- ============================================================
CREATE TABLE transactions (
                              id               BIGINT AUTO_INCREMENT PRIMARY KEY,
                              from_account_id  BIGINT,
                              to_account_id    BIGINT,
                              amount           DECIMAL(15,2) NOT NULL,
                              type             ENUM('DEPOSIT', 'WITHDRAWAL', 'TRANSFER', 'PAYMENT') NOT NULL,
                              created_at       TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

                              CONSTRAINT fk_txn_from_account
                                  FOREIGN KEY (from_account_id) REFERENCES accounts(id)
                                      ON DELETE SET NULL,
                              CONSTRAINT fk_txn_to_account
                                  FOREIGN KEY (to_account_id) REFERENCES accounts(id)
                                      ON DELETE SET NULL
);

-- ============================================================
-- 4. PAYEES
-- ============================================================
CREATE TABLE payees (
                        id              BIGINT AUTO_INCREMENT PRIMARY KEY,
                        customer_id     BIGINT NOT NULL,
                        name            VARCHAR(200) NOT NULL,
                        account_number  VARCHAR(20) NOT NULL,
                        bank_code       VARCHAR(20) NOT NULL,

                        CONSTRAINT fk_payees_customer
                            FOREIGN KEY (customer_id) REFERENCES customers(id)
                                ON DELETE CASCADE
);

-- ============================================================
-- 5. NOTIFICATIONS
-- ============================================================
CREATE TABLE notifications (
                               id          BIGINT AUTO_INCREMENT PRIMARY KEY,
                               customer_id BIGINT NOT NULL,
                               type        ENUM('ALERT', 'PROMO', 'SECURITY', 'INFO') NOT NULL,
                               message     TEXT NOT NULL,
                               is_read     BOOLEAN NOT NULL DEFAULT FALSE,
                               created_at  TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

                               CONSTRAINT fk_notifications_customer
                                   FOREIGN KEY (customer_id) REFERENCES customers(id)
                                       ON DELETE CASCADE
);

-- ============================================================
-- 6. AUDIT LOGS
-- ============================================================
CREATE TABLE audit_logs (
                            id           BIGINT AUTO_INCREMENT PRIMARY KEY,
                            account_id   BIGINT NOT NULL,
                            action       VARCHAR(100) NOT NULL,
                            performed_by VARCHAR(100) NOT NULL,
                            detail       JSON,
                            created_at   TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

                            CONSTRAINT fk_audit_account
                                FOREIGN KEY (account_id) REFERENCES accounts(id)
                                    ON DELETE CASCADE
);


-- ============================================================
--  DUMMY DATA
-- ============================================================

-- ---------- CUSTOMERS (10) ----------
INSERT INTO customers (id, email, first_name, last_name, created_at) VALUES
                                                                         (1,  'aisha.patel@email.com',      'Aisha',    'Patel',      '2024-01-15 09:30:00'),
                                                                         (2,  'marcus.johnson@email.com',   'Marcus',   'Johnson',    '2024-02-01 11:00:00'),
                                                                         (3,  'yuki.tanaka@email.com',      'Yuki',     'Tanaka',     '2024-02-14 14:22:00'),
                                                                         (4,  'carlos.rivera@email.com',    'Carlos',   'Rivera',     '2024-03-03 08:45:00'),
                                                                         (5,  'fatima.ali@email.com',       'Fatima',   'Ali',        '2024-03-20 16:10:00'),
                                                                         (6,  'james.smith@email.com',      'James',    'Smith',      '2024-04-05 10:00:00'),
                                                                         (7,  'mei.chen@email.com',         'Mei',      'Chen',       '2024-04-18 13:30:00'),
                                                                         (8,  'olivia.brown@email.com',     'Olivia',   'Brown',      '2024-05-02 09:15:00'),
                                                                         (9,  'dmitri.volkov@email.com',    'Dmitri',   'Volkov',     '2024-05-25 17:40:00'),
                                                                         (10, 'sofia.garcia@email.com',     'Sofia',    'Garcia',     '2024-06-10 12:00:00');

-- ---------- ACCOUNTS (15 — some customers have multiple) ----------
INSERT INTO accounts (id, customer_id, account_number, type, balance, status) VALUES
                                                                                  (1,  1,  'JDB-1001-CHK', 'CHECKING',  5240.75,  'ACTIVE'),
                                                                                  (2,  1,  'JDB-1001-SAV', 'SAVINGS',  18500.00,  'ACTIVE'),
                                                                                  (3,  2,  'JDB-1002-CHK', 'CHECKING',  3120.50,  'ACTIVE'),
                                                                                  (4,  3,  'JDB-1003-CHK', 'CHECKING',  9800.00,  'ACTIVE'),
                                                                                  (5,  3,  'JDB-1003-BIZ', 'BUSINESS', 42000.00,  'ACTIVE'),
                                                                                  (6,  4,  'JDB-1004-CHK', 'CHECKING',  1575.20,  'ACTIVE'),
                                                                                  (7,  5,  'JDB-1005-SAV', 'SAVINGS',  27350.00,  'ACTIVE'),
                                                                                  (8,  6,  'JDB-1006-CHK', 'CHECKING',     0.00,  'CLOSED'),
                                                                                  (9,  6,  'JDB-1006-SAV', 'SAVINGS',  11200.00,  'ACTIVE'),
                                                                                  (10, 7,  'JDB-1007-CHK', 'CHECKING',  6890.30,  'ACTIVE'),
                                                                                  (11, 8,  'JDB-1008-CHK', 'CHECKING',  4450.00,  'FROZEN'),
                                                                                  (12, 8,  'JDB-1008-SAV', 'SAVINGS',  33000.00,  'ACTIVE'),
                                                                                  (13, 9,  'JDB-1009-BIZ', 'BUSINESS', 87500.00,  'ACTIVE'),
                                                                                  (14, 10, 'JDB-1010-CHK', 'CHECKING',  2100.60,  'ACTIVE'),
                                                                                  (15, 10, 'JDB-1010-SAV', 'SAVINGS',  15750.00,  'ACTIVE');

-- ---------- TRANSACTIONS (25 — mix of all types) ----------
INSERT INTO transactions (id, from_account_id, to_account_id, amount, type, created_at) VALUES
-- Deposits (from_account_id is NULL — money coming in)
(1,  NULL, 1,    3000.00, 'DEPOSIT',    '2024-06-01 08:00:00'),
(2,  NULL, 3,    1500.00, 'DEPOSIT',    '2024-06-01 09:30:00'),
(3,  NULL, 7,    5000.00, 'DEPOSIT',    '2024-06-02 10:00:00'),
(4,  NULL, 13,  20000.00, 'DEPOSIT',    '2024-06-03 11:15:00'),
(5,  NULL, 14,    800.00, 'DEPOSIT',    '2024-06-04 14:00:00'),

-- Withdrawals (to_account_id is NULL — money going out)
(6,  1,  NULL,   200.00, 'WITHDRAWAL', '2024-06-05 12:00:00'),
(7,  3,  NULL,   350.00, 'WITHDRAWAL', '2024-06-05 13:45:00'),
(8,  4,  NULL,   500.00, 'WITHDRAWAL', '2024-06-06 09:00:00'),
(9,  10, NULL,   120.00, 'WITHDRAWAL', '2024-06-07 16:30:00'),
(10, 6,  NULL,    75.00, 'WITHDRAWAL', '2024-06-08 10:20:00'),

-- Transfers (account to account within the bank)
(11, 1,  2,    2500.00, 'TRANSFER',   '2024-06-10 08:30:00'),
(12, 4,  5,    5000.00, 'TRANSFER',   '2024-06-10 09:00:00'),
(13, 12, 11,   1000.00, 'TRANSFER',   '2024-06-11 11:00:00'),
(14, 15, 14,    500.00, 'TRANSFER',   '2024-06-12 14:15:00'),
(15, 9,  3,     750.00, 'TRANSFER',   '2024-06-13 10:45:00'),
(16, 2,  1,    1000.00, 'TRANSFER',   '2024-06-14 08:00:00'),
(17, 13, 10,   3200.00, 'TRANSFER',   '2024-06-15 09:30:00'),

-- Payments (from account, to_account_id NULL — external payee)
(18, 1,  NULL,   150.00, 'PAYMENT',   '2024-06-16 10:00:00'),
(19, 3,  NULL,    89.99, 'PAYMENT',   '2024-06-16 11:30:00'),
(20, 5,  NULL,  1200.00, 'PAYMENT',   '2024-06-17 09:00:00'),
(21, 6,  NULL,    45.00, 'PAYMENT',   '2024-06-18 15:00:00'),
(22, 10, NULL,   320.00, 'PAYMENT',   '2024-06-19 12:00:00'),
(23, 14, NULL,    65.50, 'PAYMENT',   '2024-06-20 08:45:00'),
(24, 7,  NULL,   500.00, 'PAYMENT',   '2024-06-21 14:00:00'),
(25, 13, NULL,  4500.00, 'PAYMENT',   '2024-06-22 10:30:00');

-- ---------- PAYEES (8) ----------
INSERT INTO payees (id, customer_id, name, account_number, bank_code) VALUES
                                                                          (1, 1, 'City Electric Co.',       'EXT-8001', 'CELC001'),
                                                                          (2, 1, 'Sunrise Insurance',       'EXT-8002', 'SNRI002'),
                                                                          (3, 2, 'Metro Water Authority',   'EXT-8003', 'MWAT003'),
                                                                          (4, 3, 'Tokyo Imports LLC',       'EXT-8004', 'TKIL004'),
                                                                          (5, 4, 'Rivera Family Trust',     'EXT-8005', 'RVFT005'),
                                                                          (6, 7, 'CloudHost Services',      'EXT-8006', 'CLHS006'),
                                                                          (7, 9, 'Baltic Trade Partners',   'EXT-8007', 'BLTP007'),
                                                                          (8, 10,'Garcia Auto Repair',      'EXT-8008', 'GCAR008');

-- ---------- NOTIFICATIONS (12) ----------
INSERT INTO notifications (id, customer_id, type, message, is_read, created_at) VALUES
                                                                                    (1,  1,  'SECURITY', 'New login detected from Chrome on Windows.',                   TRUE,  '2024-06-01 08:05:00'),
                                                                                    (2,  1,  'ALERT',    'Your checking account balance fell below $1,000.',              FALSE, '2024-06-16 10:05:00'),
                                                                                    (3,  2,  'INFO',     'Your June e-statement is ready to view.',                       FALSE, '2024-06-30 06:00:00'),
                                                                                    (4,  3,  'PROMO',    'Earn 4.5% APY — upgrade to a Premium Savings account today!',  FALSE, '2024-06-05 09:00:00'),
                                                                                    (5,  3,  'ALERT',    'Large transfer of $5,000.00 from checking to business account.',TRUE,  '2024-06-10 09:01:00'),
                                                                                    (6,  5,  'SECURITY', 'Your password was changed successfully.',                       TRUE,  '2024-06-03 12:00:00'),
                                                                                    (7,  6,  'ALERT',    'Account JDB-1006-CHK has been closed per your request.',        TRUE,  '2024-06-01 10:00:00'),
                                                                                    (8,  8,  'SECURITY', 'Your account JDB-1008-CHK has been temporarily frozen.',        TRUE,  '2024-06-11 11:30:00'),
                                                                                    (9,  8,  'INFO',     'Contact support to resolve the hold on your checking account.', FALSE, '2024-06-11 11:35:00'),
                                                                                    (10, 9,  'PROMO',    'Business accounts now include free international wires!',       FALSE, '2024-06-15 08:00:00'),
                                                                                    (11, 10, 'INFO',     'Welcome to JD Bank! Set up direct deposit to get started.',     TRUE,  '2024-06-10 12:05:00'),
                                                                                    (12, 10, 'ALERT',    'Payment of $65.50 sent to Garcia Auto Repair.',                 TRUE,  '2024-06-20 08:50:00');

-- ---------- AUDIT LOGS (15) ----------
INSERT INTO audit_logs (id, account_id, action, performed_by, detail, created_at) VALUES
                                                                                      (1,  1,  'ACCOUNT_OPENED',   'SYSTEM',         '{"method": "online_signup"}',                          '2024-01-15 09:31:00'),
                                                                                      (2,  2,  'ACCOUNT_OPENED',   'SYSTEM',         '{"method": "online_signup"}',                          '2024-01-15 09:32:00'),
                                                                                      (3,  8,  'ACCOUNT_CLOSED',   'james.smith',    '{"reason": "customer_request"}',                       '2024-06-01 10:00:00'),
                                                                                      (4,  1,  'DEPOSIT',          'SYSTEM',         '{"amount": 3000.00, "txn_id": 1}',                    '2024-06-01 08:00:00'),
                                                                                      (5,  1,  'WITHDRAWAL',       'aisha.patel',    '{"amount": 200.00, "txn_id": 6, "channel": "ATM"}',   '2024-06-05 12:00:00'),
                                                                                      (6,  1,  'TRANSFER_OUT',     'aisha.patel',    '{"amount": 2500.00, "txn_id": 11, "to": "JDB-1001-SAV"}','2024-06-10 08:30:00'),
                                                                                      (7,  2,  'TRANSFER_IN',      'SYSTEM',         '{"amount": 2500.00, "txn_id": 11, "from": "JDB-1001-CHK"}','2024-06-10 08:30:00'),
                                                                                      (8,  11, 'STATUS_CHANGE',    'COMPLIANCE_BOT', '{"old_status": "ACTIVE", "new_status": "FROZEN", "reason": "suspicious_activity"}','2024-06-11 11:30:00'),
                                                                                      (9,  13, 'DEPOSIT',          'SYSTEM',         '{"amount": 20000.00, "txn_id": 4}',                   '2024-06-03 11:15:00'),
                                                                                      (10, 13, 'PAYMENT',          'dmitri.volkov',  '{"amount": 4500.00, "txn_id": 25, "payee": "Baltic Trade Partners"}','2024-06-22 10:30:00'),
                                                                                      (11, 5,  'TRANSFER_IN',      'SYSTEM',         '{"amount": 5000.00, "txn_id": 12, "from": "JDB-1003-CHK"}','2024-06-10 09:00:00'),
                                                                                      (12, 9,  'TRANSFER_OUT',     'james.smith',    '{"amount": 750.00, "txn_id": 15, "to": "JDB-1002-CHK"}','2024-06-13 10:45:00'),
                                                                                      (13, 3,  'TRANSFER_IN',      'SYSTEM',         '{"amount": 750.00, "txn_id": 15, "from": "JDB-1006-SAV"}','2024-06-13 10:45:00'),
                                                                                      (14, 7,  'PAYMENT',          'fatima.ali',     '{"amount": 500.00, "txn_id": 24, "payee": "External"}','2024-06-21 14:00:00'),
                                                                                      (15, 14, 'PAYMENT',          'sofia.garcia',   '{"amount": 65.50, "txn_id": 23, "payee": "Garcia Auto Repair"}','2024-06-20 08:45:00');


-- ============================================================
--  VERIFICATION QUERIES (uncomment to test)
-- ============================================================
-- SELECT 'customers' AS tbl, COUNT(*) AS rows FROM customers
-- UNION ALL SELECT 'accounts',      COUNT(*) FROM accounts
-- UNION ALL SELECT 'transactions',  COUNT(*) FROM transactions
-- UNION ALL SELECT 'payees',        COUNT(*) FROM payees
-- UNION ALL SELECT 'notifications', COUNT(*) FROM notifications
-- UNION ALL SELECT 'audit_logs',    COUNT(*) FROM audit_logs;