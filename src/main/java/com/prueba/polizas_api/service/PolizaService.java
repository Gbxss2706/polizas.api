package com.prueba.polizas_api.service;

import com.prueba.polizas_api.dto.PolizaResponse;
import com.prueba.polizas_api.model.EstadoPoliza;
import com.prueba.polizas_api.model.TipoPoliza;

import java.util.List;

public class PolizaService implements IPolizaService {
    @Override
    public List<PolizaResponse> listar(TipoPoliza tipo, EstadoPoliza estado) {
        return List.of();
    }

    @Override
    public PolizaResponse renovar(Long polizaId) {
        return null;
    }

    @Override
    public PolizaResponse cancelar(Long polizaId) {
        return null;
    }
}
