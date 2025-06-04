package com.mercadeo.servicio.usuarios.services;

import com.mercadeo.servicio.usuarios.dtos.invitation.AceptarRechazarInvitacionRequestDTO;
import com.mercadeo.servicio.usuarios.dtos.invitation.EnviarInvitacionRequestDTO;
import com.mercadeo.servicio.usuarios.dtos.invitation.InvitacionResponseDTO;
import com.mercadeo.servicio.usuarios.enums.EstadoInvitacion;
import com.mercadeo.servicio.usuarios.enums.RolUsuarioOrganizacion;
import com.mercadeo.servicio.usuarios.exception.BadRequestException;
import com.mercadeo.servicio.usuarios.exception.ResourceNotFoundException;
import com.mercadeo.servicio.usuarios.exception.UnauthorizedException;
import com.mercadeo.servicio.usuarios.models.InvitacionOrganizacion;
import com.mercadeo.servicio.usuarios.models.Organizacion;
import com.mercadeo.servicio.usuarios.models.RolUsuarioOrganizacion;
import com.mercadeo.servicio.usuarios.models.Usuario;
import com.mercadeo.servicio.usuarios.repository.InvitacionOrganizacionRepository;
import com.mercadeo.servicio.usuarios.repository.OrganizacionRepository;
import com.mercadeo.servicio.usuarios.repository.RolUsuarioOrganizacionRepository;
import com.mercadeo.servicio.usuarios.repository.UsuarioRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class InvitacionService {

    private final InvitacionOrganizacionRepository invitacionOrganizacionRepository;
    private final OrganizacionRepository organizacionRepository;
    private final UsuarioRepository usuarioRepository;
    private final RolUsuarioOrganizacionRepository rolUsuarioOrganizacionRepository;

    public InvitacionService(InvitacionOrganizacionRepository invitacionOrganizacionRepository,
                             OrganizacionRepository organizacionRepository,
                             UsuarioRepository usuarioRepository,
                             RolUsuarioOrganizacionRepository rolUsuarioOrganizacionRepository) {
        this.invitacionOrganizacionRepository = invitacionOrganizacionRepository;
        this.organizacionRepository = organizacionRepository;
        this.usuarioRepository = usuarioRepository;
        this.rolUsuarioOrganizacionRepository = rolUsuarioOrganizacionRepository;
    }

    @Transactional
    public InvitacionResponseDTO enviarInvitacion(UUID idOrganizacion, EnviarInvitacionRequestDTO request, UUID idUsuarioInvitador) {
        Organizacion organizacion = organizacionRepository.findById(idOrganizacion)
                .orElseThrow(() -> new ResourceNotFoundException("Organización", "id", idOrganizacion));
        Usuario usuarioInvitador = usuarioRepository.findById(idUsuarioInvitador)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario", "id", idUsuarioInvitador));

        // Validar permisos del invitador (ej. solo ADMIN o PROPIETARIO pueden invitar)
        RolUsuarioOrganizacion rolInvitador = rolUsuarioOrganizacionRepository.findByUsuarioIdAndOrganizacionId(idUsuarioInvitador, idOrganizacion)
                .orElseThrow(() -> new UnauthorizedException("El usuario no pertenece a esta organización."));

        if (rolInvitador.getRol() != RolUsuarioOrganizacion.ADMINISTRADOR && rolInvitador.getRol() != RolUsuarioOrganizacion.PROPIETARIO) {
            throw new UnauthorizedException("No tienes permisos para invitar miembros a esta organización.");
        }

        // Verificar si ya existe una invitación pendiente para el mismo correo en la misma organización
        if (invitacionOrganizacionRepository.findByCorreoInvitadoAndOrganizacionId(request.getCorreoInvitado(), idOrganizacion).isPresent()) {
            throw new BadRequestException("Ya existe una invitación pendiente para este correo en esta organización.");
        }

        InvitacionOrganizacion invitacion = new InvitacionOrganizacion();
        invitacion.setOrganizacion(organizacion);
        invitacion.setCorreoInvitado(request.getCorreoInvitado());
        invitacion.setUsuarioInvitador(usuarioInvitador);
        invitacion.setRol(RolUsuarioOrganizacion.valueOf(request.getRol())); // Convertir String a ENUM
        invitacion.setToken(UUID.randomUUID()); // Generar un token único
        invitacion.setEstado(EstadoInvitacion.PENDIENTE);
        invitacion.setExpiraEn(LocalDateTime.now().plusDays(7)); // Expira en 7 días

        InvitacionOrganizacion nuevaInvitacion = invitacionOrganizacionRepository.save(invitacion);

        // Aquí se podría integrar un servicio de notificación para enviar el correo electrónico
        // notificationService.sendInvitationEmail(nuevaInvitacion.getCorreoInvitado(), nuevaInvitacion.getToken().toString(), organizacion.getNombre());

        return mapToInvitacionResponseDTO(nuevaInvitacion);
    }

    @Transactional
    public InvitacionResponseDTO aceptarInvitacion(AceptarRechazarInvitacionRequestDTO request) {
        InvitacionOrganizacion invitacion = invitacionOrganizacionRepository.findByToken(request.getToken())
                .orElseThrow(() -> new BadRequestException("Token de invitación inválido."));

        if (invitacion.getEstado() != EstadoInvitacion.PENDIENTE) {
            throw new BadRequestException("Esta invitación ya ha sido " + invitacion.getEstado().name().toLowerCase() + ".");
        }
        if (invitacion.getExpiraEn().isBefore(LocalDateTime.now())) {
            invitacion.setEstado(EstadoInvitacion.EXPIRADA);
            invitacionOrganizacionRepository.save(invitacion);
            throw new BadRequestException("La invitación ha expirado.");
        }

        // Buscar o crear el usuario invitado si no existe
        Usuario usuarioInvitado = usuarioRepository.findByCorreoElectronico(invitacion.getCorreoInvitado())
                .orElseGet(() -> {
                    // Aquí se podría implementar un flujo para que el usuario se registre primero
                    // Por simplicidad, asumimos que el usuario ya existe o se creará de alguna forma
                    throw new ResourceNotFoundException("Usuario", "correo", invitacion.getCorreoInvitado());
                });

        // Verificar si el usuario ya es miembro de la organización
        if (rolUsuarioOrganizacionRepository.findByUsuarioIdAndOrganizacionId(usuarioInvitado.getId(), invitacion.getOrganizacion().getId()).isPresent()) {
            throw new BadRequestException("El usuario ya es miembro de esta organización.");
        }

        // Crear el rol de usuario en la organización
        RolUsuarioOrganizacion nuevoRol = new RolUsuarioOrganizacion();
        nuevoRol.setUsuario(usuarioInvitado);
        nuevoRol.setOrganizacion(invitacion.getOrganizacion());
        nuevoRol.setRol(invitacion.getRol());
        nuevoRol.setEstaActivo(true);
        rolUsuarioOrganizacionRepository.save(nuevoRol);

        invitacion.setEstado(EstadoInvitacion.ACEPTADA);
        InvitacionOrganizacion updatedInvitacion = invitacionOrganizacionRepository.save(invitacion);

        return mapToInvitacionResponseDTO(updatedInvitacion);
    }

    @Transactional
    public InvitacionResponseDTO rechazarInvitacion(AceptarRechazarInvitacionRequestDTO request) {
        InvitacionOrganizacion invitacion = invitacionOrganizacionRepository.findByToken(request.getToken())
                .orElseThrow(() -> new BadRequestException("Token de invitación inválido."));

        if (invitacion.getEstado() != EstadoInvitacion.PENDIENTE) {
            throw new BadRequestException("Esta invitación ya ha sido " + invitacion.getEstado().name().toLowerCase() + ".");
        }
        if (invitacion.getExpiraEn().isBefore(LocalDateTime.now())) {
            invitacion.setEstado(EstadoInvitacion.EXPIRADA);
            invitacionOrganizacionRepository.save(invitacion);
            throw new BadRequestException("La invitación ha expirado.");
        }

        invitacion.setEstado(EstadoInvitacion.RECHAZADA);
        InvitacionOrganizacion updatedInvitacion = invitacionOrganizacionRepository.save(invitacion);

        return mapToInvitacionResponseDTO(updatedInvitacion);
    }

    @Transactional(readOnly = true)
    public List<InvitacionResponseDTO> obtenerInvitacionesPendientesPorCorreo(String correo) {
        return invitacionOrganizacionRepository.findByCorreoInvitadoAndEstado(correo, EstadoInvitacion.PENDIENTE.name())
                .stream()
                .map(this::mapToInvitacionResponseDTO)
                .collect(Collectors.toList());
    }

    private InvitacionResponseDTO mapToInvitacionResponseDTO(InvitacionOrganizacion invitacion) {
        InvitacionResponseDTO dto = new InvitacionResponseDTO();
        dto.setId(invitacion.getId());
        dto.setIdOrganizacion(invitacion.getOrganizacion().getId());
        dto.setNombreOrganizacion(invitacion.getOrganizacion().getNombre());
        dto.setCorreoInvitado(invitacion.getCorreoInvitado());
        dto.setIdUsuarioInvitador(invitacion.getUsuarioInvitador().getId());
        dto.setNombreUsuarioInvitador(invitacion.getUsuarioInvitador().getNombre());
        dto.setRol(invitacion.getRol().name());
        dto.setToken(invitacion.getToken());
        dto.setEstado(invitacion.getEstado().name());
        dto.setExpiraEn(invitacion.getExpiraEn());
        dto.setFechaCreacion(invitacion.getFechaCreacion());
        return dto;
    }
}