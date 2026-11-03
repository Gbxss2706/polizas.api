package com.prueba.polizas_api.service;

import com.prueba.polizas_api.core.ICoreClient;
import com.prueba.polizas_api.dto.RiesgoRequest;
import com.prueba.polizas_api.dto.RiesgoResponse;
import com.prueba.polizas_api.exception.BusinessRuleException;
import com.prueba.polizas_api.exception.ResourceNotFoundException;
import com.prueba.polizas_api.model.*;
import com.prueba.polizas_api.repository.IPolizaRepository;
import com.prueba.polizas_api.repository.IRiesgoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RiesgoService implements IRiesgoService{

    private final IRiesgoRepository riesgoRepository;
    private final IPolizaRepository polizaRepository;
    private final ICoreClient coreClient;

    public RiesgoService(IRiesgoRepository riesgoRepository, IPolizaRepository polizaRepository, ICoreClient coreClient) {
        this.riesgoRepository = riesgoRepository;
        this.polizaRepository = polizaRepository;
        this.coreClient = coreClient;
    }

    @Override
    @Transactional(readOnly = true)
    public List<RiesgoResponse> listarPorPoliza(Long polizaId) {
        buscarPoliza(polizaId);
        return riesgoRepository.findByPolizaId(polizaId).stream()
                .map(RiesgoResponse::from).toList();
    }

    @Override
    public RiesgoResponse agregar(Long polizaId, RiesgoRequest request) {
        Poliza poliza = buscarPoliza(polizaId);
        if (poliza.getTipoPoliza() != TipoPoliza.COLECTIVA) {
            throw new BusinessRuleException(
                    "Solo se pueden agregar riesgos a pólizas colectivas");
        }
        if (poliza.getEstadoPoliza() == EstadoPoliza.INACTIVO) {
            throw new BusinessRuleException("No se pueden agregar riesgos a una póliza inactiva");
        }

        Riesgo riesgo = new Riesgo();
        riesgo.setDireccionInmueble(request.getDireccionInmueble());
        riesgo.setNombreArrendatario(request.getNombreArrendatario());
        riesgo.setEstadoRiesgo(EstadoRiesgo.ACTIVO);
        poliza.agregarRiesgo(riesgo);

        Riesgo guardado = riesgoRepository.save(riesgo);
        coreClient.notificarActualizacion(polizaId, "Riesgo " + guardado.getId() + " agregado");

        return RiesgoResponse.from(guardado);
    }

    @Override
    public RiesgoResponse cancelar(Long riesgoId) {
        Riesgo riesgo = riesgoRepository.findById(riesgoId)
                .orElseThrow(() -> new ResourceNotFoundException("No existe el riesgo " + riesgoId));

        if(riesgo.getEstadoRiesgo() != EstadoRiesgo.CANCELADO) {
            riesgo.setEstadoRiesgo(EstadoRiesgo.CANCELADO);
        }else{
            throw new BusinessRuleException("No se puede cancelar el riesgo");
        }

        Riesgo guardado = riesgoRepository.save(riesgo);
        coreClient.notificarActualizacion(riesgoId, "Riesgo " + riesgoId + " cancelado");

        return RiesgoResponse.from(guardado);
    }

    private Poliza buscarPoliza(Long id) {
        return polizaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No existe la póliza " + id));
    }
}
