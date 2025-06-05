package com.mercadeo.monorepo.servicio_rastreo.dtos.alerta;

import lombok.Data;
import javax.validation.constraints.Pattern;
import java.math.BigDecimal;

@Data
public class ActualizarAlertaRequestDTO {
    private BigDecimal condicionValor;
    private String mensaje;
    private Boolean enviada;
    private String metodoEnvio;
    private String estado; // PENDIENTE, ENVIADA, FALLIDA, CANCELADA
}