package com.example.anchieta_system.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.anchieta_system.Entity.Expense;
import com.example.anchieta_system.service.ExpenseService;

/**
 * Controlador responsável por operações relacionadas a despesas.
 */
@RestController
@RequestMapping("/api/expenses")
public class ExpenseController {

    @Autowired
    private ExpenseService expenseService;

    /**
     * Cria uma nova despesa.
     *
     * @param expense Objeto Expense contendo as informações da despesa.
     * @return ResponseEntity com a despesa criada.
     */
    @PostMapping
    public ResponseEntity<Expense> createExpense(@RequestBody Expense expense) {
        Expense createdExpense = expenseService.createExpense(expense);
        return ResponseEntity.ok(createdExpense);
    }

    /**
     * Retorna uma lista de todas as despesas registradas.
     *
     * @return ResponseEntity com a lista de despesas.
     */
    @GetMapping
    public ResponseEntity<List<Expense>> getAllExpenses() {
        List<Expense> expenses = expenseService.getAllExpenses();
        return ResponseEntity.ok(expenses);
    }
} 