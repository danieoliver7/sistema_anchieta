package com.example.anchieta_system.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.anchieta_system.Entity.Sale;
import com.example.anchieta_system.repository.SaleRepository;

@Service
public class SaleService {
    
    @Autowired
    private SaleRepository saleRepository;
    
    public Sale createSale(Sale sale) {
        return saleRepository.save(sale);
    }
    
    public List<Sale> getAllSales() {
        return saleRepository.findAll();
    }
} 