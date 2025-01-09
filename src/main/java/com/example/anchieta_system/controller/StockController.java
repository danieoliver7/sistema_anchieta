package com.example.anchieta_system.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.anchieta_system.Entity.Stock;
import com.example.anchieta_system.service.StockService;

import lombok.extern.slf4j.Slf4j;

/**
 * Controlador responsável por operações relacionadas a estoque.
 */
@Slf4j
@RestController
@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping("/auth/stocks")
public class StockController {

    @Autowired
    private StockService stockService;

    /**
     * Cria um novo item de estoque.
     *
     * @param stock Objeto Stock contendo as informações do item de estoque.
     * @return ResponseEntity com o item de estoque criado.
     */
    @PostMapping
    public ResponseEntity<Stock> createStock(@RequestBody Stock stock) {
        Stock createdStock = stockService.createStock(stock);
        return ResponseEntity.ok(createdStock);
    }

    /**
     * Retorna uma lista de todos os itens de estoque registrados.
     *
     * @return ResponseEntity com a lista de itens de estoque.
     */
    @GetMapping
    public ResponseEntity<List<Stock>> getAllStocks() {
        List<Stock> stocks = stockService.getAllStocks();
        return ResponseEntity.ok(stocks);
    }
} 