package com.example.todo_api.controller;

import com.example.todo_api.dto.todo.TodoRequest;
import com.example.todo_api.dto.todo.TodoResponse;
import com.example.todo_api.service.TodoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/todos")
@RequiredArgsConstructor
public class TodoController {

    private final TodoService todoService;

    @GetMapping
    public List<TodoResponse> list(
            @AuthenticationPrincipal UserDetails user,
            @RequestParam(required = false) Boolean completed) {
        if (completed == null) {
            return todoService.findAll(user.getUsername());
        }

        return todoService.findByStatus(user.getUsername(), completed);
    }

    @GetMapping("/{id}")
    public TodoResponse get(@PathVariable Long id,
                            @AuthenticationPrincipal UserDetails user) {
        return todoService.findById(id, user.getUsername());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TodoResponse create(@Valid @RequestBody TodoRequest request,
                               @AuthenticationPrincipal UserDetails user) {
        return todoService.create(request, user.getUsername());
    }

    @PutMapping("/{id}")
    public TodoResponse update(@PathVariable Long id,
                               @Valid @RequestBody TodoRequest request,
                               @AuthenticationPrincipal UserDetails user) {
        return todoService.update(id, request, user.getUsername());
    }

    @PatchMapping("/{id}/toggle")
    public TodoResponse toggle(@PathVariable Long id,
                               @AuthenticationPrincipal UserDetails user) {
        return todoService.toggleCompleted(id, user.getUsername());
    }

    @DeleteMapping("/{id}/toggle")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id,
                       @AuthenticationPrincipal UserDetails user) {
        todoService.delete(id, user.getUsername());
    }

}
