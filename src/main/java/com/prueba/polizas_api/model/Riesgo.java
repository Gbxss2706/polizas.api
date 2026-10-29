package com.prueba.polizas_api.model;


import jakarta.persistence.*;

@Entity
@Table(name = "riesgos")
public class Riesgo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100, nullable = false)
    private String direccionInmueble;

    @Column(length = 100, nullable = false)
    private String nombreArrendatario;

    @Enumerated(EnumType.STRING)
    private EstadoRiesgo estadoRiesgo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "poliza_id", nullable = false)
    private Poliza poliza;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDireccionInmueble() {
        return direccionInmueble;
    }

    public void setDireccionInmueble(String direccionInmueble) {
        this.direccionInmueble = direccionInmueble;
    }

    public String getNombreArrendatario() {
        return nombreArrendatario;
    }

    public void setNombreArrendatario(String nombreArrendatario) {
        this.nombreArrendatario = nombreArrendatario;
    }

    public EstadoRiesgo getEstadoRiesgo() {
        return estadoRiesgo;
    }

    public void setEstadoRiesgo(EstadoRiesgo estadoRiesgo) {
        this.estadoRiesgo = estadoRiesgo;
    }
}
