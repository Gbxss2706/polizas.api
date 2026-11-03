package com.prueba.polizas_api.service;

import com.prueba.polizas_api.core.ICoreClient;
import com.prueba.polizas_api.dto.PolizaResponse;
import com.prueba.polizas_api.exception.BusinessRuleException;
import com.prueba.polizas_api.exception.ResourceNotFoundException;
import com.prueba.polizas_api.model.EstadoPoliza;
import com.prueba.polizas_api.model.EstadoRiesgo;
import com.prueba.polizas_api.model.Poliza;
import com.prueba.polizas_api.model.TipoPoliza;
import com.prueba.polizas_api.repository.IPolizaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PolizaService implements IPolizaService {

    private final IPolizaRepository polizaRepository;
    private static final double IPC = 0.052;
    private final ICoreClient coreClient;

    public PolizaService(IPolizaRepository polizaRepository, ICoreClient coreClient) {
        this.polizaRepository = polizaRepository;
        this.coreClient = coreClient;
    }

    @Override
    @Transactional(readOnly = true)
    public List<PolizaResponse> listar(TipoPoliza tipo, EstadoPoliza estado) {
        List<Poliza> polizas;
        if (tipo != null && estado != null) {
            polizas = polizaRepository.findByTipoPolizaAndEstadoPoliza(tipo, estado);
        } else if (tipo != null) {
            polizas = polizaRepository.findByTipoPoliza(tipo);
        } else if (estado != null) {
            polizas = polizaRepository.findByEstadoPoliza(estado);
        } else {
            polizas = polizaRepository.findAll();
        }
        return polizas.stream().map(PolizaResponse::from).toList();
    }

    @Override
    public PolizaResponse renovar(Long polizaId) {
        Poliza poliza = buscarPoliza(polizaId);
        if (poliza.getEstadoPoliza() == EstadoPoliza.INACTIVO) {
            throw new BusinessRuleException("No se puede renovar una póliza inactiva");
        }

        long nuevoCanon = Math.round(poliza.getCanonMensual() * (1 + IPC));

        poliza.setFechaInicio(poliza.getFechaFin());
        poliza.setCanonMensual(nuevoCanon);
        poliza.setPrimaMensual(nuevoCanon * poliza.getVigenciaMensual());
        poliza.setEstadoPoliza(EstadoPoliza.RENOVADO);

        coreClient.notificarActualizacion(polizaId, "Póliza renovada. Nuevo canon: " + nuevoCanon);

        return PolizaResponse.from(poliza);
    }

    @Override
    public PolizaResponse cancelar(Long polizaId) {
        Poliza poliza = buscarPoliza(polizaId);
        if (poliza.getEstadoPoliza() == EstadoPoliza.INACTIVO) {
            throw new BusinessRuleException("La póliza esta inactiva");
        }

        poliza.setEstadoPoliza(EstadoPoliza.INACTIVO);
        poliza.getRiesgos().forEach(r -> r.setEstadoRiesgo(EstadoRiesgo.CANCELADO));

        coreClient.notificarActualizacion(polizaId, "Póliza cancelada junto con sus riesgos");
        return PolizaResponse.from(poliza);
    }

    private Poliza buscarPoliza(Long id) {
        return polizaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No existe la póliza " + id));
    }
}
