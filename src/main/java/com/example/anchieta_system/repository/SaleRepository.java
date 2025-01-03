package com.example.anchieta_system.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.example.anchieta_system.Entity.Sale;
import java.math.BigDecimal;

public interface SaleRepository extends JpaRepository<Sale, Long> {

    @Query("SELECT SUM(s.amount) FROM Sale s")
    BigDecimal calculateTotalSales();
} 