package com.example.anchieta_system.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.anchieta_system.Entity.Sale;
import com.example.anchieta_system.service.SaleService;

/**
 * Controlador responsável por operações relacionadas a vendas.
 */
@RestController
@RequestMapping("/api/sales")
public class SaleController {
    
    @Autowired
    private SaleService saleService;
    
    /**
     * Cria uma nova venda.
     *
     * @param sale Objeto Sale contendo as informações da venda.
     * @return ResponseEntity com a venda criada.
     */
    @PostMapping
    public ResponseEntity<Sale> createSale(@RequestBody Sale sale) {
        Sale createdSale = saleService.createSale(sale);
        return ResponseEntity.ok(createdSale);
    }
    
    /**
     * Retorna uma lista de todas as vendas registradas.
     *
     * @return ResponseEntity com a lista de vendas.
     */
    @GetMapping
    public ResponseEntity<List<Sale>> getAllSales() {
        List<Sale> sales = saleService.getAllSales();
        return ResponseEntity.ok(sales);
    }
} 