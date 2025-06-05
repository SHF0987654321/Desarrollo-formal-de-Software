package com.mercadeo.monorepo.servicio_rastreo.dtos.rastreador;

import lombok.Data;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.math.BigDecimal;
import java.util.UUID;

@Data
public class CrearRastreadorRequestDTO {
    @NotNull(message = "El ID de usuario configurador no puede ser nulo.")
    private UUID idUsuarioConfigurador;

    @NotNull(message = "El ID del listado no puede ser nulo.")
    private UUID idListado;

    private String nombrePersonalizado;
    private BigDecimal precioObjetivo;

    @NotNull(message = "La frecuencia de rastreo no puede ser nula.")
    @Min(value = 1, message = "La frecuencia de rastreo debe ser al menos 1 minuto.")
    private Integer frecuenciaRastreo; // en minutos

    @NotBlank(message = "El estado del rastreador no puede estar vacío.")
    @Pattern(regexp = "ACTIVO|PAUSADO|ERROR|COMPLETADO", message = "Estado de rastreador no válido.")
    private String estado;
}