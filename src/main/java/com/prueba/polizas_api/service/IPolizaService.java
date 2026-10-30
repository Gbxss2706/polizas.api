package com.prueba.polizas_api.service;

import com.prueba.polizas_api.dto.PolizaResponse;
import com.prueba.polizas_api.model.EstadoPoliza;
import com.prueba.polizas_api.model.TipoPoliza;

import java.util.List;

public interface IPolizaService {
    List<PolizaResponse> listar(TipoPoliza tipo, EstadoPoliza estado);
    PolizaResponse renovar(Long polizaId);
    PolizaResponse cancelar(Long polizaId);
}
