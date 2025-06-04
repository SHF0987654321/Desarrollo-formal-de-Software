package com.mercadeo.servicio.usuarios.controllers;

import com.mercadeo.servicio.usuarios.dtos.organization.ActualizarOrganizacionRequestDTO;
import com.mercadeo.servicio.usuarios.dtos.organization.CrearOrganizacionRequestDTO;
import com.mercadeo.servicio.usuarios.dtos.organization.OrganizacionResponseDTO;
import com.mercadeo.servicio.usuarios.dtos.role.ActualizarRolRequestDTO;
import com.mercadeo.servicio.usuarios.dtos.role.RolUsuarioOrganizacionResponseDTO;
import com.mercadeo.servicio.usuarios.services.OrganizationService;
import com.mercadeo.servicio.usuarios.services.UserOrganizationRoleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/organizaciones")
@Validated
public class OrganizacionController {

    private final OrganizationService organizationService;
    private final UserOrganizationRoleService userOrganizationRoleService;

    public OrganizacionController(OrganizationService organizationService, UserOrganizationRoleService userOrganizationRoleService) {
        this.organizationService = organizationService;
        this.userOrganizationRoleService = userOrganizationRoleService;
    }

    @PostMapping
    public ResponseEntity<OrganizacionResponseDTO> crearOrganizacion(@Valid @RequestBody CrearOrganizacionRequestDTO request) {
        // En un sistema real, el idUsuarioPropietario se obtendría del contexto de seguridad (usuario autenticado)
        UUID idUsuarioPropietario = UUID.fromString("a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a11"); // Placeholder
        OrganizacionResponseDTO response = organizationService.crearOrganizacion(request, idUsuarioPropietario);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrganizacionResponseDTO> obtenerOrganizacionPorId(@PathVariable UUID id) {
        // En un sistema real, se verificaría que el usuario autenticado pertenece a esta organización
        OrganizacionResponseDTO response = organizationService.obtenerOrganizacionPorId(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<OrganizacionResponseDTO> actualizarOrganizacion(@PathVariable UUID id, @Valid @RequestBody ActualizarOrganizacionRequestDTO request) {
        // En un sistema real, se verificaría que el usuario autenticado tiene permisos de admin/propietario
        OrganizacionResponseDTO response = organizationService.actualizarOrganizacion(id, request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/mis-organizaciones")
    public ResponseEntity<List<OrganizacionResponseDTO>> obtenerMisOrganizaciones() {
        // En un sistema real, el idUsuario se obtendría del contexto de seguridad (usuario autenticado)
        UUID idUsuario = UUID.fromString("a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a11"); // Placeholder
        List<OrganizacionResponseDTO> response = organizationService.obtenerOrganizacionesPorUsuario(idUsuario);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // Endpoints para gestión de miembros (roles) dentro de una organización
    @GetMapping("/{idOrganizacion}/miembros")
    public ResponseEntity<List<RolUsuarioOrganizacionResponseDTO>> obtenerMiembrosDeOrganizacion(@PathVariable UUID idOrganizacion) {
        // Verificar permisos del usuario autenticado para ver miembros de esta organización
        List<RolUsuarioOrganizacionResponseDTO> response = userOrganizationRoleService.obtenerMiembrosDeOrganizacion(idOrganizacion);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/{idOrganizacion}/miembros/{idUsuario}/rol")
    public ResponseEntity<RolUsuarioOrganizacionResponseDTO> actualizarRolMiembro(
            @PathVariable UUID idOrganizacion,
            @PathVariable UUID idUsuario,
            @Valid @RequestBody ActualizarRolRequestDTO request) {
        // Verificar permisos del usuario autenticado para actualizar roles en esta organización
        RolUsuarioOrganizacionResponseDTO response = userOrganizationRoleService.actualizarRol(idUsuario, idOrganizacion, request.getRol(), request.getEstaActivo());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}