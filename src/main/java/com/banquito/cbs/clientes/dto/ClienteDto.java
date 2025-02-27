package com.banquito.cbs.clientes.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class ClienteDto {
    
    @NotBlank(message = "El tipo de cliente es requerido")
    @Size(min = 3, max = 3, message = "El tipo de cliente debe tener 3 caracteres")
    private String tipoCliente;

    @NotBlank(message = "El tipo de identificación es requerido")
    @Size(min = 3, max = 3, message = "El tipo de identificación debe tener 3 caracteres")
    private String tipoDocumento;

    @NotBlank(message = "El número de identificación es requerido")
    @Size(max = 20, message = "El número de identificación no debe exceder 20 caracteres")
    private String numeroDocumento;

    @NotBlank(message = "Los nombres son requeridos")
    @Size(max = 100, message = "Los nombres no deben exceder 50 caracteres")
    private String nombre;

    @NotBlank(message = "Los apellidos son requeridos")
    @Size(max = 100, message = "Los apellidos no deben exceder 50 caracteres")
    private String apellido;

    @NotBlank(message = "El nombre completo es requerido")
    @Size(max = 200, message = "El nombre completo no debe exceder 100 caracteres")
    private String nombreCompleto;
    
    @NotBlank(message = "El email es requerido")
    @Pattern(regexp = "^[A-Za-z0-9+_.-]+@(.+)$", message = "Email inválido")
    private String email;

    @NotNull(message = "La fecha de nacimiento es requerida")
    @Past(message = "La fecha de nacimiento debe ser una fecha pasada")
    private LocalDate fechaNacimiento;

    @Size(max = 100, message = "El nombre del comercio no debe exceder 100 caracteres")
    private String nombreComercio;

    @Size(max = 20, message = "El tipo de comercio no debe exceder 20 caracteres")
    private String tipoComercio;

    @Past(message = "La fecha de constitución debe ser una fecha pasada")
    private LocalDate fechaConstitucion;

    @Size(min = 3, max = 3, message = "El estado comercio debe tener 3 caracteres")
    private String estado;

    @NotBlank(message = "La nacionalidad es requerida")
    @Size(max = 50, message = "La nacionalidad no debe exceder 50 caracteres")
    private String nacionalidad;

    @NotBlank(message = "El estado civil es requerido")
    @Size(min = 3, max = 3, message = "El estado civil debe tener 3 caracteres")
    private String estadoCivil;

    @NotNull(message = "El ingreso promedio mensual es requerido")
    @DecimalMin(value = "0.0", message = "El ingreso promedio mensual no puede ser negativo")
    @Digits(integer = 18, fraction = 2, message = "El ingreso debe tener máximo 18 dígitos enteros y 2 decimales")
    private BigDecimal ingresoPromedioMensual;
} 