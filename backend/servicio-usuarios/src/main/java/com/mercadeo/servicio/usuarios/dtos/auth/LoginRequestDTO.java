package com.mercadeo.servicio.usuarios.dtos.auth;

import lombok.Data;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
public class LoginRequestDTO {
    @NotBlank(message = "El correo electrónico no puede estar vacío.")
    @Email(message = "El formato del correo electrónico no es válido.")
    private String correoElectronico;

    @NotBlank(message = "La contraseña no puede estar vacía.")
    private String contrasena;
}