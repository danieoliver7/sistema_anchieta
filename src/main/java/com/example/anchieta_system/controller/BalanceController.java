package com.example.anchieta_system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.anchieta_system.Entity.Balance;
import com.example.anchieta_system.service.BalanceService;
import com.example.anchieta_system.dto.BalanceDTO;
import java.time.format.DateTimeFormatter;

@RestController
@RequestMapping("/api/balance")
@CrossOrigin(origins = "http://localhost:5173")
public class BalanceController {

    @Autowired
    private BalanceService balanceService;

    @GetMapping
    public ResponseEntity<BalanceDTO> getBalance() {
        try {
            Balance balance = balanceService.getBalance();
            
            BalanceDTO dto = new BalanceDTO();
            dto.setAmount(balance.getAmount());
            dto.setFormattedDate(balance.getDate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
            dto.setFormattedAmount("R$ " + balance.getAmount().toString());
            
            return ResponseEntity.ok(dto);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateBalance(
            @PathVariable Long id, 
            @RequestBody Balance balance) {
        try {
            Balance updatedBalance = balanceService.updateBalance(id, balance);
            return ResponseEntity.ok(updatedBalance);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
} 