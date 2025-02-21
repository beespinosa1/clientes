package com.banquito.cbs.clientes.modelo;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "usuario")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID",nullable = false)
    private Integer id;

    @Column(name = "ROL",length = 3, nullable = false)
    private String rol;

    @Column(name = "USUARIO",length = 20, nullable = false)
    private String usuario;

    @Column(name = "CLAVE",length = 20, nullable = false)
    private String clave;

    @Column(name = "ESTADO",length = 3, nullable = false)
    private String estado;

    @Column(name = "FECHA_ULTIMO_INGRESO", nullable = false)
    private LocalDateTime fechaUltimoIngreso;

    @Column(name = "IP_ULTIMO_INGRESO", length = 50, nullable = false)
    private String ipUltimoIngreso;

    @Column(name = "FECHA_CREACION", nullable = false)
    private LocalDateTime fechaCreacion;

    @Column(name = "FECHA_ACTUALIZACION", nullable = true)
    private LocalDateTime fechaActualizacion;

}
