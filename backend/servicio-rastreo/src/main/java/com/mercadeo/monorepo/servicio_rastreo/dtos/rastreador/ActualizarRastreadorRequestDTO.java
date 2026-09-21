package com.mercadeo.monorepo.servicio_rastreo.dtos.rastreador;

import lombok.Data;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Pattern;
import java.math.BigDecimal;

@Data
public class ActualizarRastreadorRequestDTO {
    private String nombrePersonalizado;
    private BigDecimal precioObjetivo;

    @Min(value = 1, message = "La frecuencia de rastreo debe ser al menos 1 minuto.")
    private Integer frecuenciaRastreo;

    @Pattern(regexp = "ACTIVO|PAUSADO|ERROR|COMPLETADO", message = "Estado de rastreador no válido.")
    private String estado;
}