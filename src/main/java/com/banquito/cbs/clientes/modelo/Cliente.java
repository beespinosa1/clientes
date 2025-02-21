package com.banquito.cbs.clientes.modelo;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "cliente")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID",nullable = false)
    private Integer id;

    @Column(name = "TIPO_CLIENTE",length = 3, nullable = false)
    private String tipoCliente;

    @Column(name = "TIPO_DOCUMENTO",length = 3, nullable = false)
    private String tipoDocumento;

    @Column(name = "DOCUMENTO",length = 15, nullable = false)
    private String numeroDocumento;

    @Column(name = "NOMBRE",length = 100, nullable = false)
    private String nombre;

    @Column(name = "APELLIDO",length = 100, nullable = false)
    private String apellido;

    @Column(name = "NOMBRE_COMPLETO",length = 200, nullable = false)
    private String nombreCompleto;

    @Column(name = "EMAIL",length = 100, nullable = false)
    private String email;

    @Column(name = "FECHA_NACIMIENTO", nullable = false)
    private LocalDate fechaNacimiento;

    @Column (name = "NOMBRE_COMERCIO", length = 100, nullable = true)
    private String nombreComercio;

    @Column (name = "TIPO_COMERCIO", length = 20, nullable = true)
    private String tipoComercio;

    @Column (name = "FECHA_CONSTITUCION", nullable = false)
    private LocalDate fechaConstitucion;
    
    @Column (name = "ESTADO", length = 3, nullable = false)
    private String estado;

    @Column (name = "NACIONALIDAD", length = 50, nullable = false)
    private String nacionalidad;

    @Column (name = "ESTADO_CIVIL", length = 3, nullable = false)
    private String estadoCivil;

    @Column (name = "INGRESO_PROMEDIO_MENSUAL", precision = 20, scale = 2, nullable = false)
    private BigDecimal ingresoPromedioMensual;

    @Column (name = "FECHA_CREACION", nullable = false)
    private LocalDateTime fechaCreacion;

    @Column (name = "FECHA_ACTUALIZACION", nullable = false)
    private LocalDateTime fechaActualizacion;
   
    
    
    
}