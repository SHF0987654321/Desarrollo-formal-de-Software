package com.mercadeo.servicio.usuarios.services.interfaces;

import com.mercadeo.servicio.usuarios.services.dtos.invitation.AceptarRechazarInvitacionRequestDTO;
import com.mercadeo.servicio.usuarios.services.dtos.invitation.EnviarInvitacionRequestDTO;
import com.mercadeo.servicio.usuarios.services.dtos.invitation.InvitacionResponseDTO;

import java.util.List;
import java.util.UUID;

public interface InvitacionServiceInterface {
    InvitacionResponseDTO enviarInvitacion(UUID idOrganizacion, EnviarInvitacionRequestDTO request, UUID idUsuarioInvitador);
    InvitacionResponseDTO aceptarInvitacion(AceptarRechazarInvitacionRequestDTO request);
    InvitacionResponseDTO rechazarInvitacion(AceptarRechazarInvitacionRequestDTO request);
    List<InvitacionResponseDTO> obtenerInvitacionesPendientesPorCorreo(String correo);
}