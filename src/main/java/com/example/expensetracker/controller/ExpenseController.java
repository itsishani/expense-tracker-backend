package com.example.expensetracker.controller;

import com.example.expensetracker.model.Expense;
import com.example.expensetracker.model.User;
import com.example.expensetracker.repository.ExpenseRepository;
import com.example.expensetracker.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/expense")
public class ExpenseController {
    @Autowired
    private ExpenseRepository expenseRepo;

    @Autowired
    private UserRepository userRepo;

    @PostMapping("/add")
    public Expense addExpense(@RequestParam String username, @RequestBody Expense expense) {
        User user = userRepo.findByUsername(username);
        expense.setUser(user);
        return expenseRepo.save(expense);
    }

    @GetMapping("/view")
    public List<Expense> viewExpenses(@RequestParam String username) {
        User user = userRepo.findByUsername(username);
        return expenseRepo.findByUser(user);
    }
}