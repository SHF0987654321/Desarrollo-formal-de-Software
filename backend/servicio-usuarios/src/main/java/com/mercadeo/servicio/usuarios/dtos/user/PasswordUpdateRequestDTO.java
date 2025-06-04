package com.mercadeo.servicio.usuarios.dtos.user;

import lombok.Data;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class PasswordUpdateRequestDTO {
    @NotBlank(message = "La contraseña actual no puede estar vacía.")
    private String contrasenaActual;

    @NotBlank(message = "La nueva contraseña no puede estar vacía.")
    @Size(min = 8, message = "La nueva contraseña debe tener al menos 8 caracteres.")
    private String nuevaContrasena;
}