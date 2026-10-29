package com.prueba.polizas_api.dto;

import com.prueba.polizas_api.model.EstadoRiesgo;
import com.prueba.polizas_api.model.Riesgo;

public class RiesgoResponse {

    private Long id;
    private String direccionInmueble;
    private String arrendatario;
    private EstadoRiesgo estado;
    private Long polizaId;

    public RiesgoResponse() {
    }

    public static RiesgoResponse from(Riesgo r) {
        RiesgoResponse dto = new RiesgoResponse();
        dto.id = r.getId();
        dto.direccionInmueble = r.getDireccionInmueble();
        dto.arrendatario = r.getNombreArrendatario();
        dto.estado = r.getEstadoRiesgo();
        dto.polizaId = r.getPoliza().getId();
        return dto;
    }
}
