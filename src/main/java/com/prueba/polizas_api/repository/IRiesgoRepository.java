package com.prueba.polizas_api.repository;

import com.prueba.polizas_api.model.EstadoRiesgo;
import com.prueba.polizas_api.model.Riesgo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IRiesgoRepository extends JpaRepository<Riesgo, Long> {
    List<Riesgo> findByPolizaId(Long polizaId);

    long countByPolizaIdAndEstadoRiesgo(Long polizaId, EstadoRiesgo estado);
}
