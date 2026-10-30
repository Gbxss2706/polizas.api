package com.prueba.polizas_api.service;

import com.prueba.polizas_api.dto.RiesgoRequest;
import com.prueba.polizas_api.dto.RiesgoResponse;

import java.util.List;

public interface IRiesgoService {
    List<RiesgoResponse> listarPorPoliza(Long polizaId);
    RiesgoResponse agregar(Long polizaId, RiesgoRequest request);
    RiesgoResponse cancelar(Long riesgoId);
}
