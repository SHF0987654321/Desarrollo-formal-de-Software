package com.mercadeo.servicio.usuarios.dtos.role;

import lombok.Data;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class RolUsuarioOrganizacionResponseDTO {
    private UUID id;
    private UUID idUsuario;
    private String nombreUsuario; // Para contexto
    private UUID idOrganizacion;
    private String nombreOrganizacion; // Para contexto
    private String rol; // Representación del ENUM
    private Boolean estaActivo;
    private LocalDateTime fechaCreacion;
}