package com.prueba.polizas_api.core;

import com.prueba.polizas_api.dto.EventoCoreRequest;
import org.springframework.stereotype.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestClientException;

@Component
public class CoreClient implements ICoreClient{
    private static final Logger log = LoggerFactory.getLogger(CoreClient.class);

    private final RestClient restClient;

    public CoreClient(@Value("${app.core.url}") String coreUrl,
                          @Value("${app.api-key}") String apiKey) {
        this.restClient = RestClient.builder()
                .baseUrl(coreUrl)
                .defaultHeader("x-api-key", apiKey)
                .build();
    }

    @Override
    public void notificarActualizacion(Long polizaId, String detalle) {
        try {
            restClient.post()
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(new EventoCoreRequest("ACTUALIZACION", polizaId, detalle))
                    .retrieve()
                    .toBodilessEntity();
        } catch (RestClientException e) {
            log.error("No se pudo notificar al CORE la póliza {}: {}", polizaId, detalle, e);
        }
    }
}
