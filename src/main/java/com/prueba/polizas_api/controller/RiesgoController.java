package com.prueba.polizas_api.controller;

import com.prueba.polizas_api.dto.RiesgoRequest;
import com.prueba.polizas_api.dto.RiesgoResponse;
import com.prueba.polizas_api.service.IRiesgoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class RiesgoController {

    private final IRiesgoService riesgoService;

    public RiesgoController(IRiesgoService riesgoService) {
        this.riesgoService = riesgoService;
    }

    @GetMapping("/polizas/{polizaId}/riesgos")
    public ResponseEntity<List<RiesgoResponse>> listarPorPoliza(@PathVariable Long polizaId) {
        return ResponseEntity.ok(riesgoService.listarPorPoliza(polizaId));
    }

    @PostMapping("/polizas/{polizaId}/riesgos")
    public ResponseEntity<RiesgoResponse> agregar(@PathVariable Long polizaId,
                                                  @Valid @RequestBody RiesgoRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(riesgoService.agregar(polizaId, request));
    }

    @PostMapping("/riesgos/{id}/cancelar")
    public ResponseEntity<RiesgoResponse> cancelar(@PathVariable Long id) {
        return ResponseEntity.ok(riesgoService.cancelar(id));
    }
}