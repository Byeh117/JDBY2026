package org.example.todo_app.controller;

import org.example.todo_app.dto.TodoForm;
import org.example.todo_app.entity.Todo;
import org.example.todo_app.service.TodoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/todos")
@RequiredArgsConstructor
public class TodoController {

    private final TodoService todoService;

    // LIST + filter
    @GetMapping
    public String list(@RequestParam(defaultValue = "all") String filter,
                       Model model) {
        model.addAttribute("todos", todoService.findByStatus(filter));
        model.addAttribute("filter", filter);
        return "todos/list";
    }

    // NEW Form
    @GetMapping("/new")
    public String newForm(Model model) {
        model.addAttribute("todoForm", new TodoForm());
        model.addAttribute("mode", "create");
        return "todos/form";
    }

    // CREATE
    @PostMapping
    public String create(@Valid @ModelAttribute("todoForm") TodoForm form,
                         BindingResult result,
                         Model model,
                         RedirectAttributes flash) {
        if (result.hasErrors()) {
            model.addAttribute("mode", "create");
            return "todos/form";
        }
        Todo saved = todoService.create(form);
        flash.addFlashAttribute("success", "Todo \"" + saved.getTitle() + "\" created. ");
        return "redirect:/todos";
    }

    // EDIT form
    @GetMapping("/{id}/edit")
    public String editForm(@PathVariable Long id, Model model) {
        Todo todo = todoService.findById(id);
        TodoForm form = new TodoForm(
                todo.getId(),
                todo.getTitle(),
                todo.getDescription(),
                todo.isCompleted()
        );
        model.addAttribute("todoForm", form);
        model.addAttribute("mode", "edit");
        return "todos/form";
    }

    // UPDATE
    @PostMapping("/{id}")
    public String update(@PathVariable Long id,
                         @Valid @ModelAttribute("todoForm") TodoForm form,
                         BindingResult result,
                         Model model,
                         RedirectAttributes flash) {
        if (result.hasErrors()) {
            model.addAttribute("mode", "edit");
            return "todos/form";
        }
        todoService.update(id, form);
        flash.addFlashAttribute("success", "Todo updated.");
        return "redirect:/todos";
    }

    // TOGGLE complete
    @PostMapping("/{id}/toggle")
    public String toggle(@PathVariable Long id, RedirectAttributes flash) {
        todoService.toggleCompleted(id);
        return "redirect:/todos";
    }

    // DELETE
    @PostMapping("/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes flash) {
        todoService.delete(id);
        flash.addFlashAttribute("success", "Todo deleted.");
        return "redirect:/todos";
    }
}
