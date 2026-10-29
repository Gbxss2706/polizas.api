package com.prueba.polizas_api.dto;

import jakarta.validation.constraints.NotBlank;

public class RiesgoRequest {

    @NotBlank(message = "La dirección del inmueble es obligatoria")
    private String direccionInmueble;

    @NotBlank(message = "El nombre del arrendatario es obligatorio")
    private String nombreArrendatario;

    public RiesgoRequest() {
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
}
