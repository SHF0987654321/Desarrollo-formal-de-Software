package com.mercadeo.servicio.usuarios.dtos.auth;

import lombok.Data;
import java.util.UUID;

@Data
public class LoginResponseDTO {
    private String accessToken;
    private String refreshToken; // Opcional, si se implementa
    private UUID idUsuario;
    private String correoElectronico;
    private String nombre;
}