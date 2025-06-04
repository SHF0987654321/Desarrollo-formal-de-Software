package com.mercadeo.servicio.usuarios.dtos.invitation;

import lombok.Data;
import javax.validation.constraints.NotBlank;
import java.util.UUID;
import javax.validation.constraints.Pattern;

@Data
public class AceptarRechazarInvitacionRequestDTO {
    @NotBlank(message = "El token de invitación no puede estar vacío.")
    private UUID token;

    @NotBlank(message = "La acción no puede estar vacía.")
    @Pattern(regexp = "ACEPTAR|RECHAZAR", message = "Acción no válida. Debe ser 'ACEPTAR' o 'RECHAZAR'.")
    private String accion;
}