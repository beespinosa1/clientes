package com.banquito.cbs.clientes.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class CuentaDto {
    
    private Integer id;

    @NotNull(message = "El ID del cliente es requerido")
    private Integer clienteId;

    @NotBlank(message = "El tipo de cuenta es requerido")
    @Size(min = 3, max = 3, message = "El tipo debe tener 3 caracteres")
    private String tipo;

    @NotBlank(message = "El número de cuenta es requerido")
    @Size(max = 13, message = "El número no debe exceder 13 caracteres")
    private String numero;

    @NotNull(message = "El saldo total es requerido")
    @DecimalMin(value = "0.0", message = "El saldo total no puede ser negativo")
    @Digits(integer = 18, fraction = 2, message = "El saldo total debe tener máximo 18 dígitos enteros y 2 decimales")
    private BigDecimal saldoTotal;

    @NotNull(message = "El saldo disponible es requerido")
    @DecimalMin(value = "0.0", message = "El saldo disponible no puede ser negativo")
    @Digits(integer = 18, fraction = 2, message = "El saldo disponible debe tener máximo 18 dígitos enteros y 2 decimales")
    private BigDecimal saldoDisponible;

    @NotNull(message = "El saldo a acreditar es requerido")
    @DecimalMin(value = "0.0", message = "El saldo a acreditar no puede ser negativo")
    @Digits(integer = 18, fraction = 2, message = "El saldo a acreditar debe tener máximo 18 dígitos enteros y 2 decimales")
    private BigDecimal saldoAcreditar;

    @NotBlank(message = "El estado es requerido")
    @Size(min = 3, max = 3, message = "El estado debe tener 3 caracteres")
    private String estado;

    private LocalDateTime fechaCreacion;
} 