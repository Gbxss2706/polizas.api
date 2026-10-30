package com.prueba.polizas_api.service;

import com.prueba.polizas_api.dto.RiesgoRequest;
import com.prueba.polizas_api.dto.RiesgoResponse;

import java.util.List;

public class RiesgoService implements IRiesgoService{

    @Override
    public List<RiesgoResponse> listarPorPoliza(Long polizaId) {
        return List.of();
    }

    @Override
    public RiesgoResponse agregar(Long polizaId, RiesgoRequest request) {
        return null;
    }

    @Override
    public RiesgoResponse cancelar(Long riesgoId) {
        return null;
    }
}
