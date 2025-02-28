package com.banquito.cbs.clientes.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import lombok.Data;
import java.math.BigDecimal;

@Data
public class CuentaDto {
    
    @NotNull(message = "El ID del cliente es requerido")
    private Integer idCliente;

    @NotBlank(message = "El tipo de cuenta es requerido")
    @Size(min = 3, max = 3, message = "El tipo debe tener 3 caracteres")
    private String tipo;

    @NotNull(message = "El saldo disponible es requerido")
    @DecimalMin(value = "0.0", message = "El saldo disponible no puede ser negativo")
    @Digits(integer = 18, fraction = 2, message = "El saldo disponible debe tener máximo 18 dígitos enteros y 2 decimales")
    private BigDecimal saldoDisponible;
} 