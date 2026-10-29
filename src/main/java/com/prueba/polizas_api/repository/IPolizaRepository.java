package com.prueba.polizas_api.repository;

import com.prueba.polizas_api.model.EstadoPoliza;
import com.prueba.polizas_api.model.Poliza;
import com.prueba.polizas_api.model.TipoPoliza;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IPolizaRepository extends JpaRepository<Poliza, Long> {
    List<Poliza> findByTipoPolizaAndEstadoPoliza(TipoPoliza tipo, EstadoPoliza estado);

    List<Poliza> findByTipoPoliza(TipoPoliza tipo);

    List<Poliza> findByEstadoPoliza(EstadoPoliza estado);
}
