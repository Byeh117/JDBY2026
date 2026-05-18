package com.example.expense_tracker_app.controller;

import com.example.expense_tracker_app.dto.AccountForm;
import com.example.expense_tracker_app.entity.Account;
import com.example.expense_tracker_app.service.AccountService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/accounts")
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;

    @GetMapping
    public String list(String name, Model model) {
        model.addAttribute("accounts", accountService.findAll(name));
        return "accounts/list";
    }

    @GetMapping("/new")
    public String newAccount(Model model) {
        model.addAttribute("accountForm", new AccountForm());
        model.addAttribute("mode", "create");
        return "accounts/form";
    }

    @PostMapping
    public String create(@Valid @ModelAttribute("accountForm") AccountForm accountForm,
                         BindingResult result,
                         Model model,
                         RedirectAttributes flash) {
        if (result.hasErrors()) {
            model.addAttribute("mode", "create");
            return "accounts/form";
        }
        Account saved = accountService.create(accountForm);
        flash.addFlashAttribute("success", "Account \"" + saved.getName() + "\" created.");
        return "redirect:/accounts";
    }

    @GetMapping("/{id}/edit")
    public String editForm(@PathVariable Long id, Model model) {
        Account account = accountService.findById(id);
        AccountForm accountForm = new AccountForm(
                account.getId(),
                account.getName(),
                account.getType().name(),
                account.getOpeningBalance()
        );
        model.addAttribute("accountForm", accountForm);
        model.addAttribute("mode", "edit");
        return "accounts/form";
    }

    @PostMapping("/{id}")
    public String accountUpdate(@PathVariable Long id,
                         @Valid @ModelAttribute("accountForm") AccountForm accountForm,
                         BindingResult result,
                         Model model,
                         RedirectAttributes flash) {
        if (result.hasErrors()) {
            model.addAttribute("mode", "edit");
            return "accounts/form";
        }
        accountService.update(id, accountForm);
        flash.addFlashAttribute("success", "Account updated.");
        return "redirect:/accounts";
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes flash) {
        accountService.delete(id);
        flash.addFlashAttribute("success", "Account deleted.");
        return "redirect:/accounts";
    }
}
