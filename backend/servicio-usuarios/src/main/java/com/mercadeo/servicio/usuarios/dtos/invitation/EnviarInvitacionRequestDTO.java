package com.mercadeo.servicio.usuarios.dtos.invitation;

import lombok.Data;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data
public class EnviarInvitacionRequestDTO {
    @NotBlank(message = "El correo del invitado no puede estar vacío.")
    @Email(message = "El formato del correo del invitado no es válido.")
    private String correoInvitado;

    @NotBlank(message = "El rol no puede estar vacío.")
    @Pattern(regexp = "PROPIETARIO|ADMINISTRADOR|MIEMBRO|LECTOR", message = "Rol no válido.")
    private String rol;
}