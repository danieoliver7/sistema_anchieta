package com.example.anchieta_system.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.anchieta_system.Entity.Stock;

public interface StockRepository extends JpaRepository<Stock, Long> {
    
}