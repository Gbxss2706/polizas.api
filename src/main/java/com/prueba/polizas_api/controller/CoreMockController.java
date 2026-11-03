package com.prueba.polizas_api.controller;

import com.prueba.polizas_api.dto.EventoCoreRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/core-mock")
public class CoreMockController {

    private static final Logger log = LoggerFactory.getLogger(CoreMockController.class);

    @PostMapping("/evento")
    public ResponseEntity<Void> recibirEvento(@RequestBody EventoCoreRequest evento) {
        log.info("[CORE-MOCK] Evento recibido: {} para la póliza {}",
                evento.getEvento(), evento.getPolizaId());
        return ResponseEntity.accepted().build();
    }
}
