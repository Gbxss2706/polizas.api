package com.prueba.polizas_api.dto;

public class EventoCoreRequest {

    private String evento;
    private Long polizaId;
    private String detalle;

    public EventoCoreRequest(String evento, Long polizaId, String detalle) {
        this.evento = evento;
        this.polizaId = polizaId;
        this.detalle = detalle;
    }

    public String getEvento() {
        return evento;
    }

    public Long getPolizaId() {
        return polizaId;
    }

    public void setEvento(String evento) {
        this.evento = evento;
    }

    public void setPolizaId(Long polizaId) {
        this.polizaId = polizaId;
    }

    public String getDetalle() {
        return detalle;
    }

    public void setDetalle(String detalle) {
        this.detalle = detalle;
    }
}
