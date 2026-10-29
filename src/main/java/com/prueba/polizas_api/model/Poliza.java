package com.prueba.polizas_api.model;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "polizas")
public class Poliza {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 50, nullable = false)
    private Long canonMensual;

    @Column(length = 50, nullable = false)
    private Long primaMensual;

    @Column(length = 50, nullable = false)
    private Integer vigenciaMensual;

    @Column(length = 50, nullable = false)
    private Date fechaInicio;

    @Column(length = 50, nullable = false)
    private Date fechaFin;

    @Enumerated(EnumType.STRING)
    private EstadoPoliza estadoPoliza;

    @Enumerated(EnumType.STRING)
    private TipoPoliza tipoPoliza;
}
