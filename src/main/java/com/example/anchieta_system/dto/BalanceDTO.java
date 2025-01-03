package com.example.anchieta_system.dto;

import java.math.BigDecimal;
import lombok.Data;

@Data
public class BalanceDTO {
    private BigDecimal amount;
    private String formattedDate; // Data formatada para exibição
    
    // Pode adicionar campos calculados ou formatados
    private String formattedAmount; // Exemplo: R$ 1.000,00
} 