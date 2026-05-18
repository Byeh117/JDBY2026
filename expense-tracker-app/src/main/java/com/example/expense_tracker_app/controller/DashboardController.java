package com.example.expense_tracker_app.controller;

import com.example.expense_tracker_app.dto.DashboardData;
import com.example.expense_tracker_app.service.DashboardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/dashboard")
@RequiredArgsConstructor
public class DashboardController {

    private final  DashboardService dashboardService;

    @GetMapping
    public String dashboard(Model model) {
        DashboardData data = dashboardService.currentMonth();
        model.addAttribute("dashboard", data);
        return "dashboard";
    }
}
