package com.mercadeo.servicio.usuarios.dtos.invitation;

import lombok.Data;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.UUID;

@Data
public class ActualizarRolRequestDTO {
    @NotNull(message = "El ID de usuario no puede ser nulo.")
    private UUID idUsuario;

    @NotBlank(message = "El rol no puede estar vacío.")
    @Pattern(regexp = "PROPIETARIO|ADMINISTRADOR|MIEMBRO|LECTOR", message = "Rol no válido.")
    private String rol;

    @NotNull(message = "El estado activo no puede ser nulo.")
    private Boolean estaActivo;
}