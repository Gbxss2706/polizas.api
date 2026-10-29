package com.prueba.polizas_api.dto;

import com.prueba.polizas_api.model.EstadoPoliza;
import com.prueba.polizas_api.model.Poliza;
import com.prueba.polizas_api.model.TipoPoliza;

import java.util.Date;

public class PolizaResponse {

    private Long id;
    private TipoPoliza tipo;
    private EstadoPoliza estado;
    private Date fechaInicio;
    private Date fechaFin;
    private Integer vigenciaMensual;
    private Long canonMensual;
    private Long primaMensual;

    public PolizaResponse() {
    }

    public static PolizaResponse from(Poliza p) {
        PolizaResponse dto = new PolizaResponse();
        dto.id = p.getId();
        dto.tipo = p.getTipoPoliza();
        dto.estado = p.getEstadoPoliza();
        dto.fechaInicio = p.getFechaInicio();
        dto.fechaFin = p.getFechaFin();
        dto.vigenciaMensual = p.getVigenciaMensual();
        dto.canonMensual = p.getCanonMensual();
        dto.primaMensual = p.getPrimaMensual();
        return dto;
    }

    public Long getId() {
        return id;
    }

    public TipoPoliza getTipo() {
        return tipo;
    }

    public EstadoPoliza getEstado() {
        return estado;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public Integer getVigenciaMensual() {
        return vigenciaMensual;
    }

    public Long getCanonMensual() {
        return canonMensual;
    }

    public Long getPrimaMensual() {
        return primaMensual;
    }
}
