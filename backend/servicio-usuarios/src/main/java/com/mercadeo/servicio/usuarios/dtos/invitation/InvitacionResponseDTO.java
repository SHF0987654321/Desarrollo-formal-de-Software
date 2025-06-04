package com.mercadeo.servicio.usuarios.dtos.invitation;

import lombok.Data;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class InvitacionResponseDTO {
    private UUID id;
    private UUID idOrganizacion;
    private String nombreOrganizacion; // Para contexto
    private String correoInvitado;
    private UUID idUsuarioInvitador;
    private String nombreUsuarioInvitador; // Para contexto
    private String rol; // Representación del ENUM
    private UUID token;
    private String estado; // Representación del ENUM
    private LocalDateTime expiraEn;
    private LocalDateTime fechaCreacion;
}