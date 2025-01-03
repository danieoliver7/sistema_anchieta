package com.example.anchieta_system.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.anchieta_system.repository.ExpenseRepository;
import com.example.anchieta_system.Entity.Expense;
import java.time.LocalDate;
import java.util.List;

@Service
public class ExpenseService {
    
    @Autowired
    private ExpenseRepository expenseRepository;

    public Expense createExpense(Expense expense) {
        return expenseRepository.save(expense);
    }

    public List<Expense> getAllExpenses() {
        return expenseRepository.findAll();
    }

    public double getTotalExpensesLast30Days() {
        LocalDate thirtyDaysAgo = LocalDate.now().minusDays(30);
        return expenseRepository.findTotalExpensesLast30Days(thirtyDaysAgo);
    }
}

