package com.example.anchieta_system.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.anchieta_system.Entity.Expense;
import com.example.anchieta_system.service.ExpenseService;
import com.example.anchieta_system.service.BalanceService;
import org.springframework.http.HttpStatus;

/**
 * Controlador responsável por operações relacionadas a despesas.
 */
@RestController
@RequestMapping("/api/expenses")
@CrossOrigin(origins = "http://localhost:5173")
public class ExpenseController {

    @Autowired
    private ExpenseService expenseService;

    @Autowired
    private BalanceService balanceService;

    /**
     * Cria uma nova despesa.
     *
     * @param expense Objeto Expense contendo as informações da despesa.
     * @return ResponseEntity com a despesa criada.
     */
    @PostMapping
    public ResponseEntity<?> createExpense(@RequestBody Expense expense, @RequestHeader("user") String userEmail) {
        try {
            // Cria a despesa
            Expense createdExpense = expenseService.createExpense(expense);
            
            // Atualiza o saldo automaticamente (subtraindo o valor)
            balanceService.updateBalanceAfterExpense(expense.getAmount());
            
            return ResponseEntity.ok(createdExpense);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
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

    /**
     * Retorna o total de gastos nos últimos 30 dias.
     *
     * @return ResponseEntity com o total de gastos.
     */
    @GetMapping("/last30days")
    public ResponseEntity<Double> getExpensesLast30Days() {
        try {
            double totalExpenses = expenseService.getTotalExpensesLast30Days();
            return ResponseEntity.ok(totalExpenses);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
} 