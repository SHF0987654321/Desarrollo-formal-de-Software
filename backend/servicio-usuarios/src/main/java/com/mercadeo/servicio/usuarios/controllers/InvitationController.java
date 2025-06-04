package com.mercadeo.servicio.usuarios.controllers;

import com.mercadeo.servicio.usuarios.dtos.invitation.AceptarRechazarInvitacionRequestDTO;
import com.mercadeo.servicio.usuarios.dtos.invitation.EnviarInvitacionRequestDTO;
import com.mercadeo.servicio.usuarios.dtos.invitation.InvitacionResponseDTO;
import com.mercadeo.servicio.usuarios.services.InvitacionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api")
@Validated
public class InvitacionController {

    private final InvitacionService invitacionService;

    public InvitacionController(InvitacionService invitacionService) {
        this.invitacionService = invitacionService;
    }

    @PostMapping("/organizaciones/{idOrganizacion}/invitar")
    public ResponseEntity<InvitacionResponseDTO> enviarInvitacion(
            @PathVariable UUID idOrganizacion,
            @Valid @RequestBody EnviarInvitacionRequestDTO request) {
        // En un sistema real, el idUsuarioInvitador se obtendría del contexto de seguridad
        UUID idUsuarioInvitador = UUID.fromString("a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a11"); // Placeholder
        InvitacionResponseDTO response = invitacionService.enviarInvitacion(idOrganizacion, request, idUsuarioInvitador);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PostMapping("/invitaciones/aceptar")
    public ResponseEntity<InvitacionResponseDTO> aceptarInvitacion(@Valid @RequestBody AceptarRechazarInvitacionRequestDTO request) {
        // Aquí no se requiere autenticación si el token es la única validación,
        // pero se podría requerir autenticación del usuario que acepta para vincularlo.
        InvitacionResponseDTO response = invitacionService.aceptarInvitacion(request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/invitaciones/rechazar")
    public ResponseEntity<InvitacionResponseDTO> rechazarInvitacion(@Valid @RequestBody AceptarRechazarInvitacionRequestDTO request) {
        InvitacionResponseDTO response = invitacionService.rechazarInvitacion(request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/invitaciones/pendientes")
    public ResponseEntity<List<InvitacionResponseDTO>> obtenerInvitacionesPendientes() {
        // En un sistema real, el correo se obtendría del usuario autenticado
        String correoUsuarioAutenticado = "usuario@example.com"; // Placeholder
        List<InvitacionResponseDTO> response = invitacionService.obtenerInvitacionesPendientesPorCorreo(correoUsuarioAutenticado);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}