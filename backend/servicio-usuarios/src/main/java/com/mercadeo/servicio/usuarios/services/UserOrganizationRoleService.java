package com.mercadeo.servicio.usuarios.services;

import com.mercadeo.servicio.usuarios.dtos.role.RolUsuarioOrganizacionResponseDTO;
import com.mercadeo.servicio.usuarios.enums.RolUsuarioOrganizacion;
import com.mercadeo.servicio.usuarios.exception.BadRequestException;
import com.mercadeo.servicio.usuarios.exception.ResourceNotFoundException;
import com.mercadeo.servicio.usuarios.models.Organizacion;
import com.mercadeo.servicio.usuarios.models.RolUsuarioOrganizacion;
import com.mercadeo.servicio.usuarios.models.Usuario;
import com.mercadeo.servicio.usuarios.repository.OrganizacionRepository;
import com.mercadeo.servicio.usuarios.repository.RolUsuarioOrganizacionRepository;
import com.mercadeo.servicio.usuarios.repository.UsuarioRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserOrganizationRoleService {

    private final RolUsuarioOrganizacionRepository rolUsuarioOrganizacionRepository;
    private final UsuarioRepository usuarioRepository;
    private final OrganizacionRepository organizacionRepository;

    public UserOrganizationRoleService(RolUsuarioOrganizacionRepository rolUsuarioOrganizacionRepository, UsuarioRepository usuarioRepository, OrganizacionRepository organizacionRepository) {
        this.rolUsuarioOrganizacionRepository = rolUsuarioOrganizacionRepository;
        this.usuarioRepository = usuarioRepository;
        this.organizacionRepository = organizacionRepository;
    }

    @Transactional
    public RolUsuarioOrganizacionResponseDTO asignarRol(UUID idUsuario, UUID idOrganizacion, String rolString) {
        Usuario usuario = usuarioRepository.findById(idUsuario)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario", "id", idUsuario));
        Organizacion organizacion = organizacionRepository.findById(idOrganizacion)
                .orElseThrow(() -> new ResourceNotFoundException("Organización", "id", idOrganizacion));

        RolUsuarioOrganizacion rolEnum = RolUsuarioOrganizacion.valueOf(rolString);

        return rolUsuarioOrganizacionRepository.findByUsuarioIdAndOrganizacionId(idUsuario, idOrganizacion)
                .map(existingRol -> {
                    throw new BadRequestException("El usuario ya tiene un rol asignado en esta organización.");
                })
                .orElseGet(() -> {
                    RolUsuarioOrganizacion nuevoRol = new RolUsuarioOrganizacion();
                    nuevoRol.setUsuario(usuario);
                    nuevoRol.setOrganizacion(organizacion);
                    nuevoRol.setRol(rolEnum);
                    nuevoRol.setEstaActivo(true);
                    return mapToRolUsuarioOrganizacionResponseDTO(rolUsuarioOrganizacionRepository.save(nuevoRol));
                });
    }

    @Transactional
    public RolUsuarioOrganizacionResponseDTO actualizarRol(UUID idUsuario, UUID idOrganizacion, String nuevoRolString, Boolean estaActivo) {
        RolUsuarioOrganizacion rolExistente = rolUsuarioOrganizacionRepository.findByUsuarioIdAndOrganizacionId(idUsuario, idOrganizacion)
                .orElseThrow(() -> new ResourceNotFoundException("Rol de usuario en organización", "usuarioId y organizacionId", idUsuario + " - " + idOrganizacion));

        RolUsuarioOrganizacion nuevoRolEnum = RolUsuarioOrganizacion.valueOf(nuevoRolString);

        rolExistente.setRol(nuevoRolEnum);
        rolExistente.setEstaActivo(estaActivo);

        return mapToRolUsuarioOrganizacionResponseDTO(rolUsuarioOrganizacionRepository.save(rolExistente));
    }

    @Transactional(readOnly = true)
    public List<RolUsuarioOrganizacionResponseDTO> obtenerMiembrosDeOrganizacion(UUID idOrganizacion) {
        return rolUsuarioOrganizacionRepository.findByOrganizacionId(idOrganizacion)
                .stream()
                .map(this::mapToRolUsuarioOrganizacionResponseDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public RolUsuarioOrganizacionResponseDTO obtenerRolDeUsuarioEnOrganizacion(UUID idUsuario, UUID idOrganizacion) {
        RolUsuarioOrganizacion rol = rolUsuarioOrganizacionRepository.findByUsuarioIdAndOrganizacionId(idUsuario, idOrganizacion)
                .orElseThrow(() -> new ResourceNotFoundException("Rol de usuario en organización", "usuarioId y organizacionId", idUsuario + " - " + idOrganizacion));
        return mapToRolUsuarioOrganizacionResponseDTO(rol);
    }

    private RolUsuarioOrganizacionResponseDTO mapToRolUsuarioOrganizacionResponseDTO(RolUsuarioOrganizacion rol) {
        RolUsuarioOrganizacionResponseDTO dto = new RolUsuarioOrganizacionResponseDTO();
        dto.setId(rol.getId());
        dto.setIdUsuario(rol.getUsuario().getId());
        dto.setNombreUsuario(rol.getUsuario().getNombre());
        dto.setIdOrganizacion(rol.getOrganizacion().getId());
        dto.setNombreOrganizacion(rol.getOrganizacion().getNombre());
        dto.setRol(rol.getRol().name());
        dto.setEstaActivo(rol.getEstaActivo());
        dto.setFechaCreacion(rol.getFechaCreacion());
        return dto;
    }
}
