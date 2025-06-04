package com.mercadeo.servicio.usuarios.dtos.user;

import lombok.Data;
import javax.validation.constraints.Size;

@Data
public class ActualizarUsuarioRequestDTO {
    @Size(max = 255, message = "El nombre no puede exceder los 255 caracteres.")
    private String nombre;
}