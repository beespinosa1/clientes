package com.banquito.cbs.clientes.modelo;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@ToString
@EqualsAndHashCode
@Entity
@Table(name = "cuenta")
public class Cuenta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID",nullable = false)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "ID_CLIENTE", nullable = false)
    private Cliente cliente;

    @Column(name = "TIPO",length = 3, nullable = false)
    private String tipo;

    @Column(name = "NUMERO",length = 13, nullable = false)
    private String numero;

    @Column(name = "SALDO_TOTAL", precision = 20, scale = 2, nullable = false)
    private BigDecimal saldoTotal;

    @Column(name = "SALDO_DISPONIBLE", precision = 20, scale = 2, nullable = false)
    private BigDecimal saldoDisponible;

    @Column(name = "SALDO_ACREDITAR", precision = 20, scale = 2, nullable = false)
    private BigDecimal saldoAcreditar;

    @Column(name = "ESTADO",length = 3, nullable = false)
    private String estado;

    @Column(name = "FECHA_CREACION", nullable = false)
    private LocalDateTime fechaCreacion;


}
