package com.prueba.polizas_api.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
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

    @OneToMany(mappedBy = "poliza", cascade = CascadeType.ALL)
    private List<Riesgo> riesgos = new ArrayList<Riesgo>();

    public void agregarRiesgo(Riesgo riesgo) {
        riesgos.add(riesgo);
        riesgo.setPoliza(this);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCanonMensual() {
        return canonMensual;
    }

    public void setCanonMensual(Long canonMensual) {
        this.canonMensual = canonMensual;
    }

    public Long getPrimaMensual() {
        return primaMensual;
    }

    public void setPrimaMensual(Long primaMensual) {
        this.primaMensual = primaMensual;
    }

    public Integer getVigenciaMensual() {
        return vigenciaMensual;
    }

    public void setVigenciaMensual(Integer vigenciaMensual) {
        this.vigenciaMensual = vigenciaMensual;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public EstadoPoliza getEstadoPoliza() {
        return estadoPoliza;
    }

    public void setEstadoPoliza(EstadoPoliza estadoPoliza) {
        this.estadoPoliza = estadoPoliza;
    }

    public TipoPoliza getTipoPoliza() {
        return tipoPoliza;
    }

    public void setTipoPoliza(TipoPoliza tipoPoliza) {
        this.tipoPoliza = tipoPoliza;
    }
}
