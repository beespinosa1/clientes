package com.banquito.cbs.clientes.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import lombok.Data;
import java.math.BigDecimal;

@Data
public class CrearCuentaDto {
    
    @NotNull(message = "El ID del cliente es requerido")
    private Integer clienteId;

    @NotNull(message = "El tipo de cuenta es requerido")
    private String tipo;

    @NotNull(message = "El depósito inicial es requerido")
    @DecimalMin(value = "0.0", message = "El depósito inicial no puede ser negativo")
    @Digits(integer = 18, fraction = 2, message = "El depósito inicial debe tener máximo 18 dígitos enteros y 2 decimales")
    private BigDecimal depositoInicial;
} 