package com.prueba.polizas_api.dto;

public class EventoCoreRequest {

    private String evento;
    private Long polizaId;

    public EventoCoreRequest(String evento, Long polizaId) {
        this.evento = evento;
        this.polizaId = polizaId;
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
}
