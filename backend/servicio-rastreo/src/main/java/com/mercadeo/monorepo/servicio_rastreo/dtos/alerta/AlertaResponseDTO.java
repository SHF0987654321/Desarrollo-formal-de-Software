package com.mercadeo.monorepo.servicio_rastreo.dtos.alerta;

import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class AlertaResponseDTO {
    private UUID id;
    private UUID idUsuarioDestinatario;
    private String nombreUsuarioDestinatario; // Para contexto
    private UUID idRastreador;
    private String nombreRastreador; // Para contexto
    private String tipoAlerta;
    private BigDecimal condicionValor;
    private String mensaje;
    private Boolean enviada;
    private LocalDateTime fechaEnvio;
    private String metodoEnvio;
    private String estado;
    private LocalDateTime fechaCreacion;
    private LocalDateTime fechaActualizacion;
}
