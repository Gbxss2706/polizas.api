package com.prueba.polizas_api.controller;

import com.prueba.polizas_api.dto.PolizaResponse;
import com.prueba.polizas_api.model.EstadoPoliza;
import com.prueba.polizas_api.model.TipoPoliza;
import com.prueba.polizas_api.service.IPolizaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/polizas")
public class PolizaController {
    private final IPolizaService polizaService;

    public PolizaController(IPolizaService polizaService) {
        this.polizaService = polizaService;
    }

    @GetMapping
    public ResponseEntity<List<PolizaResponse>> listar(
            @RequestParam(required = false) TipoPoliza tipo,
            @RequestParam(required = false) EstadoPoliza estado) {
        return ResponseEntity.ok(polizaService.listar(tipo, estado));
    }

    @PostMapping("/{id}/renovar")
    public ResponseEntity<PolizaResponse> renovar(@PathVariable Long id) {
        return ResponseEntity.ok(polizaService.renovar(id));
    }

    @PostMapping("/{id}/cancelar")
    public ResponseEntity<PolizaResponse> cancelar(@PathVariable Long id) {
        return ResponseEntity.ok(polizaService.cancelar(id));
    }
}
