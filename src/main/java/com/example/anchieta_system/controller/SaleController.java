package com.example.anchieta_system.controller;

import java.util.List;
import java.math.BigDecimal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.anchieta_system.Entity.Sale;
import com.example.anchieta_system.service.SaleService;
import com.example.anchieta_system.service.BalanceService;
import org.springframework.http.HttpStatus;

/**
 * Controlador responsável por operações relacionadas a vendas.
 */
@RestController
@RequestMapping("/api/sales")
@CrossOrigin(origins = "http://localhost:5173")
public class SaleController {
    
    @Autowired
    private SaleService saleService;
    
    @Autowired
    private BalanceService balanceService;
    
    /**
     * Cria uma nova venda.
     *
     * @param sale Objeto Sale contendo as informações da venda.
     * @return ResponseEntity com a venda criada.
     */
    @PostMapping
    public ResponseEntity<?> createSale(@RequestBody Sale sale, @RequestHeader("user") String userEmail) {
        try {
            // Cria a venda
            Sale createdSale = saleService.createSale(sale);
            
            // Atualiza o saldo automaticamente
            balanceService.updateBalanceAfterSale(sale.getAmount());
            
            return ResponseEntity.ok(createdSale);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
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
    
    /**
     * Retorna o total de vendas registradas.
     *
     * @return ResponseEntity com o total de vendas.
     */
    @GetMapping("/total")
    public ResponseEntity<BigDecimal> getTotalSales() {
        try {
            BigDecimal totalSales = saleService.getTotalSales();
            return ResponseEntity.ok(totalSales);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
} 