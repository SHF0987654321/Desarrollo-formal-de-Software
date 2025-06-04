package com.mercadeo.servicio.usuarios.services;

import com.mercadeo.servicio.usuarios.dtos.organization.ActualizarOrganizacionRequestDTO;
import com.mercadeo.servicio.usuarios.dtos.organization.CrearOrganizacionRequestDTO;
import com.mercadeo.servicio.usuarios.dtos.organization.OrganizacionResponseDTO;
import com.mercadeo.servicio.usuarios.dtos.role.RolUsuarioOrganizacionResponseDTO;
import com.mercadeo.servicio.usuarios.enums.EstadoOrganizacion;
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
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class OrganizationService {

    private final OrganizacionRepository organizacionRepository;
    private final UsuarioRepository usuarioRepository;
    private final RolUsuarioOrganizacionRepository rolUsuarioOrganizacionRepository;

    public OrganizationService(OrganizacionRepository organizacionRepository, UsuarioRepository usuarioRepository, RolUsuarioOrganizacionRepository rolUsuarioOrganizacionRepository) {
        this.organizacionRepository = organizacionRepository;
        this.usuarioRepository = usuarioRepository;
        this.rolUsuarioOrganizacionRepository = rolUsuarioOrganizacionRepository;
    }

    @Transactional
    public OrganizacionResponseDTO crearOrganizacion(CrearOrganizacionRequestDTO request, UUID idUsuarioPropietario) {
        Usuario propietario = usuarioRepository.findById(idUsuarioPropietario)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario", "id", idUsuarioPropietario));

        // Validar unicidad de idFiscal si es empresa formal
        if (request.getEsEmpresaFormal() != null && request.getEsEmpresaFormal() && request.getIdFiscal() != null && request.getCodigoPaisIdFiscal() != null) {
            if (organizacionRepository.findByIdFiscalAndCodigoPaisIdFiscalAndEsEmpresaFormalIsTrue(request.getIdFiscal(), request.getCodigoPaisIdFiscal()).isPresent()) {
                throw new BadRequestException("Ya existe una organización formal registrada con este ID fiscal y código de país.");
            }
        }

        Organizacion organizacion = new Organizacion();
        organizacion.setNombre(request.getNombre());
        organizacion.setUsuarioPropietario(propietario);
        organizacion.setEsEmpresaFormal(request.getEsEmpresaFormal() != null ? request.getEsEmpresaFormal() : false);
        organizacion.setIdFiscal(request.getIdFiscal());
        organizacion.setCodigoPaisIdFiscal(request.getCodigoPaisIdFiscal());
        organizacion.setDireccionFacturacion(request.getDireccionFacturacion());
        organizacion.setCorreoContacto(request.getCorreoContacto());
        organizacion.setTelefonoContacto(request.getTelefonoContacto());
        organizacion.setDominio(request.getDominio());
        organizacion.setEstado(EstadoOrganizacion.ACTIVA); // Estado inicial

        Organizacion nuevaOrganizacion = organizacionRepository.save(organizacion);

        // Asignar rol de PROPIETARIO al usuario creador
        RolUsuarioOrganizacion rolPropietario = new RolUsuarioOrganizacion();
        rolPropietario.setUsuario(propietario);
        rolPropietario.setOrganizacion(nuevaOrganizacion);
        rolPropietario.setRol(RolUsuarioOrganizacion.PROPIETARIO);
        rolPropietario.setEstaActivo(true);
        rolUsuarioOrganizacionRepository.save(rolPropietario);

        return mapToOrganizacionResponseDTO(nuevaOrganizacion);
    }

    @Transactional(readOnly = true)
    public OrganizacionResponseDTO obtenerOrganizacionPorId(UUID id) {
        Organizacion organizacion = organizacionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Organización", "id", id));
        return mapToOrganizacionResponseDTO(organizacion);
    }

    @Transactional
    public OrganizacionResponseDTO actualizarOrganizacion(UUID idOrganizacion, ActualizarOrganizacionRequestDTO request) {
        Organizacion organizacion = organizacionRepository.findById(idOrganizacion)
                .orElseThrow(() -> new ResourceNotFoundException("Organización", "id", idOrganizacion));

        // Actualizar campos si no son nulos
        if (request.getNombre() != null) {
            organizacion.setNombre(request.getNombre());
        }
        if (request.getDireccionFacturacion() != null) {
            organizacion.setDireccionFacturacion(request.getDireccionFacturacion());
        }
        if (request.getCorreoContacto() != null) {
            organizacion.setCorreoContacto(request.getCorreoContacto());
        }
        if (request.getTelefonoContacto() != null) {
            organizacion.setTelefonoContacto(request.getTelefonoContacto());
        }

        Organizacion updatedOrganizacion = organizacionRepository.save(organizacion);
        return mapToOrganizacionResponseDTO(updatedOrganizacion);
    }

    @Transactional(readOnly = true)
    public List<OrganizacionResponseDTO> obtenerOrganizacionesPorUsuario(UUID idUsuario) {
        // Obtener todas las relaciones de roles para este usuario
        List<RolUsuarioOrganizacion> roles = rolUsuarioOrganizacionRepository.findByUsuarioId(idUsuario);

        // Mapear cada relación a un DTO de organización
        return roles.stream()
                .map(rol -> {
                    OrganizacionResponseDTO dto = mapToOrganizacionResponseDTO(rol.getOrganizacion());
                    // Puedes añadir el rol específico del usuario en esta organización si lo necesitas en el DTO
                    // dto.setRolDelUsuario(rol.getRol().name());
                    return dto;
                })
                .collect(Collectors.toList());
    }

    private OrganizacionResponseDTO mapToOrganizacionResponseDTO(Organizacion organizacion) {
        OrganizacionResponseDTO dto = new OrganizacionResponseDTO();
        dto.setId(organizacion.getId());
        dto.setNombre(organizacion.getNombre());
        dto.setIdUsuarioPropietario(organizacion.getUsuarioPropietario().getId());
        dto.setEsEmpresaFormal(organizacion.getEsEmpresaFormal());
        dto.setIdFiscal(organizacion.getIdFiscal());
        dto.setCodigoPaisIdFiscal(organizacion.getCodigoPaisIdFiscal());
        dto.setDireccionFacturacion(organizacion.getDireccionFacturacion());
        dto.setCorreoContacto(organizacion.getCorreoContacto());
        dto.setTelefonoContacto(organizacion.getTelefonoContacto());
        dto.setDominio(organizacion.getDominio());
        dto.setIdPlanSuscripcion(organizacion.getIdPlanSuscripcion());
        dto.setFechaInicioPlan(organizacion.getFechaInicioPlan());
        dto.setFechaFinPlan(organizacion.getFechaFinPlan());
        dto.setSuscripcionActiva(organizacion.getSuscripcionActiva());
        dto.setEstado(organizacion.getEstado().name());
        dto.setFechaCreacion(organizacion.getFechaCreacion());
        dto.setFechaActualizacion(organizacion.getFechaActualizacion());
        return dto;
    }
}