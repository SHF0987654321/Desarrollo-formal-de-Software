package com.mercadeo.monorepo.servicio_rastreo.dtos.alerta;

import lombok.Data;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.math.BigDecimal;
import java.util.UUID;

@Data
public class CrearAlertaRequestDTO {
    @NotNull(message = "El ID de usuario destinatario no puede ser nulo.")
    private UUID idUsuarioDestinatario;

    @NotNull(message = "El ID del rastreador no puede ser nulo.")
    private UUID idRastreador;

    @NotBlank(message = "El tipo de alerta no puede estar vacío.")
    @Pattern(regexp = "PRECIO_BAJO|PRECIO_ALTO|DISPONIBILIDAD|ERROR", message = "Tipo de alerta no válido.")
    private String tipoAlerta;

    private BigDecimal condicionValor;
    private String mensaje;
    private String metodoEnvio; // EMAIL, SMS, PUSH, WEBHOOK
}