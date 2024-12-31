package com.example.anchieta_system.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.anchieta_system.Entity.Expense;


public interface ExpeseRepository extends JpaRepository<Expense, Long> {
    
}