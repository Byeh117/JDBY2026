package com.example.expense_tracker_app.controller;

import com.example.expense_tracker_app.entity.Transaction;
import com.example.expense_tracker_app.repository.TransactionRepository;
import com.example.expense_tracker_app.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/transactions")
@RequiredArgsConstructor
public class TransactionController {

    private final TransactionService transactionService;

    @GetMapping
    public String list(
            @RequestParam(required = false) LocalDate start,
            @RequestParam(required = false) LocalDate end,
            @RequestParam(required = false) Long categoryId,
            Model model) {

        if (start == null) start = LocalDate.now().withDayOfMonth(1);
        if (end == null) end = LocalDate.now().withDayOfMonth(LocalDate.now().lengthOfMonth());

        List<Transaction> transactions = transactionService.findFiltered(start, end, categoryId);

        model.addAttribute("transactions", transactions);
        model.addAttribute("start", start);
        model.addAttribute("end", end);
        model.addAttribute("categoryId", categoryId);
        return "transactions/list";
    }
}
