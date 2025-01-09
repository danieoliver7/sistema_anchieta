package com.example.anchieta_system.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Column;
import java.time.LocalDate;
import lombok.Data;
import jakarta.persistence.PrePersist;

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
    @Column(nullable = false)
    private Double price;

    @Column(nullable = false)
    private String mark;

    @Column
    private LocalDate validity;

    @PrePersist
    protected void onCreate() {
        this.entryDate = LocalDate.now();
    }

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

public Double getPrice() {
    return price;
}

public void setPrice(Double price) {
    this.price = price;
}

public String getMark() {
    return mark;
}

public void setMark(String mark) {
    this.mark = mark;
}

public LocalDate getValidity() {
    return validity;
}

public void setValidity(LocalDate validity) {
    this.validity = validity;
}
    
}
