package com.banquito.cbs.clientes.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import lombok.Data;
import java.math.BigDecimal;

@Data
public class DepositoDto {
    
    @NotNull(message = "El valor del depósito es requerido")
    @DecimalMin(value = "0.01", message = "El valor del depósito debe ser mayor a 0")
    @Digits(integer = 18, fraction = 2, message = "El valor debe tener máximo 18 dígitos enteros y 2 decimales")
    private BigDecimal valor;
} 