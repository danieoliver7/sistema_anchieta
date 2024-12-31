package com.example.anchieta_system.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Column;
import java.time.LocalDate;
import lombok.Data;

@Entity
@Data
public class Stock {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String productName;
    
    @Column(nullable = false)
    private LocalDate entryDate;
    
public Long getId() {
    return id;
}

public void setId(Long id) {
    this.id = id;
}

public String getProductName() {
    return productName;
}

public void setProductName(String productName) {
    this.productName = productName;
}

public LocalDate getEntryDate() {
    return entryDate;
}

public void setEntryDate(LocalDate entryDate) {
    this.entryDate = entryDate;
}
    
}
