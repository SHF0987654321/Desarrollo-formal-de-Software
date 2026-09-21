package com.mercadeo.servicio.usuarios.dtos.auth;

import lombok.Data;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Data
public class RegistroUsuarioRequestDTO {
    @NotBlank(message = "El correo electrónico no puede estar vacío.")
    @Email(message = "El formato del correo electrónico no es válido.")
    private String correoElectronico;

    @NotBlank(message = "La contraseña no puede estar vacía.")
    @Size(min = 8, message = "La contraseña debe tener al menos 8 caracteres.")
    private String contrasena;

    @NotBlank(message = "El nombre no puede estar vacío.")
    @Size(max = 255, message = "El nombre no puede exceder los 255 caracteres.")
    private String nombre;
}
