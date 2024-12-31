package com.example.anchieta_system.Entity;

import java.math.BigDecimal;
import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Column;
import lombok.Data;

@Entity
@Data
public class Sale {
     @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private BigDecimal totalValue;
    
    @Column(nullable = false)
    private LocalDate date;

public Long getId() {
    return id;
}

public void setId(Long id) {
    this.id = id;
}

public BigDecimal getTotalValue() {
    return totalValue;
}

public void setTotalValue(BigDecimal totalValue) {
    this.totalValue = totalValue;
}

public LocalDate getDate() {
    return date;
}

public void setDate(LocalDate date) {
    this.date = date;
}
    
}

