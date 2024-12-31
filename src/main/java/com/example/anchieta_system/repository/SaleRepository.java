package com.example.anchieta_system.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.anchieta_system.Entity.Sale;

public interface SaleRepository extends JpaRepository<Sale, Long> {
} 