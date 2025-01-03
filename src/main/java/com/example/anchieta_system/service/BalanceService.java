package com.example.anchieta_system.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.anchieta_system.Entity.Balance;
import com.example.anchieta_system.repository.BalanceRepository;
import java.math.BigDecimal;
import jakarta.annotation.PostConstruct;
import com.example.anchieta_system.Entity.User;

@Service
public class BalanceService {

    @Autowired
    private BalanceRepository balanceRepository;

    @PostConstruct
    public void initializeBalance() {
        if (balanceRepository.count() == 0) {
            Balance balance = new Balance();
            balance.setAmount(new BigDecimal("0.00"));
            balanceRepository.save(balance);
        }
    }

    public Balance getBalance() {
        return balanceRepository.findAll()
            .stream()
            .findFirst()
            .orElseThrow(() -> new RuntimeException("Nenhum saldo encontrado"));
    }

    public Balance updateBalance(Long id, Balance newBalance) {
        Balance balance = balanceRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Saldo não encontrado"));
        balance.setAmount(newBalance.getAmount());
        return balanceRepository.save(balance);
    }

    public void updateBalanceAfterSale(BigDecimal saleAmount) {
        Balance balance = getBalance();
        balance.setAmount(balance.getAmount().add(saleAmount));
        balanceRepository.save(balance);
    }

    public void updateBalanceAfterExpense(BigDecimal expenseAmount) {
        Balance balance = getBalance();
        balance.setAmount(balance.getAmount().subtract(expenseAmount));
        balanceRepository.save(balance);
    }
} 