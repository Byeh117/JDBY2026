package com.example.expense_tracker_app.controller;

import com.example.expense_tracker_app.dto.CategoryForm;
import com.example.expense_tracker_app.entity.Category;
import com.example.expense_tracker_app.service.CategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping
    public String list(Model model) {
        model.addAttribute("Categories", categoryService.findAll());
        return "categories/list";
    }

    @GetMapping("/new")
    public String newCategory(Model model) {
        model.addAttribute("categoryForm", new CategoryForm());
        model.addAttribute("mode", "create");
        return "categories/form";
    }

    @PostMapping
    public String create(@Valid @ModelAttribute("categoryForm") CategoryForm categoryForm,
                         BindingResult result,
                         Model model,
                         RedirectAttributes flash) {
        if (result.hasErrors()) {
            model.addAttribute("mode", "created");
            return "categories/form";
        }

        Category saved = categoryService.create(categoryForm);
        flash.addFlashAttribute("success", "Category \"" + saved.getName() + "\" created.");
        return "redirect:/categories";
    }

    @GetMapping("/{id}/edit")
    public String editForm(@PathVariable Long id, Model model) {
        Category category = categoryService.findById(id);
        CategoryForm categoryForm = new CategoryForm(
                category.getId(),
                category.getName(),
                category.getType().name()
        );
        model.addAttribute("categoryForm", categoryForm);
        model.addAttribute("mode", "edit");
        return "categories/form";
    }

    @PostMapping("/{id}")
    public String categoryUpdate(@PathVariable Long id,
                                 @Valid @ModelAttribute("categoryForm") CategoryForm categoryForm,
                                 BindingResult result,
                                 Model model,
                                 RedirectAttributes flash) {
        if (result.hasErrors()) {
            model.addAttribute("mode", "edit");
            return "categories/form";
        }
        categoryService.update(id, categoryForm);
        flash.addFlashAttribute("success", "Category updated.");
        return "redirect:/categories";
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes flash) {
        categoryService.delete(id);
        flash.addFlashAttribute("success", "Category deleted.");
        return "redirect:/categories";
    }
}
