package org.example.jbbankapp;

import org.springframework.boot.SpringApplication;

public class TestJbBankAppApplication {

    public static void main(String[] args) {
        SpringApplication.from(JbBankAppApplication::main).with(TestcontainersConfiguration.class).run(args);
    }

}
