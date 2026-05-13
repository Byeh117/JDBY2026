package org.example.jbbankapp;

import org.example.jbbankapp.entities.BankAccount;
import org.example.jbbankapp.repositories.BankAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.math.BigDecimal;

@SpringBootApplication
public class JbBankAppApplication implements CommandLineRunner {

    @Autowired
    private BankAccountRepository repository;

    public static void main(String[] args) {
        SpringApplication.run(JbBankAppApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        BankAccount bankAccount = new BankAccount();
        bankAccount.setBalance(new BigDecimal("5000"));
        bankAccount.setType("CHECKING");
        repository.save(bankAccount);
        repository.findAll().forEach(System.out::println);
    }

}
