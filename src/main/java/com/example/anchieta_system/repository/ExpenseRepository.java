package com.example.anchieta_system.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.example.anchieta_system.Entity.Expense;
import java.time.LocalDate;

public interface ExpenseRepository extends JpaRepository<Expense, Long> {

    @Query("SELECT SUM(e.amount) FROM Expense e WHERE e.date >= :startDate")
    double findTotalExpensesLast30Days(@Param("startDate") LocalDate startDate);
} 