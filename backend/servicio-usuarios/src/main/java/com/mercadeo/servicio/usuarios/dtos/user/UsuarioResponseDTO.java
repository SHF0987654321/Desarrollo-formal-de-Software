package com.mercadeo.servicio.usuarios.dtos.user;

import lombok.Data;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class UsuarioResponseDTO {
    private UUID id;
    private String correoElectronico;
    private String nombre;
    private LocalDateTime fechaCreacion;
}
